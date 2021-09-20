package com.microbs.ui.main.retro

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.microbs.model.Retro
import kotlinx.coroutines.launch

class RetroViewModel(application: Application) : AndroidViewModel(application) {

    private val retroRepository: RetroRepository by lazy { RetroRepository.getInstance(application) }

    private val _retrosFroUserLiveData = MutableLiveData<List<Retro>>()
    val retrosFroUserLiveData: LiveData<List<Retro>> =
        _retrosFroUserLiveData

    fun getRetrosForUser(gitUsername: String) {
        viewModelScope.launch {
            _retrosFroUserLiveData.value =
                retroRepository.getRetrosForUser(gitUsername)
        }
    }


}