package bhsoft.retirement.calculator.poc.caculator


import android.os.Bundle
import android.os.Handler
import android.provider.AlarmClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import bhsoft.retirement.calculator.poc.R
import kotlinx.android.synthetic.main.fragment_caculator.*
import kotlinx.android.synthetic.main.fragment_saving_core.*


class CaculatorFragment : Fragment() {




    companion object {
        fun newInstance(message: String): bhsoft.retirement.calculator.poc.caculator.CaculatorFragment {

            val f = CaculatorFragment()

            val bdl = Bundle(1)

            bdl.putString(AlarmClock.EXTRA_MESSAGE, message)

            f.arguments = bdl

            return f

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_caculator,
            container,
            false
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lnOptional.setOnClickListener {
            if (framelayout.visibility == View.GONE) {
                framelayout.visibility = View.VISIBLE
            }
            else
                if (framelayout.visibility == View.VISIBLE)
                {framelayout.visibility = View.GONE }
        }

        alert.setOnClickListener {
           showdialog()
        }
        alert1.setOnClickListener {
            showdialog()
        }

    }
    fun showdialog() {

        val alertDialog = context?.let {
            AlertDialog.Builder(it)
                //set message
                .setMessage("Our calculator predicts your retirement nest egg in today’s dollars, then shows how it would stretch over the years you plan to spend in retirement, taking inflation into account.")
                //set positive button

                .show()
        }
    }

}
