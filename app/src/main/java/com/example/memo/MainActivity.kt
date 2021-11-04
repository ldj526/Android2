package com.example.memo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memo.databinding.ActivityMainBinding
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
        }
        val customDecoration = CustomDecoration(10f, 10f, Color.BLUE)
    }


    private fun initRecyclerView() {
        adapter = MemoAdapater(this)   // Adapter 생성
        with(binding) {
            recyclerView.adapter = adapter     // 화면의 RecyclerView와 연결
            recyclerView.layoutManager =
                GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
            val customDecoration = CustomDecoration(3f, 3f, Color.BLACK)
            recyclerView.addItemDecoration(customDecoration)
        }
    }
}