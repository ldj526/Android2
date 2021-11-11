package com.example.memo.activity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.memo.databinding.ActivityWriteBinding
import com.example.memo.model.Memo
import com.example.memo.viewmodel.MemoViewModel
import kotlinx.android.synthetic.main.activity_update.*

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
                intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }

        }
    }
}