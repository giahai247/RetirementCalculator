package bhsoft.retirement.calculator.poc.caculator


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import bhsoft.retirement.calculator.poc.R
import kotlinx.android.synthetic.main.fragment_saving_core.*

/**
 * A simple [Fragment] subclass.
 */
class SavingCoreFragment : Fragment() {
    private val progressBar: ProgressBar? = null

    private var progressStatus = 0

    private val handler: Handler = Handler()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saving_core, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pb.progress = 51

//        progress()

    }
    fun progress(){
        if (progressStatus == 100) {
            progressStatus = 0
        }
        Thread(Runnable {
            while (progressStatus < 100) { // Update the progress status
                progressStatus += 1
                // Try to sleep the thread for 20 milliseconds
                try {
                    Thread.sleep(20) //3 seconds
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                // Update the progress bar
                handler.post(Runnable {
                    progressBar?.setProgress(progressStatus)
                    // Show the progress on TextView
                    tv.setText("You're $progressStatus% to goal")

                })
            }
        }).start()
    }


}
