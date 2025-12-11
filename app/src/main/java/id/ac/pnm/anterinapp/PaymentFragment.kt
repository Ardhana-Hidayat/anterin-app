package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
        val destLoc = arguments?.getString("DESTINATION_NAME") ?: "Tujuan Kamu"

        view.findViewById<TextView>(R.id.tvTrans1).text = transportName
        view.findViewById<ImageView>(R.id.ivTrans1).setImageResource(transportIcon)

        val tvTransport = view.findViewById<TextView>(R.id.tvTransport)
        val tvDest = view.findViewById<TextView>(R.id.tvDestinationLoc)

        tvDest.text = "Tujuan: $destLoc"
        tvTransport.text = transportName

        val calendar = Calendar.getInstance()
        val today = calendar.time

        val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
        val dateString = formatter.format(today)

        val tvDateNow = view.findViewById<TextView>(R.id.tvDate)
        tvDateNow.text = dateString

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            findNavController().navigateUp()
        }

        view.findViewById<Button>(R.id.btnPay).setOnClickListener {
            Toast.makeText(context, "Pembayaran Berhasil!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.successFragment)
        }
    }
}