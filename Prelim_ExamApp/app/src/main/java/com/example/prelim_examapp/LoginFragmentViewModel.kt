package com.example.prelim_examapp

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import com.example.prelim_examapp.databse.UserRepository
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginFragmentViewModel (private val repository: UserRepository, application: Application) :
    AndroidViewModel(application),Observable{

        val users=repository.readAllData

    @Bindable
    val usersName=MutableLiveData<String>()
    val userPassword=MutableLiveData<String>()
    
    private val job= Job()
    private val scope= CoroutineScope(Dispatchers.Main + job)
    private val _toastError = MutableLiveData<Boolean>()
    private val _toastCorrect =MutableLiveData<Boolean>()
    
    val toastError:LiveData<Boolean>
        get() =_toastError
    val toastCorrect:LiveData<Boolean>
        get() = _toastCorrect
    fun logIn(){
        if(usersName.value == null || userPassword.value == null){
            _toastError.value=true
        }
        else{
            scope.launch { 
                val user = repository.getUser(userName=usersName.value!!)
                if (user != null){
                    if(user.userPass==userPassword.value){
                        userPassword.value==null
                        usersName.value == null
                        _toastCorrect.value=true
                    }
                }
            }
        }
    }
    fun done() {
        _toastCorrect.value = false
    }
    fun error(){
        _toastError.value=  false
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}