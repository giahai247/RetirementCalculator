package bhsoft.retirement.calculator.poc.signin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bhsoft.retirement.calculator.poc.caculator.MainActivity
import bhsoft.retirement.calculator.poc.R
import bhsoft.retirement.calculator.poc.database.DatabaseHelper
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    private var prefs: SharedPreferences? = null
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        prefs =this.getSharedPreferences("save", 0)
        checkBox.isChecked= prefs!!.getBoolean("checked", false)
        if (checkBox.isChecked) {
            val user = prefs!!.getString("email", "")
            val password = prefs!!.getString("pass", "")
            editEmail.setText(user)
            editPassword.setText(password)
        }
        else if ( !checkBox.isChecked) {
            editEmail.setText("")
            editPassword.setText("")
            deleteEditext()
            checkBox.isChecked= false}

        btnSignin.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val pass = editPassword.text.toString().trim()
            Toast.makeText(this@SignInActivity,"$email và $pass",Toast.LENGTH_SHORT).show()
            var isChecked = checkBox.isChecked
            if (isChecked){
                rememberUser(email,pass)
                login()
                Toast.makeText(this@SignInActivity,"Sign In Successfull!",Toast.LENGTH_SHORT).show()
            }
            else {
                deleteEditext()
                login()
                Toast.makeText(this@SignInActivity,"Sign In Successfull!",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun rememberUser(email: String, pass :String){
        val editor = prefs!!.edit()

        editor.putString("email", email)
        editor.putString("pass", pass)
        editor.putBoolean("checked",checkBox.isChecked)
        editor.apply()
    }
    private fun deleteEditext(){
        val editor = prefs!!.edit()
        editor.putString("email", "")
        editor.putString("pass", "")
        editor.putBoolean("checked",false)
        editor.apply()
    }
    private fun login(){
        databaseHelper = DatabaseHelper(this@SignInActivity)
        if (databaseHelper.checkUser(editEmail!!.text.toString().trim { it <= ' ' }, editPassword!!.text.toString().trim { it <= ' ' }) ){
            val accountsIntent = Intent(this@SignInActivity, MainActivity::class.java)
            accountsIntent.putExtra("EMAIL", editEmail!!.text.toString().trim { it <= ' ' })
            startActivity(accountsIntent)


        } else {
            Toast.makeText(this@SignInActivity, "Email hadn't signed up!",Toast.LENGTH_SHORT).show()
        }
    }
}
