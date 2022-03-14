package com.example.agecalculator

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DevActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dev)

        val message = """
            Hello there I am Aditya Kumar. Thank you for downloading my application.
            Feel free to visit my website DevConnect and check out my other projects.
            here is my link:- https://radiant-bastion-62859.herokuapp.com/profile/3947f970-07f0-4863-bdf6-0d247c0b2e82/
            """.trimIndent()
        var textView_info = findViewById<TextView>(R.id.textView_info)
        textView_info.setText(message)
    }
}