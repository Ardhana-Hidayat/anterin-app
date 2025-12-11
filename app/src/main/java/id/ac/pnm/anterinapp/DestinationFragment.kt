package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class DestinationFragment : Fragment() {

    private lateinit var etSearch: EditText

    // Variabel untuk Mode Single Transport
    private var isTransportSelected = false
    private var isStationBased = false
    private var transportName: String? = null
    private var transportIcon: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_destination, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. CEK DATA DARI HALAMAN SEBELUMNYA
        val args = arguments
        if (args != null && args.containsKey("TRANS_NAME")) {
            // Jika ada data transport (dari menu Motor/Mobil/dll)
            isTransportSelected = true
            transportName = args.getString("TRANS_NAME")
            transportIcon = args.getInt("TRANS_ICON")
            isStationBased = args.getBoolean("IS_STATION_BASED", false)
        }

        val cardIndicator = view.findViewById<CardView>(R.id.cardIndicator)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        etSearch = view.findViewById(R.id.etSearchLocation)

        cardIndicator?.visibility = View.GONE

        if (isTransportSelected) {
            if (isStationBased) {
                // jika pilih kereta/bus
                tvTitle.text = "Pilih Lokasi\nStasiun / Terminal"
                etSearch.hint = "Cari nama stasiun/terminal..."
            } else {
                // jika pilih motor/mobil
                tvTitle.text = "Tentukan Tujuan Kamu"
                etSearch.hint = "Cari alamat tujuan..."
            }
        } else {
            tvTitle.text = "Mau Kemana\nHari Ini?"
            etSearch.hint = "Cari lokasi tujuan..."
        }

        val btnSearch = view.findViewById<ImageView>(R.id.btnSearch)

        //set listener search input
        etSearch.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                (event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                val input = etSearch.text.toString()
                if (input.isNotEmpty()) {
                    Toast.makeText(context, "Lokasi dipilih: $input", Toast.LENGTH_SHORT).show()
                }
                true
            } else false
        }

        btnSearch?.setOnClickListener {
            val input = etSearch.text.toString()
            if (input.isNotEmpty()) {
                Toast.makeText(context, "Lokasi dipilih: $input", Toast.LENGTH_SHORT).show()
            }
        }

        val navUpListener = View.OnClickListener { findNavController().navigateUp() }
        view.findViewById<View>(R.id.btnBack).setOnClickListener(navUpListener)
        view.findViewById<View>(R.id.btnBackContainer).setOnClickListener(navUpListener)

        view.findViewById<View>(R.id.btnConfirmDest).setOnClickListener {
            val locationInput = etSearch.text.toString()
            val finalDest = if (locationInput.isEmpty()) "Lokasi di Peta" else locationInput

            val bundle = Bundle().apply {
                putString("DESTINATION_NAME", finalDest)
            }

            if (isTransportSelected) {
                bundle.putString("TRANS1_NAME", transportName)
                bundle.putInt("TRANS1_ICON", transportIcon)
                bundle.putBoolean("IS_STATION_BASED", isStationBased)

                findNavController().navigate(R.id.paymentFragment, bundle)
            } else {
                findNavController().navigate(R.id.transportSelectionFragment, bundle)
            }
        }
    }
}