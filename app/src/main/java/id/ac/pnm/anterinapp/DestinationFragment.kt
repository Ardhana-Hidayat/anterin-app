package id.ac.pnm.anterinapp

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.preference.PreferenceManager
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import java.util.Locale

class DestinationFragment : Fragment() {

    private lateinit var mapView: MapView
    private lateinit var etSearch: EditText

    private var isComboMode = false
    private var isSingleMode = false

    private var trans1Name: String? = null
    private var trans2Name: String? = null
    private var trans1Icon: Int = 0
    private var trans2Icon: Int = 0

    private var singleTransName: String? = null
    private var singleTransIcon: Int = 0
    private var isStationBased: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Configuration.getInstance().load(
            context,
            PreferenceManager.getDefaultSharedPreferences(context)
        )
        Configuration.getInstance().userAgentValue = requireContext().packageName
        return inflater.inflate(R.layout.fragment_destination, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = view.findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)

        val startPoint = GeoPoint(-7.6298, 111.5176)
        mapView.controller.setZoom(15.0)
        mapView.controller.setCenter(startPoint)

        val args = arguments
        if (args != null) {
            if (args.containsKey("TRANS1_NAME")) {
                isComboMode = true
                trans1Name = args.getString("TRANS1_NAME")
                trans2Name = args.getString("TRANS2_NAME")
                trans1Icon = args.getInt("TRANS1_ICON")
                trans2Icon = args.getInt("TRANS2_ICON")
            } else if (args.containsKey("TRANS_NAME")) {
                isSingleMode = true
                singleTransName = args.getString("TRANS_NAME")
                singleTransIcon = args.getInt("TRANS_ICON")
                isStationBased = args.getBoolean("IS_STATION_BASED", false)
            }
        }

        val cardIndicator = view.findViewById<CardView>(R.id.cardIndicator)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        etSearch = view.findViewById(R.id.etSearchLocation)

        if (isComboMode) {
            cardIndicator.visibility = View.VISIBLE
            tvTitle.text = "Tentukan Lokasi\nJemput & Tujuan"

            view.findViewById<TextView>(R.id.tvTrans1).text = trans1Name
            view.findViewById<TextView>(R.id.tvTrans2).text = trans2Name
            view.findViewById<ImageView>(R.id.ivTrans1).setImageResource(trans1Icon)
            view.findViewById<ImageView>(R.id.ivTrans2).setImageResource(trans2Icon)

        } else if (isSingleMode) {
            cardIndicator.visibility = View.GONE

            if (isStationBased) {
                tvTitle.text = "Pilih Lokasi\nStasiun / Terminal"
                etSearch.hint = "Cari nama stasiun (misal: Madiun)..."
            } else {
                tvTitle.text = "Tentukan Titik\nPenjemputan"
                etSearch.hint = "Cari alamat jemput..."
            }

        } else {
            cardIndicator.visibility = View.GONE
            tvTitle.text = "Mau Kemana\nHari Ini?"
            etSearch.hint = "Cari lokasi tujuan..."
        }

        val btnSearch = view.findViewById<ImageView>(R.id.btnSearch)

        etSearch.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                (event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                searchLocation(etSearch.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }

        btnSearch?.setOnClickListener {
            searchLocation(etSearch.text.toString())
        }

        val navUpListener = View.OnClickListener { findNavController().navigateUp() }
        view.findViewById<View>(R.id.btnBack).setOnClickListener(navUpListener)
        view.findViewById<View>(R.id.btnBackContainer).setOnClickListener(navUpListener)

        view.findViewById<View>(R.id.btnConfirmDest).setOnClickListener {
            val locationName = etSearch.text.toString()
            val finalDest = if (locationName.isEmpty()) "Pin Lokasi Map" else locationName

            val bundle = Bundle().apply {
                putString("DESTINATION_NAME", finalDest)
            }

            if (isComboMode) {
                bundle.putString("TRANS1_NAME", trans1Name)
                bundle.putString("TRANS2_NAME", trans2Name)
                bundle.putInt("TRANS1_ICON", trans1Icon)
                bundle.putInt("TRANS2_ICON", trans2Icon)
                findNavController().navigate(R.id.paymentFragment, bundle)

            } else if (isSingleMode) {
                bundle.putString("TRANS1_NAME", singleTransName)
                bundle.putInt("TRANS1_ICON", singleTransIcon)
                bundle.putBoolean("IS_STATION_BASED", isStationBased)
                bundle.putString("PICKUP_LOCATION", finalDest)
                findNavController().navigate(R.id.paymentFragment, bundle)

            } else {
                findNavController().navigate(R.id.transportSelectionFragment, bundle)
            }
        }
    }

    private fun searchLocation(locationName: String) {
        if (locationName.isEmpty()) {
            Toast.makeText(context, "Masukkan nama lokasi", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val geocoder = Geocoder(requireContext(), Locale.getDefault())
                val listAddresses: List<Address>? = geocoder.getFromLocationName(locationName, 1)

                if (!listAddresses.isNullOrEmpty()) {
                    val address = listAddresses[0]
                    val lat = address.latitude
                    val lon = address.longitude

                    withContext(Dispatchers.Main) {
                        val newPoint = GeoPoint(lat, lon)
                        mapView.controller.animateTo(newPoint)
                        mapView.controller.setZoom(15.0)
                        Toast.makeText(context, "Menuju: ${address.featureName ?: locationName}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Lokasi tidak ditemukan", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Gagal memuat lokasi: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}