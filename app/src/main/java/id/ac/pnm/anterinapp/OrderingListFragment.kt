package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrderingListFragment : Fragment(R.layout.fragment_ordering_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val dataOrders = listOf(
            OrderData("Ojek - Ke Kampus", "9 November 2025", "OJK-001"),
            OrderData("Ojek - Ke Kampus", "8 November 2025", "OJK-002"),
            OrderData("Mobil - Ke Stasiun", "7 November 2025", "CAR-003")
        )

        val rvOrder = view.findViewById<RecyclerView>(R.id.rvOrder)
        rvOrder.layoutManager = LinearLayoutManager(context)

        val adapter = OrderAdapter(dataOrders) { orderTerpilih ->

            findNavController().navigate(R.id.action_daftarPesanan_to_detailPesanan)
        }

        rvOrder.adapter = adapter
    }
}