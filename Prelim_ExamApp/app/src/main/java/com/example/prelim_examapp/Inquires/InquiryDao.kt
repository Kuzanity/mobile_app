package com.example.prelim_examapp.Inquires

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface InquiryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addInq(inquiry:Inquiries)

    @Query("SELECT * FROM inquiries ORDER BY id ASC")
    fun readAllInq():LiveData<List<Inquiries>>
}