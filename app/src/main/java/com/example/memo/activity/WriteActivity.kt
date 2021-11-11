package com.example.memo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.memo.databinding.ActivityWriteBinding
import com.example.memo.model.Memo
import com.example.memo.viewmodel.MemoViewModel
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
            ivBack.setOnClickListener {
                var intent = Intent(this@WriteActivity, MainActivity::class.java)
                model.insert(Memo(0, etTitle.text.toString(), etContent.text.toString()))
                startActivity(intent)
                finish()
            }
        }
    }
}