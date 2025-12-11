package id.ac.pnm.anterinapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2 // Import ViewPager2

import id.ac.pnm.anterinapp.R
import id.ac.pnm.anterinapp.adapter.BannerAdapter // Import Adapter
import id.ac.pnm.anterinapp.adapter.HistoryAdapter
import id.ac.pnm.anterinapp.model.CarouselData // Import Model
import id.ac.pnm.anterinapp.model.HistoryData

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vpBanner = view.findViewById<ViewPager2>(R.id.vpBanner)

        val bannerList = listOf(
            CarouselData(R.drawable.promo_1),
            CarouselData(R.drawable.promo_2),
            CarouselData(R.drawable.promo_3)
        )

        val bannerAdapter = BannerAdapter(bannerList)
        vpBanner.adapter = bannerAdapter

        val btnMenuJadwal = view.findViewById<CardView>(R.id.btnMenuJadwal)
        btnMenuJadwal?.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_schedule)
        }

        val btnMenuPesanan = view.findViewById<CardView>(R.id.btnMenuPesanan)
        btnMenuPesanan?.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_orderList)
        }

        val cardGabungan = view.findViewById<CardView>(R.id.cardGabungan)
        cardGabungan.setOnClickListener {
            try {
                findNavController().navigate(R.id.combinationFragment)
            } catch (e: Exception) {
                Toast.makeText(context, "Fitur belum tersedia", Toast.LENGTH_SHORT).show()
            }
        }

        val rvHistoryDashboard = view.findViewById<RecyclerView>(R.id.rvHistoryDashboard)

        val historyList = listOf(
            HistoryData("Ke Kampus", "10 November 2025", "Selesai"),
            HistoryData("Stasiun Solo Balapan", "10 November 2025", "Selesai"),
            HistoryData("Pasar Besar Madiun", "09 November 2025", "Dibatalkan"),
            HistoryData("Mall Madiun", "08 November 2025", "Selesai")
        )

        rvHistoryDashboard.layoutManager = LinearLayoutManager(context)

        val adapter = HistoryAdapter(historyList) { selectedItem ->
            val bundle = Bundle().apply {
                putString("ORDER_TITLE", selectedItem.title)
                putString("ORDER_DATE", selectedItem.date)
                putString("ORDER_STATUS", selectedItem.status)
            }
            try {
                findNavController().navigate(R.id.orderDetailFragment, bundle)
            } catch (e: Exception) {
                Toast.makeText(context, "Gagal membuka detail: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        rvHistoryDashboard.adapter = adapter

        fun goToDestination(transportName: String, transportIcon: Int, isPublicTransport: Boolean) {
            val bundle = Bundle().apply {
                putString("TRANS_NAME", transportName)
                putInt("TRANS_ICON", transportIcon)

                putBoolean("IS_STATION_BASED", isPublicTransport)
            }
            try {
                findNavController().navigate(R.id.destinationFragment, bundle)
            } catch (e: Exception) {
                Toast.makeText(context, "Navigasi gagal", Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<View>(R.id.cardMotor).setOnClickListener {
            goToDestination("Motor", R.drawable.motor_icon, false)
        }

        view.findViewById<View>(R.id.cardMobil).setOnClickListener {
            goToDestination("Mobil", R.drawable.car_icon, false)
        }

        view.findViewById<View>(R.id.cardKereta).setOnClickListener {
            goToDestination("Kereta", R.drawable.train_icon, true)
        }

        view.findViewById<View>(R.id.cardBus).setOnClickListener {
            goToDestination("Bus", R.drawable.bus_icon, true)
        }
    }
}