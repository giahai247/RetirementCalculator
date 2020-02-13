package bhsoft.retirement.calculator.poc.caculator


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog

import bhsoft.retirement.calculator.poc.R
import kotlinx.android.synthetic.main.fragment_caculator.*
import kotlinx.android.synthetic.main.fragment_optional.*

/**
 * A simple [Fragment] subclass.
 */
class OptionalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_optional, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alert01.setOnClickListener {
            showdialog()
        }
        alert02.setOnClickListener {
            showdialog()
        }
        alert03.setOnClickListener {
            showdialog()
        }
        alert04.setOnClickListener {
            showdialog()
        }
        alert05.setOnClickListener {
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
