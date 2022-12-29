package com.example.sepiaapp.pet.details

import com.example.sepiaapp.BaseTestClass
import com.example.sepiaapp.databinding.FragmentPetDetailsBinding
import io.mockk.impl.annotations.RelaxedMockK

class PetDetailsFragmentTest :BaseTestClass(){
lateinit var sut: PetDetailsFragment
@RelaxedMockK
lateinit var binding1: FragmentPetDetailsBinding

    override fun setup() {
        super.setup()
        sut = PetDetailsFragment()
    }

    /*@Test
    fun loadData(){
        val spy = spyk(sut)
        every { spy.binding } returns binding1

        mockkObject(GlideHelper)
        mockkObject(WebViewHelper)
        every { GlideHelper.loadImage(any(),any(),any()) } just Runs
        every { WebViewHelper.load(any(),any(),any()) } just Runs
        spy.loadData(Pet("url1","cat","",Date()))
        verify(exactly = 1) { GlideHelper.loadImage(any(),any(),any()) }
        verify(exactly = 1) { WebViewHelper.load(any(),any(),any()) }
    }*/
}