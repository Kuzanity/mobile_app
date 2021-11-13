package com.example.prelim_examapp.Inquires

import androidx.lifecycle.LiveData

class InqRepo(private val inquiryDao: InquiryDao) {
    val readAllInq: LiveData<List<Inquiries>> = inquiryDao.readAllInq()

    suspend fun addInq(inquiry:Inquiries){
        inquiryDao.addInq(inquiry)
    }
}