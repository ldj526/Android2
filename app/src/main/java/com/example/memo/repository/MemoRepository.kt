package com.example.memo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.memo.data.MemoDatabase
import com.example.memo.data.MemoDao
import com.example.memo.model.Memo

class MemoRepository(application: Application) {
    private var memoDao: MemoDao
    private val memoList: LiveData<List<Memo>>

    init {
        var db = MemoDatabase.getInstance(application)
        memoDao = db!!.memoDao()
        memoList = db.memoDao().getAll()
    }

    fun insert(memo: Memo) {
        memoDao.insert(memo)
    }

    fun delete(memo: Memo) {
        memoDao.delete(memo)
    }

    fun update(memo: Memo) {
        memoDao.update(memo)
    }

    fun getAll(): LiveData<List<Memo>> {
        return memoDao.getAll()
    }
}