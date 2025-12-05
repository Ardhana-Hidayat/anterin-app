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

        val trans1Name = arguments?.getString("TRANS1_NAME") ?: "Motor"
        val trans2Name = arguments?.getString("TRANS2_NAME") ?: "Kereta"
        val trans1Icon = arguments?.getInt("TRANS1_ICON") ?: R.drawable.motor_icon
        val trans2Icon = arguments?.getInt("TRANS2_ICON") ?: R.drawable.train_icon

        view.findViewById<TextView>(R.id.tvTrans1).text = trans1Name
        view.findViewById<TextView>(R.id.tvTrans2).text = trans2Name
        view.findViewById<ImageView>(R.id.ivTrans1).setImageResource(trans1Icon)
        view.findViewById<ImageView>(R.id.ivTrans2).setImageResource(trans2Icon)

        view.findViewById<TextView>(R.id.tvCostDetailName).text = "$trans1Name + $trans2Name"

        view.findViewById<View>(R.id.btnBack).setOnClickListener {
            findNavController().navigateUp()
        }

        view.findViewById<Button>(R.id.btnPay).setOnClickListener {
            Toast.makeText(context, "Pembayaran Berhasil!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.successFragment)
        }
    }
}