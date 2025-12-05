package id.ac.pnm.anterinapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.pnm.anterinapp.adapter.CombinationAdapter
import id.ac.pnm.anterinapp.model.CombinationData

class CombinationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_combination, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        val tvSelected = view.findViewById<TextView>(R.id.tvSelectedCombination)
        val rvCombinations = view.findViewById<RecyclerView>(R.id.rvCombinations)
        val btnConfirm = view.findViewById<View>(R.id.btnConfirm)

        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val data = listOf(
            CombinationData(1, "Motor", R.drawable.motor_icon, "Kereta", R.drawable.train_icon),
            CombinationData(2, "Motor", R.drawable.motor_icon, "Mobil", R.drawable.car_icon),
            CombinationData(3, "Motor", R.drawable.motor_icon, "Bus", R.drawable.bus_icon),
            CombinationData(4, "Mobil", R.drawable.car_icon, "Kereta", R.drawable.train_icon)
        )

        var selectedCombination: CombinationData? = null

        val adapter = CombinationAdapter(data) { selectedItem ->
            selectedCombination = selectedItem
            tvSelected.text = "${selectedItem.transport1Name} + ${selectedItem.transport2Name}"
        }

        rvCombinations.layoutManager = LinearLayoutManager(context)
        rvCombinations.adapter = adapter

        btnConfirm.setOnClickListener {
            if (selectedCombination == null) {
                Toast.makeText(context, "Pilih kombinasi dulu!", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = Bundle().apply {
                    putString("TRANS1_NAME", selectedCombination!!.transport1Name)
                    putString("TRANS2_NAME", selectedCombination!!.transport2Name)
                    putInt("TRANS1_ICON", selectedCombination!!.transport1Icon)
                    putInt("TRANS2_ICON", selectedCombination!!.transport2Icon)
                }

                findNavController().navigate(R.id.destinationFragment, bundle)
            }
        }
    }
}