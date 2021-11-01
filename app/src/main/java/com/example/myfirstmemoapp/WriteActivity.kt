package com.example.myfirstmemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myfirstmemoapp.databinding.ActivityWriteBinding

class WriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnBack.setOnClickListener(object: View.OnClickListener {
                override fun onClick(v: View?) {
                    finish()
                }
            })
            btnSave.setOnClickListener(object:View.OnClickListener {
                override fun onClick(v: View?) {

                }
            })
        }
    }
}