package id.ac.pnm.anterinapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddTripFragment : Fragment(R.layout.fragment_add_trip) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<ImageView>(R.id.btnBack)
        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val etDate = view.findViewById<EditText>(R.id.etDate)

        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        btnSave.setOnClickListener {
            findNavController().navigate(R.id.scheduleFragment)
        }

        val myCalendar = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd MMMM yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale("id", "ID"))

            etDate.setText(sdf.format(myCalendar.time))
        }

        etDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                android.R.style.Theme_DeviceDefault_Light_Dialog,
                dateSetListener,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
}