package com.example.vamosrachar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var etPrice: EditText
    lateinit var etPersons: EditText
    lateinit var result: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPrice = findViewById(R.id.price)
        etPersons = findViewById(R.id.persons)
        result = findViewById(R.id.result)

        
    }

}