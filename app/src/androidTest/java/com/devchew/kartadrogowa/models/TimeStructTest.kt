package com.devchew.kartadrogowa

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.devchew.kartadrogowa.models.TimeStruct
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TimeStructTest {
    @Test
    fun createTimeStruct() {
        val timeStruct = TimeStruct(1, 2, 3, 4)
        assertEquals(1, timeStruct.h)
        assertEquals(2, timeStruct.m)
        assertEquals(3, timeStruct.s)
        assertEquals(4, timeStruct.tenths)
    }

    @Test
    fun emptyTimeStructShouldNotBeSet() {
        val timeStruct = TimeStruct()
        assertFalse(timeStruct.isSet())
    }

    @Test
    fun timeStructShouldBeSet() {
        val timeStruct = TimeStruct(1, 2, 3, 4)
        assertTrue(timeStruct.isSet())
    }

    @Test
    fun timeStructToSeconds() {
        val timeStruct = TimeStruct(1, 2, 3, 4)
        assertEquals(3723, timeStruct.toSeconds())
    }

    @Test
    fun timeStructToMs() {
        val timeStruct = TimeStruct(1, 2, 3, 4)
        assertEquals(3723400, timeStruct.toMs())
    }

    @Test
    fun timeStructFromMs() {
        val timeStruct = TimeStruct()
        timeStruct.fromMs(49220800L)
        assertEquals(13, timeStruct.h)
        assertEquals(40, timeStruct.m)
        assertEquals(20, timeStruct.s)
        assertEquals(8, timeStruct.tenths)
    }

    @Test
    fun timeConversion() {
        val timeStruct = TimeStruct()
        val time = 3723400L
        timeStruct.fromMs(time)
        assertEquals(time, timeStruct.toMs())
    }

}