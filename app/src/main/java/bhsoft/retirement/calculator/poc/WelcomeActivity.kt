package bhsoft.retirement.calculator.poc

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import bhsoft.retirement.calculator.poc.adapter.CustomPagerAdapter
import bhsoft.retirement.calculator.poc.adapter.ViewPageAdapter
import bhsoft.retirement.calculator.poc.databinding.ActivityWelcomeBinding
import bhsoft.retirement.calculator.poc.signin.SignInActivity
import bhsoft.retirement.calculator.poc.signup.SignUpActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {
    lateinit private var binding: ActivityWelcomeBinding
    private var isLastPage: Boolean = false
    private val images: IntArray = intArrayOf(
        R.drawable.ig1,
        R.drawable.ig3,
        R.drawable.ig2
    )
    private lateinit var viewpager: ViewPager
    private lateinit var tabs: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        initViews()
        setupViewPager()
        setupUI()

    }
    private fun setupUI(){
        btnSignup.setOnClickListener {
            val intentSignup = Intent(this@WelcomeActivity,
                SignUpActivity::class.java)
            startActivity(intentSignup)
        }
        tvSignin.setOnClickListener {
            val intentSignin = Intent(this@WelcomeActivity,
                SignInActivity::class.java)
            startActivity(intentSignin)
        }
    }
    private fun initViews(){
        viewpager = findViewById(R.id.view_pager)
    }
    private fun setupViewPager() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        binding.owner = this
        binding.viewPager.adapter = CustomPagerAdapter(this, images)
        binding.pageIndicatorView.setViewPager(binding.viewPager)


        binding.viewPager.addOnPageChangeListener(
            object : ViewPager.OnPageChangeListener {
                override fun onPageSelected(p0: Int) {
                    // no-op
                }

                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                    // no-op
                }

                @SuppressLint("SetTextI18n")
                override fun onPageScrollStateChanged(p0: Int) = when (p0) {
                    ViewPager.SCROLL_STATE_IDLE -> {
                        isLastPage = binding.viewPager.currentItem == images.size - 1
                    }
                    else -> {
                        // no-op
                    }
                }
            }
        )
    }


}

