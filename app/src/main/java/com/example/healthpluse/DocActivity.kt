package com.example.healthpluse

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class DocActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc)

        var ButtonLocate = findViewById<Button>(R.id.locate)
        var ButtonAppoint = findViewById<Button>(R.id.appointment)

        ButtonLocate.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW))
            intent.setData(Uri.parse("geo:23.437400,74.659008"))
            Intent.createChooser(intent,"Launch Maps")
            startActivity(intent)
        }

        ButtonAppoint.setOnClickListener {
            Toast.makeText(applicationContext, "Appointment request sent", Toast.LENGTH_LONG).show()
        }
    }
}