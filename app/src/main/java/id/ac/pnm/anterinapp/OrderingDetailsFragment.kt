package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.text.NumberFormat
import java.util.Locale

class OrderingDetailsFragment : Fragment(R.layout.fragment_ordering_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("ORDER_TITLE") ?: "-"
        val date = arguments?.getString("ORDER_DATE") ?: "-"
        val orderId = arguments?.getString("ORDER_ID") ?: "-"
        val price = arguments?.getInt("PRICE") ?: 0

        val tvSubHeader = view.findViewById<TextView>(R.id.tvSubHeader)
        tvSubHeader.text = title

        val tvOrder = view.findViewById<TextView>(R.id.tvOrder)
        tvOrder.text = title

        val tvDate = view.findViewById<TextView>(R.id.tvDate)
        tvDate.text = date

        val tvOrderId = view.findViewById<TextView>(R.id.tvOrderId)
        tvOrderId.text = orderId

        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        val localeID = Locale("id", "ID")

        val numberFormat = NumberFormat.getCurrencyInstance(localeID)

        tvPrice.text = numberFormat.format(price)

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}