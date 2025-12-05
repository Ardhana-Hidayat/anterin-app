package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class OrderingListFragment : Fragment(R.layout.fragment_ordering_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)

        btnBack.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }
}