package com.example.prelim_examapp.databse

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var id:Int=0,
    @NonNull @ColumnInfo(name= "user_name") var userName:String,
    @NonNull @ColumnInfo(name="password") var userPass:String
)
