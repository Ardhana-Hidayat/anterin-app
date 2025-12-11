package id.ac.pnm.anterinapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.pnm.anterinapp.R
import id.ac.pnm.anterinapp.adapter.TransportAdapter
import id.ac.pnm.anterinapp.model.TransportData

class TransportSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transport_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val destName = arguments?.getString("DESTINATION_NAME") ?: "Lokasi Pilihan"
        view.findViewById<TextView>(R.id.tvDestinationInfo).text = "Menuju: $destName"

        val data = listOf(
            TransportData(1, "Motor", R.drawable.motor_icon),
            TransportData(2, "Mobil", R.drawable.car_icon),
        )

        val rvOptions = view.findViewById<RecyclerView>(R.id.rvTransportOptions)
        rvOptions.layoutManager = LinearLayoutManager(context)

        val adapter = TransportAdapter(data) { selectedItem ->
            val bundle = Bundle().apply {
                putString("TRANS1_NAME", selectedItem.transportName)
                putInt("TRANS1_ICON", selectedItem.transportIcon)
                putString("DESTINATION_NAME", destName)
            }
            findNavController().navigate(R.id.paymentFragment, bundle)
        }
        rvOptions.adapter = adapter

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            findNavController().navigateUp()
        }
    }
}