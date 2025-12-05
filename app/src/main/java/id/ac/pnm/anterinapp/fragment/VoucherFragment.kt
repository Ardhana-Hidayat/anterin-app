package id.ac.pnm.anterinapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// --- IMPORT PENTING ---
import id.ac.pnm.anterinapp.R
import id.ac.pnm.anterinapp.adapter.VoucherAdapter
import id.ac.pnm.anterinapp.model.VoucherData

class VoucherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_voucher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList = listOf(
            VoucherData(
                "Diskon 20%",
                "Dapat digunakan untuk rute perjalanan khusus kota Madiun.",
                "Berlaku hingga 20 November 2025"
            ),
            VoucherData(
                "Potongan Rp 10.000",
                "Minimum transaksi Rp 50.000 untuk semua moda transportasi.",
                "Berlaku hingga 30 Desember 2025"
            ),
            VoucherData(
                "Gratis Ongkir Barang",
                "Khusus pengiriman barang dalam kota menggunakan Motor.",
                "Berlaku hingga 1 Januari 2026"
            ),
            VoucherData(
                "Diskon Kereta 50%",
                "Promo spesial tahun baru untuk perjalanan kereta api lokal.",
                "Berlaku hingga 5 Januari 2026"
            )
        )

        val rvVoucher = view.findViewById<RecyclerView>(R.id.rvVoucher)
        rvVoucher.layoutManager = LinearLayoutManager(context)

        val adapter = VoucherAdapter(dataList)
        rvVoucher.adapter = adapter
    }
}