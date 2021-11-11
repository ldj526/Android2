package com.example.memo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.memo.data.MemoDatabase
import com.example.memo.databinding.ActivityUpdateBinding
import com.example.memo.databinding.ActivityWriteBinding
import com.example.memo.model.Memo
import com.example.memo.viewmodel.MemoViewModel
import kotlinx.android.synthetic.main.item_memo.*

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private val model: MemoViewModel by viewModels()
    lateinit var datas: Memo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        datas = intent.getParcelableExtra("data")!!

        with(binding) {
            updateTitle.setText(datas.title)
            updateContent.setText(datas.content)
            updateBack.setOnClickListener {
                var intent = Intent(this@UpdateActivity, MainActivity::class.java)
                model.update(
                    Memo(
                        datas._id,
                        updateTitle.text.toString(),
                        updateContent.text.toString()
                    )
                )
                startActivity(intent)
                finish()
            }
        }
    }
}