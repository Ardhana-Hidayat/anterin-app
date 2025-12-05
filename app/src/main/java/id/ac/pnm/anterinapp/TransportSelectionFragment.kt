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
import id.ac.pnm.anterinapp.adapter.CombinationAdapter
import id.ac.pnm.anterinapp.model.CombinationData

class TransportSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transport_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Ambil Data Lokasi dari Halaman Sebelumnya
        val destName = arguments?.getString("DESTINATION_NAME") ?: "Lokasi Pilihan"
        view.findViewById<TextView>(R.id.tvDestinationInfo).text = "Menuju: $destName"

        // 2. Data Dummy (Nanti bisa ditambah harga)
        val data = listOf(
            CombinationData(1, "Motor", R.drawable.motor_icon, "Kereta", R.drawable.train_icon),
            CombinationData(2, "Motor", R.drawable.motor_icon, "Bus", R.drawable.bus_icon),
            CombinationData(3, "Mobil", R.drawable.car_icon, "Kereta", R.drawable.train_icon)
        )

        // 3. Setup Adapter
        val rvOptions = view.findViewById<RecyclerView>(R.id.rvTransportOptions)
        rvOptions.layoutManager = LinearLayoutManager(context)

        val adapter = CombinationAdapter(data) { selectedItem ->
            val bundle = Bundle().apply {
                putString("TRANS1_NAME", selectedItem.transport1Name)
                putString("TRANS2_NAME", selectedItem.transport2Name)
                putInt("TRANS1_ICON", selectedItem.transport1Icon)
                putInt("TRANS2_ICON", selectedItem.transport2Icon)
                putString("DESTINATION_NAME", destName)
            }
            findNavController().navigate(R.id.paymentFragment, bundle)
        }
        rvOptions.adapter = adapter

        // Back Button
        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            findNavController().navigateUp()
        }
    }
}