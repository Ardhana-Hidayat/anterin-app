package id.ac.pnm.anterinapp.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.ac.pnm.anterinapp.R

class DetailHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("ORDER_TITLE") ?: "-"
        val date = arguments?.getString("ORDER_DATE") ?: "-"
        val status = arguments?.getString("ORDER_STATUS") ?: "-"

        val tvTitle = view.findViewById<TextView>(R.id.tvDetailTitle)
        val tvDate = view.findViewById<TextView>(R.id.tvDetailDate)
        val tvStatus = view.findViewById<TextView>(R.id.tvDetailStatus)

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)

        tvTitle.text = title
        tvDate.text = date
        tvStatus.text = status

        when (status) {
            "Selesai" -> {
                tvStatus.setTextColor(Color.parseColor("#4CAF50"))
            }
            "Dibatalkan" -> {
                tvStatus.setTextColor(Color.parseColor("#F44336"))
            }
            "Dalam Proses" -> {
                tvStatus.setTextColor(Color.parseColor("#FF9800"))
            }
        }

        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}