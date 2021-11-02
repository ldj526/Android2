package com.example.myfirstmemoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MemoViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = MemoRepository(application)
    private var items = repository.getAll()

    fun getAll(): LiveData<List<Memo>> {
        return items
    }

    fun insert(memo: Memo) {
        repository.insert(memo)
    }

    fun delete(memo: Memo) {
        repository.delete(memo)
    }
}