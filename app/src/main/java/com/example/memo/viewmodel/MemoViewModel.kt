package com.example.memo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.memo.model.Memo
import com.example.memo.repository.MemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = MemoRepository(application)
    private var items = repository.getAll()

    fun getAll(): LiveData<List<Memo>> {
        return items
    }

    fun insert(memo: Memo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(memo)
        }
    }

    fun update(memo: Memo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(memo)
        }
    }

    fun delete(memo: Memo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(memo)
        }
    }
}