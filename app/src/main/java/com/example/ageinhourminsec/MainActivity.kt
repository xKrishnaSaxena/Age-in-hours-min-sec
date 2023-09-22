package com.example.ageinhourminsec

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ageinhourminsec.ui.theme.AgeinhourminsecTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    lateinit var button: Button
    lateinit var answer: TextView
    lateinit var date:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        button=findViewById(R.id.button)
        answer=findViewById(R.id.textView2)
        date=findViewById(R.id.textView3)
        button.setOnClickListener {view->
            datepicker(view)

        }

        }

    fun datepicker(view:View) {
        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        val hour=myCalendar.get(Calendar.HOUR_OF_DAY)
        val min=myCalendar.get(Calendar.MINUTE)
        val sec=myCalendar.get(Calendar.SECOND)
        val dop= DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view,year ,month, day ->
            val selecteddate="$day/${month+1}/$year"
            date.setText(selecteddate)
            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val thedate=sdf.parse(selecteddate)
            val dateinmin=thedate!!.time/60000
            val dateinsec=dateinmin*60
            val dateinhours=dateinmin/60
            val currdate=sdf.parse(sdf.format(System.currentTimeMillis()))
            val currdatemin=currdate!!.time/60000
            val currdatesec=currdatemin*60
            val currdatehour=currdatemin/60


            val diffhour=currdatehour-dateinhours
            val diffmin=currdatemin-dateinmin
            val diffsec=currdatesec-dateinsec


            answer.setText("Hours = ${diffhour}\nMinutes = ${diffmin}\nSeconds = ${diffsec} ")

        },year,month,day)

        dop.datePicker.setMaxDate(Date().time-86400000)
        dop.show()
    }
}

