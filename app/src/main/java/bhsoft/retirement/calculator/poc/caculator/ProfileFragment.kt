package bhsoft.retirement.calculator.poc.caculator


import android.os.Bundle
import android.provider.AlarmClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import bhsoft.retirement.calculator.poc.IntroFragment

import bhsoft.retirement.calculator.poc.R

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {
    companion object {
        fun newInstance(message: String): ProfileFragment {

            val f = ProfileFragment()

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
            R.layout.fragment_profile,
            container,
            false
        )
        val message = arguments!!.getString(AlarmClock.EXTRA_MESSAGE)
        val textView: TextView = view.findViewById(R.id.tvHello)
        textView.text = message
        return view
    }


}
