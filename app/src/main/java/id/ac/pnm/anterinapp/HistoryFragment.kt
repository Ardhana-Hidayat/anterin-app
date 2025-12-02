package id.ac.pnm.anterinapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Data Dummy dengan Status
        val dataList = listOf(
            HistoryData("Ke Stasiun Madiun", "12 Nov 2025, 08:00", "Selesai"),
            HistoryData("Ke Kampus PNM", "11 Nov 2025, 07:30", "Dibatalkan"),
            HistoryData("Jalan Pahlawan", "10 Nov 2025, 19:00", "Selesai"),
            HistoryData("Pasar Besar", "09 Nov 2025, 10:15", "Selesai"),
            HistoryData("Alun-Alun Madiun", "08 Nov 2025, 16:45", "Dalam Proses"), // Contoh
            HistoryData("Sun City Mall", "05 Nov 2025, 13:20", "Dibatalkan"),
            HistoryData("Terminal Purbaya", "01 Nov 2025, 06:00", "Selesai")
        )

        val rvHistory = view.findViewById<RecyclerView>(R.id.rvHistory)
        rvHistory.layoutManager = LinearLayoutManager(context)

        val adapter = HistoryAdapter(dataList)
        rvHistory.adapter = adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}