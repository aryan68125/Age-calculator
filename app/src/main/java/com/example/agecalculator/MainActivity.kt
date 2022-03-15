package com.example.agecalculator

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private var selected_date_of_birth : TextView? = null //private this textView only acessible on this activity only
    private var display_age_in_minutes : TextView?= null
    private var resultDate : String = ""
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //button to select the date from the calender
        var select_date_button = findViewById<Button>(R.id.select_date_button)
        selected_date_of_birth = findViewById<TextView>(R.id.selected_date_of_birth)
        selected_date_of_birth?.setText("") //clear the date before selecting the new date
        var calculate_button = findViewById<Button>(R.id.button)

        display_age_in_minutes = findViewById<TextView>(R.id.display_age_in_minutes)
        var display_age_in_hours = findViewById<TextView>(R.id.display_age_in_hours)
        var display_age_in_days = findViewById<TextView>(R.id.display_age_in_days)
        var display_age_in_years = findViewById<TextView>(R.id.display_age_in_years)

        //backend for selecting the date of birth from the calender after select date of birth button is pressed
        select_date_button.setOnClickListener(View.OnClickListener{
            view ->

            //logic to select the date from the calender
            // we are going to handle the datePicker logic in a seperate function named clickDatePicker
            clickDatePicker() //calling clickDatePicker function
        })

        //backend that will handle the age calculation after the calculate button is pressed
        calculate_button.setOnClickListener(View.OnClickListener{
            view ->
            //button click testing
            Log.i("calculate", "Calulate button clicked")

            //Logic to calculate age in minutes
            var minutes_format = "-> "+ resultDate + " minutes"
            display_age_in_minutes?.setText(minutes_format)

            //Logic to calculate age in hours
            var hours = resultDate.toDouble() /60 //converting minutes into hours
            var hours_format = "-> "+ hours + " hours"
            display_age_in_hours.setText(hours_format)

            //Logic to calculate age in days
            var days = hours / 24
            var days_roundoff = (days * 100.0).roundToInt() / 100.0
            var days_format = "-> "+ days_roundoff + " days"
            display_age_in_days.setText(days_format)

            //Logic to calculate age in years
            var years = days / 365
            var years_roundoff = (years * 100.0).roundToInt() / 100.0
            var years_format = "-> "+ years_roundoff + " years"
            display_age_in_years.setText(years_format)
        })
    }

    //Date Picker code
    //this function will handle the date picking feature in the application
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker()
    {
        //button click testing
        Log.i("select", "select date of birth button clicked")

       //Logic to pick a date from the calendar dialog box
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
            view, year,month,dayOfMonth ->
            //testing datepicker
            //This clock will only get executed if we actually selected a date from the calendar dialog box
            Log.i("DatePicker", "Selecting Date from the Calender")

            //Store the selected date in var selected_date_of_birth TextView
            //NOTE internally months are counted from 0
            var selected_date = "${dayOfMonth}/${month+1}/${year}"
            selected_date_of_birth?.setText(selected_date)

            //Calculate how much time has passed from the selected date up until now
            var sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)//SimpleDateFormat() allows us tyo define a pattern for our date to use
            var theDate = sdf.parse(selected_date) //creating a date object from the selected date and convert it into a format that we can use into the date object
            //Making sure that the code here is null safe so that our program doesn't crash
            theDate?.let{
                //finding out how much time has passed in minutes from the selected date
                var selectedDateInMinutes = theDate.time / 60000 //theDate.time will give us the time passed in milliseconds so we need to convert it to minutes
                //getting the current date
                var currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                var currentDateInMinutes = currentDate.time/60000
                //calculate the difference in minutes
                var differenceInMinutes = currentDateInMinutes - selectedDateInMinutes //how much time in minutes between those two time points
                resultDate = differenceInMinutes.toString()
            }
        }, year,month,day ).show()

      }

    //The function below will help create our custom menu in the ActionBar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu);
        return true
    }
    //the function below will help to handle the functionalities of our custom menu that we've just created in the ActionBar
    override fun onOptionsItemSelected(item: MenuItem) : Boolean
    {
        val id = item.itemId;
        if(id== R.id.dev_info)
        {
            //Menu option clicked testing
            Log.i("menu", "Menu item clicked")

            //open Developer info Activity using Intent
            val EXTRA_MESSAGE = ""
            val message = ""
            val intent = Intent(this, DevActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
            //adding cutom activity transition animation
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)

            return true
        }
        else
        {
            return true
        }
    }
}