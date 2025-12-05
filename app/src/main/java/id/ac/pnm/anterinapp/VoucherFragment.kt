package id.ac.pnm.anterinapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class VoucherFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VoucherFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}