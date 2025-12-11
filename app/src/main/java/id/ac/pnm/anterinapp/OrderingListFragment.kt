package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.pnm.anterinapp.adapter.OrderAdapter
import id.ac.pnm.anterinapp.model.OrderData

class OrderingListFragment : Fragment(R.layout.fragment_ordering_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val dataOrders = listOf(
            OrderData("Motor - Ke Kampus", "9 November 2025", "OJK-001", 12000),
            OrderData("Mobil - Ke Stasiun", "7 November 2025", "CAR-003", 35000)
        )

        val rvOrder = view.findViewById<RecyclerView>(R.id.rvOrder)
        rvOrder.layoutManager = LinearLayoutManager(context)

        val adapter = OrderAdapter(dataOrders) { orderTerpilih ->
            val bundle = bundleOf(
                "ORDER_TITLE" to orderTerpilih.title,
                "ORDER_DATE" to orderTerpilih.date,
                "ORDER_ID" to orderTerpilih.orderId,
                "PRICE" to orderTerpilih.price
            )

            findNavController().navigate(R.id.action_orderList_to_orderDetail, bundle)
        }

        rvOrder.adapter = adapter
    }
}