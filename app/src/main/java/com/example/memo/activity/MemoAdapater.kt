package com.example.memo.activity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memo.databinding.ItemMemoBinding
import com.example.memo.model.Memo

class MemoAdapater(private val context: Context) :
    RecyclerView.Adapter<MemoAdapater.MemoViewHolder>() {

    var datas = ArrayList<Memo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemoViewHolder { // 화면을 최초 로딩하여 만들어진 View가 없는 경우, xml파일을 inflate하여 ViewHolder를 생성한다.
        val binding =
            ItemMemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemoViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MemoViewHolder,
        position: Int
    ) { // 위의 onCreateViewHolder에서 만든 view와 실제 입력되는 각각의 데이터를 연결한다.
        holder.bind(datas[position])
        holder.itemView.setOnClickListener {    // 만든 itemClickListener를 연결
            itemClickListener.onClick(it, position)
        }
    }

    interface OnItemClickListener {     // Click interface 정의
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {      // ClickListener 등록 메서드
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener     // ClickListener 선언

    override fun getItemCount() = datas.size    // RecyclerView로 만들어지는 item의 총 개수를 반환한다.

    fun setList(memo: List<Memo>) {
        datas.clear()
        datas.addAll(memo)
    }

    inner class MemoViewHolder(private val binding: ItemMemoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Memo) {
            with(binding) {
                tvTitle.text = item.title
                tvContent.text = item.content
                cardView.setOnClickListener {
                    val intent = Intent(context, WriteActivity::class.java).apply {
                        putExtra("title", item.title.toString())
                        putExtra("content", item.content.toString())
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }.run {
                        context.startActivity(this)
                    }
                }
            }
        }
    }
}