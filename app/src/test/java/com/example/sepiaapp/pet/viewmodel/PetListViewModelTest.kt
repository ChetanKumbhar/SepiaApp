package com.example.sepiaapp.pet.viewmodel

import com.example.sepiaapp.BaseTestClass
import com.example.sepiaapp.model.Pet
import com.example.sepiaapp.model.PetList
import com.example.sepiaapp.repo.PetListRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import java.util.*


class PetListViewModelTest : BaseTestClass() {
    @MockK
    lateinit var petListRepository : PetListRepository

    private lateinit var sut : PetListViewModel


    override fun setup(){
       super.setup()
        sut = PetListViewModel(petListRepository)
    }

    @Test
    fun getPets(){
        val date = Date()
        val list = PetList(listOf(Pet("url1","cat","",date),
            Pet("url1","dog","",date)))

        every {  petListRepository.getPetList() } returns list
        runTest {
            sut.getPets()
            Assert.assertEquals(list.pets,sut.petList.value )
            verify(exactly = 1) {  petListRepository.getPetList() }
        }
    }

    @Test
    fun checkConfigTime_when_success(){
        every {  petListRepository.isValidTimeFromConfig() } returns true
        runTest {
            sut.checkConfigTime()
            Assert.assertEquals(true, sut.stateConfigTime.value)
            verify(exactly = 1) {  petListRepository.isValidTimeFromConfig() }
        }
    }
    @Test
    fun checkConfigTime_when_Fail(){
        every {  petListRepository.isValidTimeFromConfig() } throws Exception()
        runTest {
            sut.checkConfigTime()
            Assert.assertEquals(false, sut.stateConfigTime.value)
            verify(exactly = 1) {  petListRepository.isValidTimeFromConfig() }
        }
    }
}