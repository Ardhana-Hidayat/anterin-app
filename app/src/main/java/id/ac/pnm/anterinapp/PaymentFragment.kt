package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class PaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transportName = arguments?.getString("TRANS1_NAME") ?: "Layanan"
        val transportIcon = arguments?.getInt("TRANS1_ICON") ?: 0
        val isStationBased = arguments?.getBoolean("IS_STATION_BASED") ?: false
        val pickupLoc = arguments?.getString("PICKUP_LOCATION") ?: "Lokasi Terpilih"

        view.findViewById<TextView>(R.id.tvTrans1).text = transportName
        view.findViewById<ImageView>(R.id.ivTrans1).setImageResource(transportIcon)

        val tvRincian = view.findViewById<TextView>(R.id.tvCostDetailName)

        if (isStationBased) {
            tvRincian.text = "Tiket $transportName\n(Anda harus datang ke: $pickupLoc)"
        } else {
            tvRincian.text = "Layanan $transportName\n(Dijemput di: $pickupLoc)"
        }

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            findNavController().navigateUp()
        }

        view.findViewById<Button>(R.id.btnPay).setOnClickListener {
            Toast.makeText(context, "Pembayaran Berhasil!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.successFragment)
        }
    }
}