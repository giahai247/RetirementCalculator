package bhsoft.retirement.calculator.poc.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import bhsoft.retirement.calculator.poc.R
import bhsoft.retirement.calculator.poc.WelcomeActivity
import bhsoft.retirement.calculator.poc.database.DatabaseHelper
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {
    private lateinit var databaseHelper : DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        databaseHelper = DatabaseHelper(this@SignUpActivity)
        updateUI()
        tvBack1.setOnClickListener {
            onBackPressed()
        }
    }
    private fun updateUI(){

        edtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                btnCreate.isEnabled = true
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btnCreate.isEnabled = true
            }
        })
        btnCreate.setOnClickListener {
            val email :String = edtEmail.text.toString().trim()
            val password :String = edtPassword.text.toString().trim()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this@SignUpActivity,"Don't let anything empty",Toast.LENGTH_LONG).show()
            }
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ) {
                Toast.makeText(this@SignUpActivity,"Wrong Email",Toast.LENGTH_LONG).show()
            }
            else if(databaseHelper.checkUser(email)) {
                Toast.makeText(this@SignUpActivity, "Email has already exist", Toast.LENGTH_SHORT).show()
            }
            else if(password.length<8) {
                Toast.makeText(this@SignUpActivity,"Password wrong",Toast.LENGTH_LONG).show()
            }
            else {
           // Toast.makeText(this@SignUpActivity,"$email and $password",Toast.LENGTH_LONG).show()
            val intent = Intent(this@SignUpActivity,
                SecurityActivity::class.java)
            intent.putExtra("email",email)
            intent.putExtra("pass",password)
            startActivity(intent)
            }
        }

    }
}


