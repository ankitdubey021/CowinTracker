package com.ankitdubey021.cowintracker.ui.screens.cases_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankitdubey021.cowintracker.data.CovidReportDao
import com.ankitdubey021.cowintracker.repository.CovidReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CovidReportViewModel @Inject constructor(
    private val covidReportRepository: CovidReportRepository
) : ViewModel() {

    private val _liveData = MutableLiveData<CovidReportDao>()
    val liveData : LiveData<CovidReportDao> = _liveData

    init {
        getCovidReport()
    }
    private fun getCovidReport(){
        viewModelScope.launch {
            covidReportRepository.getReport().collect {
                _liveData.postValue(it)
            }
        }
    }

}