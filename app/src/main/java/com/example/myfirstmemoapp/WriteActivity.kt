package com.example.myfirstmemoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.myfirstmemoapp.databinding.ActivityWriteBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    private val model: MemoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnBack.setOnClickListener {
                finish()
            }
            btnSave.setOnClickListener {
                var intent = Intent(this@WriteActivity, MainActivity::class.java)
                lifecycleScope.launch(Dispatchers.IO){
                    model.insert(Memo(etTitle.text.toString(), etContent.text.toString()))
                }
                startActivity(intent)
            }
        }
    }
}