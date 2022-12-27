package com.example.sepiaapp.repo

import com.example.sepiaapp.helper.GsonHelper
import com.example.sepiaapp.model.Pet
import com.example.sepiaapp.model.PetList
import javax.inject.Inject

class PetListRepository @Inject constructor(
    private val gsonHelper : GsonHelper) {
    fun getPetList() : PetList {
       return gsonHelper.getPetList()
    }
}
