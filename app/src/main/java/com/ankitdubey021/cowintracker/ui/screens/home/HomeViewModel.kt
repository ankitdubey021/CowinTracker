package com.ankitdubey021.cowintracker.ui.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val stateLiveData = _stateLiveData

    fun fetchStates(){
        viewModelScope.launch {
            cowinRepository.fetchStates().collect {
                _stateLiveData.postValue(it.states)
            }
        }
    }

}