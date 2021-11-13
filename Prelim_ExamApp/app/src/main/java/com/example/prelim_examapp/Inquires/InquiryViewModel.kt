package com.example.prelim_examapp.Inquires

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.prelim_examapp.databse.LoginDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InquiryViewModel (application: Application):AndroidViewModel(application){

    private val readAllInq: LiveData<List<Inquiries>>
    private val repo:InqRepo

    init {
        val inquiryDao = LoginDatabase.getDatabase(application).inquiryDao()
        repo= InqRepo(inquiryDao)
        readAllInq = repo.readAllInq
    }
    fun addInq(inquiries: Inquiries){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addInq((inquiries))
        }
    }
}