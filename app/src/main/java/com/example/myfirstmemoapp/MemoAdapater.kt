package com.example.myfirstmemoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstmemoapp.databinding.ItemMemoBinding

class MemoAdapater(private val context: Context) :
    RecyclerView.Adapter<MemoAdapater.MemoViewHolder>() {

    var datas = ArrayList<Memo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder { // 화면을 최초 로딩하여 만들어진 View가 없는 경우, xml파일을 inflate하여 ViewHolder를 생성한다.
        val binding =
            ItemMemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoAdapater.MemoViewHolder, position: Int) { // 위의 onCreateViewHolder에서 만든 view와 실제 입력되는 각각의 데이터를 연결한다.
        holder.bind(datas[position])
    }

    override fun getItemCount() = datas.size    // RecyclerView로 만들어지는 item의 총 개수를 반환한다.

    fun setList(memo: List<Memo>){
        datas.clear()
        datas.addAll(memo)
    }

    inner class MemoViewHolder(private val binding: ItemMemoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Memo) {
            with(binding) {
                tvTitle.text = item.title
                tvContent.text = item.content
            }
        }
    }
}