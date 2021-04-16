package com.example.healthpluse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var TextViewMedi = findViewById<TextView>(R.id.medicinal)
        var TextViewEnt = findViewById<TextView>(R.id.ent)
        var TextViewOrtho = findViewById<TextView>(R.id.ortho)
        var TextViewCardio = findViewById<TextView>(R.id.cardiology)

        TextViewMedi.setOnClickListener {
            startActivity(Intent(this@HomeActivity, DocActivity::class.java))
        }
        TextViewEnt.setOnClickListener {
            startActivity(Intent(this@HomeActivity, DocActivity::class.java))
        }
        TextViewOrtho.setOnClickListener {
            startActivity(Intent(this@HomeActivity, DocActivity::class.java))
        }
        TextViewCardio.setOnClickListener {
            startActivity(Intent(this@HomeActivity, DocActivity::class.java))
        }
    }
}