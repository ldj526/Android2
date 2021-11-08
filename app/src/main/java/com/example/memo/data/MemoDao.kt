package com.example.memo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.memo.model.Memo

@Dao
interface MemoDao {
    @Query("SELECT * FROM Memo")
    fun getAll(): LiveData<List<Memo>>

    @Insert
    fun insert(memo: Memo)

    @Update
    fun update(memo: Memo)

    @Delete
    fun delete(memo: Memo)
}