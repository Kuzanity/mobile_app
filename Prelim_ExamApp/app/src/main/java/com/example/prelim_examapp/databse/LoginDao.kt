package com.example.prelim_examapp.databse

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user:User)
    @Query("SELECT * FROM user ORDER BY id")
    fun readAllData(): LiveData<List<User>>
    @Query("SELECT * FROM user WHERE user_name LIKE :userName")
    suspend fun getUserName(userName:String):User?
}