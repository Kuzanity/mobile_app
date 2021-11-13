package com.example.prelim_examapp

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import com.example.prelim_examapp.databse.UserRepository
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.prelim_examapp.databse.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository, application: Application):
    AndroidViewModel(application),Observable {
    private var data: String? = null
    var userDetails = MutableLiveData<Array<User>>()

    private val _toastError = MutableLiveData<Boolean>()
    private val _toastCorrect = MutableLiveData<Boolean>()

    @Bindable
    val inputUserName = MutableLiveData<String>()
    val inputPassword = MutableLiveData<String>()

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    val toastError: LiveData<Boolean>
        get() = _toastError
    val toastCorrect: LiveData<Boolean>
        get() = _toastCorrect

    fun submit() {
        if (inputUserName.value == null || inputPassword.value == null) {
            _toastError.value = true
        } else {
            scope.launch {
                val usersNames = repository.getUser(inputUserName.value!!)
                if (usersNames != null) {
                    _toastError.value = true
                } else {
                    val username = inputUserName.value!!
                    val password = inputPassword.value!!
                    insert(User(0, username, password))
                    _toastCorrect.value=true
                    inputUserName.value = null
                    inputPassword.value = null
                }
            }

        }

    }
    private fun insert(user: User): Job = viewModelScope.launch {
        repository.addUser(user)
    }
    fun done(){
        _toastError.value=false
    }
    fun sucess() {
        _toastCorrect.value = false
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}