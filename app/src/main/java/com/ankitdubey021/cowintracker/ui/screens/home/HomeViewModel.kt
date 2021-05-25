package com.ankitdubey021.cowintracker.ui.screens.home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.*
import com.ankitdubey021.cowintracker.data.AppointmentEntity
import com.ankitdubey021.cowintracker.data.DistrictEntity
import com.ankitdubey021.cowintracker.data.DistrictList
import com.ankitdubey021.cowintracker.data.StateEntity
import com.ankitdubey021.cowintracker.repository.CowinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cowinRepository: CowinRepository
): ViewModel(){

    init {
        fetchStates()
    }

    private val _stateLiveData = MutableLiveData<List<StateEntity>>()
    val stateLiveData : LiveData<List<StateEntity>> = _stateLiveData

    private val _districtLiveData = MutableLiveData<List<DistrictEntity>>()
    val districtLiveData : LiveData<List<DistrictEntity>> = _districtLiveData

    private val _appointments = MutableLiveData<List<AppointmentEntity>>()
    val appointments : LiveData<List<AppointmentEntity>> = _appointments

    fun fetchStates(){
        viewModelScope.launch {
            cowinRepository.fetchStates().collect {
                _stateLiveData.postValue(it.states)
            }
        }
    }

    fun fetchDistricts(stateId : Int){
        viewModelScope.launch {
            cowinRepository.fetchDistricts(stateId).collect {
                _districtLiveData.postValue(it.districts)
            }
        }
    }

    fun getAppointments(districtId : Int){
        viewModelScope.launch {
            cowinRepository.getAppointmentStatus(districtId = districtId).collect {
                _appointments.postValue(it.sessions)
            }
        }
    }

}