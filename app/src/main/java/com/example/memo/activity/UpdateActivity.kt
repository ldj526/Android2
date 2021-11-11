package com.example.memo.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
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
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }
            ivDelete.setOnClickListener {
                // Dialog 설정
                val builder = AlertDialog.Builder(this@UpdateActivity)
                builder.setMessage("삭제하시겠습니까?")
                    .setPositiveButton("확인",
                        DialogInterface.OnClickListener { dialog, id ->
                            model.delete(datas)
                            val intent = Intent(this@UpdateActivity, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                            finish()
                        })
                    .setNegativeButton("취소",
                        DialogInterface.OnClickListener { dialog, id ->
                            builder.show().dismiss()
                        })
                builder.show()
            }

        }
    }
}