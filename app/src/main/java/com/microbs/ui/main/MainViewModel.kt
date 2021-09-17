package com.microbs.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.microbs.database.AppDatabase
import com.microbs.model.EmployeeWithCustomers
import com.microbs.model.EmployeeWithStorages
import com.microbs.ui.Repository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database: AppDatabase by lazy { AppDatabase.getInstance(application) }

    private val _employeesWithStoragesLiveData = MutableLiveData<List<EmployeeWithStorages>>()
    val employeesWithStoragesLiveData: LiveData<List<EmployeeWithStorages>> =
        _employeesWithStoragesLiveData

    fun getEmployeesWithStorages() {
        viewModelScope.launch {
            _employeesWithStoragesLiveData.value =
                database.employeeDao().getEmployeesWithStorages(Repository.userId)
        }
    }

    private val _employeesWithCustomersLiveData = MutableLiveData<List<EmployeeWithCustomers>>()
    val employeesWithCustomersLiveData: LiveData<List<EmployeeWithCustomers>> =
        _employeesWithCustomersLiveData

    fun getEmployeesWithCustomers() {
        viewModelScope.launch {
            _employeesWithCustomersLiveData.value =
                database.employeeDao().getEmployeesWithCustomers(Repository.userId)
        }
    }


}