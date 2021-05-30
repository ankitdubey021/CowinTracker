package com.ankitdubey021.cowintracker.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankitdubey021.cowintracker.data.DistrictEntity
import com.ankitdubey021.cowintracker.data.HospitalEntity
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

    private val _stateLiveData = MutableLiveData<List<StateEntity>>()
    val stateLiveData : LiveData<List<StateEntity>> = _stateLiveData

    private val _districtLiveData = MutableLiveData<List<DistrictEntity>>()
    val districtLiveData : LiveData<List<DistrictEntity>> = _districtLiveData

    private val _appointments = MutableLiveData<List<HospitalEntity>?>(null)
    val appointments : LiveData<List<HospitalEntity>?> = _appointments

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

    fun getAppointments(districtId : Int, date : String){
        viewModelScope.launch {
            cowinRepository.getAppointmentStatus(districtId = districtId, dateStr = date).collect {
                _appointments.postValue(it.sessions)
            }
        }
    }

}