package bhsoft.retirement.calculator.poc.caculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bhsoft.retirement.calculator.poc.R
import bhsoft.retirement.calculator.poc.adapter.ViewPageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private  var name :String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager()
//        btnLogOut.setOnClickListener {
//            val intenLogout = Intent(this@MainActivity,WelcomeActivity::class.java)
//            startActivity(intenLogout)
//        }
    }
    private fun setupViewPager() {
        val adapter = ViewPageAdapter(
            supportFragmentManager
        )
        //val intent = intent
        val email = intent.getStringExtra("EMAIL")
        val firstFragmet: ProfileFragment = ProfileFragment.newInstance(email)
        val secondFragmet: CaculatorFragment = CaculatorFragment.newInstance("Caculator")

//        adapter.addFragment(firstFragmet, "Profile")
        adapter.addFragment(secondFragmet, "Caculator")
//        tab.setupWithViewPager(viewPager)
        //pageIndicatorView.setViewPager(viewpager)
        viewPager.adapter = adapter
}
}
