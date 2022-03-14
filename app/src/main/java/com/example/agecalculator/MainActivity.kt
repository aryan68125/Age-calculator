package com.example.agecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var Enter_date_of_birth = findViewById<EditText>(R.id.Enter_date_of_birth)
        var calculate_button = findViewById<Button>(R.id.button)

        var display_age_in_minutes = findViewById<TextView>(R.id.display_age_in_minutes)
        var display_age_in_hours = findViewById<TextView>(R.id.display_age_in_hours)
        var display_age_in_days = findViewById<TextView>(R.id.display_age_in_days)
        var display_age_in_years = findViewById<TextView>(R.id.display_age_in_years)

        calculate_button.setOnClickListener(View.OnClickListener{
            view ->
            //button click testing
            Log.i("button", "Calulate button clicked")

            //Logic to calculate age in minutes

            //Logic to calculate age in hours

            //Logic to calculate age in days

            //Logic to calculate age in years
        })
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

            return true
        }
        else
        {
            return true
        }
    }
}