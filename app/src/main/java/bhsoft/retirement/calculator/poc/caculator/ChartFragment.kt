package bhsoft.retirement.calculator.poc.caculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size

import bhsoft.retirement.calculator.poc.R
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_chart.*

class ChartFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_chart,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noOfEmp = ArrayList<BarEntry>()
        noOfEmp.add(BarEntry(0.95f, 0))
        noOfEmp.add(BarEntry(1.73f, 1))
        val year = ArrayList<String>()
        year.add("You will have about")
        year.add("You will need about")

        val bardataset = BarDataSet(noOfEmp, "Millions")
        barchart.animateY(5000)
        val data = BarData(year, bardataset)
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS)
        barchart.setDrawValueAboveBar(true)

        barchart.data = data
    }
}
