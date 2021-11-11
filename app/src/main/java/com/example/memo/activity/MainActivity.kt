package com.example.memo.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memo.data.MemoDatabase
import com.example.memo.databinding.ActivityMainBinding
import com.example.memo.model.Memo
import com.example.memo.viewmodel.MemoViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val model: MemoViewModel by viewModels()
    private lateinit var adapter: MemoAdapater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        model.getAll().observe(this, Observer {
            Collections.reverse(it)     // gridLayout에서 최근목록을 가장 위로 해주기 위함
            adapter.setList(it)
            adapter.notifyDataSetChanged()     // 데이터를 신규 추가 or 삭제 시 adapter에게 값을 알려줌
        })

        binding.fab.setOnClickListener {    // WriteActivity로 이동
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun initRecyclerView() {
        adapter = MemoAdapater(this)   // Adapter 생성
        adapter.setItemClickListener(object : MemoAdapater.OnItemClickListener {
            override fun onClick(v: View, data: Memo, position: Int) {
                val intent = Intent(this@MainActivity, UpdateActivity::class.java).apply {
                    putExtra("data", data)
                    putExtra("position", position)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run {
                    startActivity(this)
                }
            }
        })
        with(binding) {
            recyclerView.adapter = adapter     // 화면의 RecyclerView와 연결
            recyclerView.layoutManager =
                GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
        }
    }
}