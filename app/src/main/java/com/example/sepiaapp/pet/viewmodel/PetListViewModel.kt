package com.example.sepiaapp.pet.viewmodel

import android.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sepiaapp.helper.DateUtil
import com.example.sepiaapp.model.Pet
import com.example.sepiaapp.repo.PetListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetListViewModel @Inject constructor(
    private val petListRepository: PetListRepository
) : ViewModel() {
    val petList: MutableLiveData<List<Pet>> by lazy { MutableLiveData<List<Pet>>() }
    val stateConfigTime: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun getPets() {
        viewModelScope.launch {
            val pets = petListRepository.getPetList().pets
            petList.value = pets
        }
    }

    fun checkConfigTime() {
        viewModelScope.launch {
            try {
                stateConfigTime.value = petListRepository.isValidTimeFromConfig()
            } catch (e: Exception) {
                stateConfigTime.value = false
            }
        }
    }

    fun showConfigAlert(activity: FragmentActivity?) {
        AlertDialog.Builder(activity)
            .setTitle("Alert")
            .setMessage("Invalid Working Hours. (Configuration Error)")
            .setPositiveButton(android.R.string.yes) { dialog, which ->
                activity?.finish()
            }.show()
    }

}