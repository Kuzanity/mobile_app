package com.example.prelim_examapp.Inquires

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Inquiries(
    @PrimaryKey(autoGenerate = true) var id :Int,
    @NonNull @ColumnInfo (name="inquiries") var inquiry:String
)
