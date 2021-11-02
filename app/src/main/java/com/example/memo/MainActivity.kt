package com.example.memo

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val model: MemoViewModel by viewModels()
    private lateinit var adapater: MemoAdapater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        model.getAll().observe(this, Observer {
            adapater.setList(it)
            adapater.notifyDataSetChanged()     // 데이터를 신규 추가 or 삭제 시 adapter에게 값을 알려줌
        })

        binding.fab.setOnClickListener {    // WriteActivity로 이동
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        adapater = MemoAdapater(this)   // Adapter 생성
        binding.recyclerView.adapter = adapater     // 화면의 RecyclerView와 연결
        binding.recyclerView.layoutManager =
            GridLayoutManager(applicationContext, 3, GridLayoutManager.VERTICAL, true)
    }
}