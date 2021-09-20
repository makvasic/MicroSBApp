package com.microbs.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.microbs.database.AppDatabase
import com.microbs.model.User
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val database: AppDatabase by lazy { AppDatabase.getInstance(application) }

    private val _loginLiveData = MutableLiveData<User?>()
    val loginLiveData: LiveData<User?> = _loginLiveData
    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginLiveData.value = database.userDao().getUser(username, password)
        }
    }

    private val _registerLiveData = MutableLiveData<Long>()
    val registerLiveData: LiveData<Long> = _registerLiveData
    fun register(user: User) {
        viewModelScope.launch {
            _registerLiveData.value = database.userDao().insert(user)
        }
    }


}