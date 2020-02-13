package bhsoft.retirement.calculator.poc.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import bhsoft.retirement.calculator.poc.R
import bhsoft.retirement.calculator.poc.database.DatabaseHelper
import bhsoft.retirement.calculator.poc.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_name.*

class NameActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)
        btnContinue.setOnClickListener {
            val firstname: String = edtFirstname.text.toString().trim()
            val lastname: String = edtLastName.text.toString().trim()
            val intent = intent

            val email = intent.getStringExtra("email")
            val password = intent.getStringExtra("password")
            val quesstion = intent.getStringExtra("quesstion")
            val answer = intent.getStringExtra("answer")
            Toast.makeText(
                this@NameActivity,
                "$email $password $quesstion $answer",
                Toast.LENGTH_SHORT
            ).show()
            databaseHelper = DatabaseHelper(this)
            if (email != null && password != null && quesstion != null && answer != null && !databaseHelper.checkUser(
                    email
                )
            ) {
                databaseHelper.addUserDetail(
                    email,
                    password,
                    quesstion,
                    answer,
                    firstname,
                    lastname
                )

                Toast.makeText(this@NameActivity, "Stored Successfully!", Toast.LENGTH_SHORT).show()

                val intentSign = Intent(this@NameActivity, SignInActivity::class.java)
                val fullname = "$firstname $lastname"
                intentSign.putExtra("fullname",fullname)
                startActivity(intentSign)

            } else {
                Toast.makeText(this@NameActivity, "Email has already exist", Toast.LENGTH_SHORT)
                    .show()
                val signinIntent = Intent(this@NameActivity, SignUpActivity::class.java)
                startActivity(signinIntent)
            }
        }
    }
}
