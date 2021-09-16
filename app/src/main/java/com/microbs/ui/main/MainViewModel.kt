package com.microbs.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.microbs.database.AppDatabase
import com.microbs.model.Employee
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database: AppDatabase by lazy { AppDatabase.getInstance(application) }

    private val _employeesLiveData = MutableLiveData<List<Employee>>()
    val employeesLiveData: LiveData<List<Employee>> = _employeesLiveData
    fun getEmployees(userId: Long) {
        viewModelScope.launch {
            _employeesLiveData.value = database.employeeDao().getEmployees(userId)
        }
    }


}