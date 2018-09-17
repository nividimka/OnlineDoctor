package dkgroup.kz.onlinedoctor.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dkgroup.kz.onlinedoctor.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*


class SignUpActivity : AppCompatActivity() {

    internal lateinit var auth: FirebaseAuth
    internal lateinit var database: DatabaseReference
    internal lateinit var userType: String
    internal lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        userType = intent.getStringExtra(ARG_USER)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        database = FirebaseDatabase.getInstance().reference
        signUpButton.setOnClickListener {
            auth.createUserWithEmailAndPassword(emailInput!!.text.toString(), passwordInput!!.text.toString())
                    .addOnCompleteListener(this@SignUpActivity) { task ->
                        Toast.makeText(this@SignUpActivity, "createUserWithEmail:onComplete:" + task.isSuccessful, Toast.LENGTH_SHORT).show()
                        if (!task.isSuccessful) {
                            Toast.makeText(this@SignUpActivity, "Authentication failed." + task.exception!!,
                                    Toast.LENGTH_SHORT).show()
                        } else {
                            val user: HashMap<String, Any> = HashMap()
                            user.put("firstName", firstNameInput.text.toString())
                            user.put("lastName", lastNameInput.text.toString())
                            user.put("email", task.result.user.email!!)
                            user.put("userType", userType)
                            db.collection("users")
                                    .document(task.result.user.uid)
                                    .set(user)
                                    .addOnCompleteListener {
                                        Toast.makeText(this@SignUpActivity, "createUserWithEmail:onComplete", Toast.LENGTH_SHORT).show()
                                    }
                                    .addOnFailureListener(OnFailureListener {
                                        Toast.makeText(this@SignUpActivity, "Authentication failed." + it,
                                                Toast.LENGTH_SHORT).show()
                                    });

                            startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
                            finish()
                        }
                    }
        }
        signInButton.setOnClickListener {
            //            startActivity(SignInActivity.getIntent(userType, this))
//            finish()
        }
    }

    companion object {
        private val ARG_USER = "user_type"
        fun getIntent(userType: String, context: Context): Intent {
            val intent = Intent(context, SignUpActivity::class.java)
            intent.putExtra(ARG_USER, userType)
            return intent;
        }
    }
}
