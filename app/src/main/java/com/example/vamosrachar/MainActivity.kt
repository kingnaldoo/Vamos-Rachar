package com.example.vamosrachar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var etPrice: EditText
    lateinit var etPersons: EditText
    lateinit var result: TextView
    lateinit var shareButton: FloatingActionButton
    lateinit var soundButton:  FloatingActionButton
    var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPrice = findViewById(R.id.price)
        etPersons = findViewById(R.id.persons)
        result = findViewById(R.id.result)
        shareButton = findViewById(R.id.share)
        soundButton = findViewById(R.id.sound)
        tts = TextToSpeech(this, this)

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

        fun speakMsg(msg: String) {
            tts!!.speak(msg, TextToSpeech.QUEUE_FLUSH, null, "")
        }

        shareButton!!.setOnClickListener{
            share(result.text.toString())
        }

        soundButton!!.setOnClickListener {
            speakMsg(result.text.toString())
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {

            var result : Int? = null

            //Setting language of tts by device language
            result = if(Locale.getDefault().displayLanguage != "English"){
                tts!!.setLanguage(Locale("pt", "BR"))
            }else {
                tts!!.setLanguage(Locale.US)
            }


            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
                //Activating button after loading tts
                soundButton!!.isEnabled = true
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}