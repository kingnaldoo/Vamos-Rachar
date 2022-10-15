package com.example.vamosrachar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var etPrice: EditText
    lateinit var etPersons: EditText
    lateinit var result: TextView
    lateinit var shareButton: FloatingActionButton
    lateinit var soundButton:  FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPrice = findViewById(R.id.price)
        etPersons = findViewById(R.id.persons)
        result = findViewById(R.id.result)
        shareButton = findViewById(R.id.share)
        soundButton = findViewById(R.id.sound)

        etPrice.addTextChangedListener {
            if(!etPrice.text.isNullOrEmpty() && !etPersons.text.isNullOrEmpty()) {
                val priceValue = etPrice.text.toString().toDouble()
                val personsValue = etPersons.text.toString().toDouble()
                val calc = String.format("%.2f", priceValue/personsValue)
                result.text = "R$ $calc"
            }
        }

        etPersons.addTextChangedListener {
            if(!etPrice.text.isNullOrEmpty() && !etPersons.text.isNullOrEmpty()) {
                val priceValue = etPrice.text.toString().toDouble()
                val personsValue = etPersons.text.toString().toDouble()
                val calc = String.format("%.2f", priceValue/personsValue)
                result.text = "R$ $calc"
            }
        }

        fun share(msg: String) {
            var intent = Intent()
            intent.setAction(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, msg)
            intent.setType("text/plain")
            startActivity(intent)
        }

        shareButton!!.setOnClickListener{
            share(result.text.toString())
        }
    }

}