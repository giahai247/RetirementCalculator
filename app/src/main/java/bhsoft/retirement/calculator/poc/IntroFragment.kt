package bhsoft.retirement.calculator.poc


import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 */
class IntroFragment : Fragment() {
    companion object {
        fun newInstance(message: String): IntroFragment {

            val f = IntroFragment()

            val bdl = Bundle(1)

            bdl.putString(EXTRA_MESSAGE, message)

            f.arguments = bdl

            return f

        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(
            R.layout.fragment_intro,
            container,
            false
        )

        val message = arguments!!.getString(EXTRA_MESSAGE)

        val textView: TextView = view.findViewById(R.id.tvShow)
        textView.text = message

        return view
    }


}