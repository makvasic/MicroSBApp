package com.microbs.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.microbs.database.AppDatabase
import com.microbs.model.EmployeeStorageCrossRef
import com.microbs.model.EmployeeWithCustomers
import com.microbs.model.EmployeeWithStorages
import com.microbs.model.Storage
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

    fun saveEmployeeWithStorages(employeeWithStorages: EmployeeWithStorages) {
        viewModelScope.launch {
            val employeeId = database.employeeDao().saveEmployee(employeeWithStorages.employee)

            val employeeStorageCrossRefs = ArrayList<EmployeeStorageCrossRef>()
            val storages = employeeWithStorages.storages
            storages.forEach { storage ->
                employeeStorageCrossRefs.add(EmployeeStorageCrossRef(employeeId, storage.storageId))
            }
            database.employeeStorageCrossRefDao().insertRefs(employeeStorageCrossRefs)
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


    val storagesLiveData = MutableLiveData<List<Storage>?>()
    fun getStorages() {
        viewModelScope.launch {
            storagesLiveData.value = database.storageDao().getAll()
        }
    }


}