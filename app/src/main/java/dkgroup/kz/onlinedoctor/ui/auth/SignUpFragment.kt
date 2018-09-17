package dkgroup.kz.onlinedoctor.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.FlowRouter
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.Screens
import dkgroup.kz.onlinedoctor.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*
import javax.inject.Inject


class SignUpFragment : BaseFragment() {


    override val layoutRes: Int = R.layout.activity_sign_up
    internal lateinit var auth: FirebaseAuth
    internal lateinit var database: DatabaseReference
    internal lateinit var userType: String
    internal lateinit var db: FirebaseFirestore
    @Inject
    internal lateinit var router: FlowRouter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userType = arguments.getString(ARG_USER)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        database = FirebaseDatabase.getInstance().reference
        signUpButton.setOnClickListener {
            var emailString = emailInput.text.toString()
            if (emailString=="") {
                emailInput.error = "This field can't be empty."
                return@setOnClickListener
            }
            var passwordString = passwordInput.text.toString()
            if (passwordString=="") {
                passwordInput.error = "This field can't be empty."
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(emailInput!!.text.toString(), passwordInput!!.text.toString())
                    .addOnCompleteListener({
                        if (!it.isSuccessful) {
                            Toast.makeText(context, it.exception!!.message,
                                    Toast.LENGTH_SHORT).show()
                        } else {
                            val user: HashMap<String, Any> = HashMap()
                            user.put("firstName", firstNameInput.text.toString())
                            user.put("lastName", lastNameInput.text.toString())
                            user.put("userType", userType)
                            user.put("email", it.result.user.email!!)
                            user.put("fcmToken", activity.getApplication().getSharedPreferences("sprefs", Context.MODE_PRIVATE).getString("token", null))
                            db.collection("users")
                                    .document(it.result.user.uid)
                                    .set(user)
                                    .addOnCompleteListener {
                                        router.startFlow(Screens.MAIN_FLOW)
                                    }
                                    .addOnFailureListener(OnFailureListener {
                                    })
                        }

                    })
        }
        signInButton.setOnClickListener {
            router.replaceScreen(Screens.SIGN_IN_SCREEN, userType)
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    companion object {
        private val ARG_USER = "user_type"

        fun newInstance(userType: String): Fragment? {
            var signUpFragment = SignUpFragment()
            var args = Bundle()
            args.putString(ARG_USER, userType)
            signUpFragment.arguments = args
            return signUpFragment
        }
    }
}