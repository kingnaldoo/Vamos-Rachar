package com.example.vamosrachar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    lateinit var etPrice: EditText
    lateinit var etPersons: EditText
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPrice = findViewById(R.id.price)
        etPersons = findViewById(R.id.persons)
        result = findViewById(R.id.result)

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
    }

}