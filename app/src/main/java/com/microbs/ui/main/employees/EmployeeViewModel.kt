package com.microbs.ui.main.employees

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.microbs.database.AppDatabase
import com.microbs.model.Employee
import com.microbs.model.EmployeeWithStorages
import com.microbs.ui.Repository
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {

    private val database: AppDatabase by lazy { AppDatabase.getInstance(application) }

    private val _employeeWithStoragesLiveData = MutableLiveData<EmployeeWithStorages>()
    val employeeWithStoragesLiveData: LiveData<EmployeeWithStorages> =
        _employeeWithStoragesLiveData

    fun getEmployeeWithStorages(employeeId: Long) {

        if (_employeeWithStoragesLiveData.value != null) return

        if (employeeId == 0L) {
            _employeeWithStoragesLiveData.value =
                EmployeeWithStorages(Employee(userId = Repository.userId), ArrayList())
        }

        viewModelScope.launch {
            _employeeWithStoragesLiveData.value =
                database.employeeDao().getEmployeeWithStorages(employeeId)
        }
    }


}