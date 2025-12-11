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

    lateinit var etSearch: EditText

    var isQuickMode = false
    var isStationBased = false

    var singleTransName: String? = null
    var singleTransIcon: Int = 0

    var trans1Name: String? = null
    var trans2Name: String? = null
    var trans1Icon: Int = 0
    var trans2Icon: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_destination, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        if (args != null) {
            if (args.containsKey("TRANS1_NAME")) {
                trans1Name = args.getString("TRANS1_NAME")
                trans2Name = args.getString("TRANS2_NAME")
                trans1Icon = args.getInt("TRANS1_ICON")
                trans2Icon = args.getInt("TRANS2_ICON")

            } else if (args.containsKey("TRANS_NAME")) {
                isQuickMode = true
                singleTransName = args.getString("TRANS_NAME")
                singleTransIcon = args.getInt("TRANS_ICON")
                isStationBased = args.getBoolean("IS_STATION_BASED", false)
            }
        }

        val cardIndicator = view.findViewById<CardView>(R.id.cardIndicator)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        etSearch = view.findViewById(R.id.etSearchLocation)

        cardIndicator?.visibility = View.GONE

        if (isQuickMode) {
            if (isStationBased) {
                tvTitle.text = "Pilih Lokasi\nStasiun / Terminal"
                etSearch.hint = "Cari nama stasiun/terminal..."
            } else {
                tvTitle.text = "Tentukan Titik\nPenjemputan"
                etSearch.hint = "Cari alamat jemput..."
            }

        } else if (trans1Name != null) {
            cardIndicator?.visibility = View.VISIBLE
            tvTitle.text = "Tentukan Lokasi\nJemput & Tujuan"

            view.findViewById<TextView>(R.id.tvTrans1).text = trans1Name
            view.findViewById<TextView>(R.id.tvTrans2).text = trans2Name
            view.findViewById<ImageView>(R.id.ivTrans1).setImageResource(trans1Icon)
            view.findViewById<ImageView>(R.id.ivTrans2).setImageResource(trans2Icon)

        } else {
            tvTitle.text = "Mau Kemana\nHari Ini?"
            etSearch.hint = "Cari lokasi tujuan..."
        }

        val btnSearch = view.findViewById<ImageView>(R.id.btnSearch)

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
                putString("PICKUP_LOCATION", finalDest)
            }

            if (isQuickMode) {
                bundle.putString("TRANS1_NAME", singleTransName)
                bundle.putInt("TRANS1_ICON", singleTransIcon)
                bundle.putBoolean("IS_STATION_BASED", isStationBased)

                findNavController().navigate(R.id.paymentFragment, bundle)

            } else if (trans1Name != null) {
                bundle.putString("TRANS1_NAME", trans1Name)
                bundle.putInt("TRANS1_ICON", trans1Icon)

                findNavController().navigate(R.id.paymentFragment, bundle)

            } else {
                findNavController().navigate(R.id.transportSelectionFragment, bundle)
            }
        }
    }
}