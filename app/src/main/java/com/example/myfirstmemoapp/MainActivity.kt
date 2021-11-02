package com.example.myfirstmemoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstmemoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val model: MemoViewModel by viewModels()
    private lateinit var adapater: MemoAdapater
    val datas = mutableListOf<Memo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.fab.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        adapater = MemoAdapater(this)   // Adapter 생성
        binding.recyclerView.adapter = adapater     // 화면의 RecyclerView와 연결
        binding.recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)

        datas.add(Memo(intent.getStringExtra("title"),intent.getStringExtra("content")))

        adapater.datas = datas
        adapater.notifyDataSetChanged()     // 데이터를 신규 추가 or 삭제 시 adapter에게 값을 알려줌
    }
}