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

        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val tvSelected = view.findViewById<TextView>(R.id.tvSelectedCombination)
        val rvCombinations = view.findViewById<RecyclerView>(R.id.rvCombinations)
        val btnConfirm = view.findViewById<View>(R.id.btnConfirm)

        val data = listOf(
            CombinationModel(1, "Motor", R.drawable.motor_icon, "Kereta", R.drawable.train_icon),
            CombinationModel(2, "Motor", R.drawable.motor_icon, "Mobil", R.drawable.car_icon),
            CombinationModel(3, "Motor", R.drawable.motor_icon, "Bus", R.drawable.bus_icon),
            CombinationModel(4, "Mobil", R.drawable.car_icon, "Kereta", R.drawable.train_icon)
        )

        val adapter = CombinationAdapter(data) { selectedItem ->
            tvSelected.text = "${selectedItem.transport1Name} + ${selectedItem.transport2Name}"
        }

        rvCombinations.layoutManager = LinearLayoutManager(context)
        rvCombinations.adapter = adapter

        btnConfirm.setOnClickListener {
            if (tvSelected.text == "-") {
                Toast.makeText(context, "Pilih kombinasi dulu!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Memesan: ${tvSelected.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}