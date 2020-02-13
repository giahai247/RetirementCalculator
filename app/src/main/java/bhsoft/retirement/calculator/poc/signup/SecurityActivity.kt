package bhsoft.retirement.calculator.poc.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import bhsoft.retirement.calculator.poc.R
import kotlinx.android.synthetic.main.activity_security.*
import kotlinx.android.synthetic.main.activity_security.btnContinue

class SecurityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security)
        setupSpinner()
        updateUI()
        tvBack.setOnClickListener {
            onBackPressed()
        }

    }
    private fun setupSpinner(){
        val question = resources.getStringArray(R.array.question)
        // access the spinner


        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, question)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    tvSpin.text = question[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                    tvSpin.text= question[0]
                }
            }

        }
    }
    private fun updateUI(){

        edtAnswer.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                btnContinue.isEnabled = true
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btnContinue.isEnabled = false
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                btnContinue.isEnabled = true
            }
        })
        btnContinue.setOnClickListener {
            val email = intent.getStringExtra("email")
            val password = intent.getStringExtra("pass")
            val quesstions :String = tvSpin.text.toString().trim()
            val answer :String = edtAnswer.text.toString().trim()
          //  Toast.makeText(this@SecurityActivity,"$quesstions and $answer and $email $password",Toast.LENGTH_LONG).show()
            val intentnext = Intent(this@SecurityActivity,
                NameActivity::class.java)
            intentnext.putExtra("quesstion",quesstions)
            intentnext.putExtra("answer",answer)
            intentnext.putExtra("email",email)
            intentnext.putExtra("password",password)
            startActivity(intentnext)
        }


    }
}
