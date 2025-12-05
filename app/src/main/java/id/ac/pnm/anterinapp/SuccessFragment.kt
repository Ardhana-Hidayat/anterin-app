package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SuccessFragment : Fragment(R.layout.fragment_success) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnFinish = view.findViewById<Button>(R.id.btnFinish)

        btnFinish.setOnClickListener {
            findNavController().navigate(R.id.action_success_to_schedule)
        }
    }
}