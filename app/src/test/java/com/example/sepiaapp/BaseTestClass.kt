package com.example.sepiaapp

import android.app.Application
import android.content.Context
import androidx.annotation.CallSuper
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.MockKAnnotations
import io.mockk.spyk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseTestClass {
    private val testDispatcher = UnconfinedTestDispatcher()
    protected open val application = spyk(ApplicationProvider.getApplicationContext<Application>())
    protected open val context: Context = spyk(ApplicationProvider.getApplicationContext<Context>())

    @Before
    @CallSuper
    open fun setup(){
        MockKAnnotations.init(this)
        kotlinx.coroutines.Dispatchers.setMain(testDispatcher)
    }
}