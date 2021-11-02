package com.example.myfirstmemoapp

import androidx.lifecycle.LiveData
import androidx.room.*

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