package com.example.memo.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "memo")
data class Memo(

    @PrimaryKey(autoGenerate = true) val _id: Int = 0,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "content") val content: String?
) : Parcelable
