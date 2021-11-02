package com.example.myfirstmemoapp

import android.app.Application
import androidx.lifecycle.LiveData

class MemoRepository(application: Application) {
    private var memoDao: MemoDao
    private val memoList: LiveData<List<Memo>>

    init {
        var db = AppDatabase.getInstance(application)
        memoDao = db!!.memoDao()
        memoList = db.memoDao().getAll()
    }

    fun insert(memo: Memo) {
        memoDao.insert(memo)
    }

    fun delete(memo: Memo) {
        memoDao.delete(memo)
    }

    fun getAll(): LiveData<List<Memo>> {
        return memoDao.getAll()
    }
}