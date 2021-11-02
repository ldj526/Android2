package com.example.myfirstmemoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.myfirstmemoapp.databinding.ActivityWriteBinding
import kotlinx.coroutines.Dispatchers

class WriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    private val model: MemoViewModel by viewModels()
    lateinit var profileAdapter: MemoAdapater
    private val datas = mutableListOf<Memo>()

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
                intent.putExtra("title",etTitle.text.toString())
                intent.putExtra("content",etContent.text.toString())
                startActivity(intent)
            }
        }
    }
}