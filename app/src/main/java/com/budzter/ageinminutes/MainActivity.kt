package com.budzter.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDate.setOnClickListener { view ->
            onClickDatePicker(view)
        }
    }

    fun onClickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener{ view, selectedYear, selectedMonth, selectedDayOfMonth ->

                val selectedDate = "${selectedMonth + 1}/$selectedDayOfMonth/$selectedYear"
                tvSelectedDate.setText(selectedDate)

                // parse into simple date format for easier date computation
                val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
                val tempDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = tempDate!!.time / 60000

                val tempCurrentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = tempCurrentDate!!.time / 60000

                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                tvCalculatedMinutes.setText(differenceInMinutes.toString())

            },
            year,
            month,
            dayOfMonth
        )
        
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}