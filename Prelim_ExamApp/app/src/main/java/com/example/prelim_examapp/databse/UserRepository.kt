package com.example.prelim_examapp.databse

import androidx.lifecycle.LiveData

class UserRepository(private val loginDao:LoginDao) {
    val readAllData= loginDao.readAllData()

    suspend fun addUser(user:User) {
        return loginDao.addUser(user)
    }
    suspend fun getUser(userName:String):User?{
        return loginDao.getUserName(userName)
    }
}
