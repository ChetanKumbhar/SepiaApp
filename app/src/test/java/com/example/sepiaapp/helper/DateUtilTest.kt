package com.example.sepiaapp.helper

import com.example.sepiaapp.BaseTestClass
import com.example.sepiaapp.model.Config
import com.example.sepiaapp.model.Setting
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Test
import java.util.*

class DateUtilTest : BaseTestClass() {

    @MockK
    lateinit var gsonHelper: GsonHelper

    lateinit var sut : DateUtil

    override fun setup() {
        super.setup()
        sut = DateUtil(gsonHelper)
    }

    @Test
    fun assert_Sunday(){
        val actual = DateUtil.Day.SUNDAY
        Assert.assertEquals(actual.name,"U")
        Assert.assertEquals(actual.value,Calendar.SUNDAY)
    }

    @Test
    fun assert_Monday(){
        val actual = DateUtil.Day.MONDAY
        Assert.assertEquals(actual.name,"M")
        Assert.assertEquals(actual.value,Calendar.MONDAY)
    }
    @Test
    fun assert_Tuesday(){
        val actual = DateUtil.Day.TUESDAY
        Assert.assertEquals(actual.name,"T")
        Assert.assertEquals(actual.value,Calendar.TUESDAY)
    }@Test
    fun assert_WEDNESDAY(){
        val actual = DateUtil.Day.WEDNESDAY
        Assert.assertEquals(actual.name,"W")
        Assert.assertEquals(actual.value,Calendar.WEDNESDAY)
    }
    @Test
    fun assert_THURSDAY(){
        val actual = DateUtil.Day.THURSDAY
        Assert.assertEquals(actual.name,"R")
        Assert.assertEquals(actual.value,Calendar.THURSDAY)
    }
    @Test
    fun assert_Friday(){
        val actual = DateUtil.Day.FRIDAY
        Assert.assertEquals(actual.name,"F")
        Assert.assertEquals(actual.value,Calendar.FRIDAY)
    }
    @Test
    fun assert_SATURDAY(){
        val actual = DateUtil.Day.SATURDAY
        Assert.assertEquals(actual.name,"S")
        Assert.assertEquals(actual.value,Calendar.SATURDAY)
    }

    @Test
    fun parse_Sunday(){
        val actual = DateUtil.Day.parse("U")
        Assert.assertEquals(actual,DateUtil.Day.SUNDAY)
    }

    @Test
    fun parse_Monday(){
        val actual = DateUtil.Day.parse("M")
        Assert.assertEquals(actual,DateUtil.Day.MONDAY)
    }

    @Test
    fun parse_Tuesday(){
        val actual = DateUtil.Day.parse("T")
        Assert.assertEquals(actual,DateUtil.Day.TUESDAY)
    }

    @Test
    fun parse_WEDNESDAY(){
        val actual = DateUtil.Day.parse("W")
        Assert.assertEquals(actual,DateUtil.Day.WEDNESDAY)
    }

    @Test
    fun parse_THURSDAY(){
        val actual = DateUtil.Day.parse("R")
        Assert.assertEquals(actual,DateUtil.Day.THURSDAY)
    }

    @Test
    fun parse_FRIDAY(){
        val actual = DateUtil.Day.parse("F")
        Assert.assertEquals(actual,DateUtil.Day.FRIDAY)
    }

    @Test
    fun parse_SATURDAY(){
        val actual = DateUtil.Day.parse("S")
        Assert.assertEquals(actual,DateUtil.Day.SATURDAY)
    }

    @Test
    fun isValidTimeFromConfig_success(){
        mockkStatic(Calendar::class)
        every { Calendar.getInstance()[Calendar.DAY_OF_WEEK] } returns 3 // tuesday

        val spy = spyk(sut)

        val gc = GregorianCalendar()
        gc.set(Calendar.HOUR_OF_DAY, 14)
        gc.set(Calendar.MINUTE, 10)
        val date = gc.time

        every { spy.getDateNow() } returns date
        every { spy.getConfig() } returns Config(Setting(workHours = "M-F 09:00 - 18:00"))

        val actual = spy.isValidTimeFromConfig()
        Assert.assertTrue(actual)
    }

    @Test
    fun isValidTimeFromConfig_Fail_outByDayRange(){
        mockkStatic(Calendar::class)
        every { Calendar.getInstance()[Calendar.DAY_OF_WEEK] } returns 1 // Sunday

        val spy = spyk(sut)

        val gc = GregorianCalendar()
        gc.set(Calendar.HOUR_OF_DAY, 14)
        gc.set(Calendar.MINUTE, 10)
        val date = gc.time

        every { spy.getDateNow() } returns date
        every { spy.getConfig() } returns Config(Setting(workHours = "M-F 09:00 - 18:00"))

        val actual = spy.isValidTimeFromConfig()
        Assert.assertFalse(actual)
    }


    @Test
    fun isValidTimeFromConfig_Fail_outByTimeRange(){
        mockkStatic(Calendar::class)
        every { Calendar.getInstance()[Calendar.DAY_OF_WEEK] } returns 1 // Sunday

        val spy = spyk(sut)

        val gc = GregorianCalendar()
        gc.set(Calendar.HOUR_OF_DAY, 17)
        gc.set(Calendar.MINUTE, 10)
        val date = gc.time

        every { spy.getDateNow() } returns date
        every { spy.getConfig() } returns Config(Setting(workHours = "M-F 09:00 - 18:00"))

        val actual = spy.isValidTimeFromConfig()
        Assert.assertFalse(actual)
    }

    @Test
    fun getConfig(){
        every { gsonHelper.getTimeConfig() } returns Config(Setting(workHours = ""))
        sut.getConfig()
        verify(exactly = 1) { gsonHelper.getTimeConfig() }
    }
}