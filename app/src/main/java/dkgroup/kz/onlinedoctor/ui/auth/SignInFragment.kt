package dkgroup.kz.onlinedoctor.ui.auth

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.FlowRouter
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.Screens
import dkgroup.kz.onlinedoctor.ui.base.BaseFragment
import kotlinx.android.synthetic.main.sign_in_fragment.*
import java.util.*
import javax.inject.Inject

class SignInFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.sign_in_fragment
    internal lateinit var auth: FirebaseAuth
    internal lateinit var userType: String
    internal lateinit var db: FirebaseFirestore
    @Inject
    internal lateinit var router: FlowRouter


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        userType = arguments.getString(ARG_USER)
        db = FirebaseFirestore.getInstance()
        signUpButton.setOnClickListener {
            router.replaceScreen(Screens.SIGN_UP_SCREEN, userType)
        }
        signInButton.setOnClickListener {
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
            auth.signInWithEmailAndPassword(emailInput.text.toString(), passwordInput.text.toString())
                    .addOnCompleteListener(OnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            Toast.makeText(context,"Incorrect username or password",Toast.LENGTH_LONG).show()
                        } else {
                            val user: HashMap<String, Any> = HashMap()
                            user.put("userType", userType)
                            user.put("fcmToken", activity.getApplication().getSharedPreferences("sprefs", Context.MODE_PRIVATE).getString("token", null))
                            db.collection("users")
                                    .document(task.result.user.uid)
                                    .set(user, SetOptions.merge())
                                    .addOnCompleteListener {
                                        router.startFlow(Screens.MAIN_FLOW)
                                    }
                                    .addOnFailureListener(OnFailureListener {
                                        Log.e("onComplete", it.message)
                                    })
                        }
                    })
        }

    }


    companion object {
        private val ARG_USER = "user_type"

        fun newInstance(userType: String): Fragment? {
            var signInFragment = SignInFragment()
            var args = Bundle()
            args.putString(ARG_USER, userType)
            signInFragment.arguments = args
            return signInFragment
        }
    }
}
