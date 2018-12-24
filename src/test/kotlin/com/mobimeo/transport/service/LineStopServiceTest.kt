package com.mobimeo.transport.service

import com.mobimeo.transport.repository.LineTimingRepository
import com.mobimeo.transport.utils.TimeFactory
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigInteger
import java.sql.Time
import java.sql.Timestamp
import java.time.Instant

@RunWith(MockitoJUnitRunner::class)
internal class LineStopServiceTest {
    @Mock
    private lateinit var lineTimingRepository: LineTimingRepository
    @Mock
    private lateinit var timeFactory: TimeFactory

    private lateinit var underTest: LineStopService

    @Before
    internal fun setUp() {
        underTest = LineStopService(lineTimingRepository, timeFactory)
    }

    @Test
    fun findByArrivingNextShouldReturnNullWhenNoLinesFound() {
        val time = Time.valueOf("10:00:00")
        val stopId = 1L

        Mockito.`when`(lineTimingRepository.findLineArrivingAfter(time, stopId)).thenReturn(emptyMap())
        Mockito.`when`(timeFactory.now()).thenReturn(time)

        val actual = underTest.findLineArrivingNextAt(stopId)

        assertNull(actual)
    }

    @Test
    fun findByArrivingNextShouldReturnLineStopDTOWhenFound() {
        val time = Time.valueOf("10:00:00")
        val stopId = 1L

        val map = hashMapOf<String, Any>("stopId" to BigInteger.ONE,
                "lineId" to BigInteger.ONE,
                "lineName" to "M4",
                "timing" to Timestamp.from(Instant.now()))
        Mockito.`when`(lineTimingRepository.findLineArrivingAfter(time, stopId)).thenReturn(map)
        Mockito.`when`(timeFactory.now()).thenReturn(time)

        val actual = underTest.findLineArrivingNextAt(stopId)

        assertEquals(1L, actual!!.lineId)
        assertEquals(stopId, actual!!.stopId)
    }
}