package com.android.hepsiburadacase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hepsiburadacase.model.AppsModel
import com.android.hepsiburadacase.service.ItunesApiService
import kotlinx.coroutines.launch

class AppsViewModel : ViewModel() {
    val apps = MutableLiveData<AppsModel>()
    private val itunesApiService = ItunesApiService()
    fun refreshData(string: String, entity: String, limit: String) {
        getData(string, entity, limit)
    }

    private fun getData(string: String, entity: String, limit: String) {
        viewModelScope.launch {
            val response = itunesApiService.getAppsData(string, entity, limit)
            if (response.isSuccessful) {
                val data = response.body()
                apps.value = data
            }
        }
    }
}