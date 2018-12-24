package com.mobimeo.transport.service

import com.mobimeo.transport.model.Line
import com.mobimeo.transport.model.LineDelay
import com.mobimeo.transport.model.LineDelayDTO
import com.mobimeo.transport.repository.LineDelayRepository
import com.mobimeo.transport.repository.LineTimingRepository
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.sql.Time

@RunWith(MockitoJUnitRunner::class)
internal class LineDelayServiceTest {
    @Mock
    private lateinit var lineDelayRepository: LineDelayRepository

    private lateinit var underTest: LineDelayService

    @Before
    internal fun setUp() {
        underTest = LineDelayService(lineDelayRepository)
    }

    @Test
    fun findDelayShouldReturnDelaysForALine(){
        val lineId = 1L
        val delayMins: Long = 10
        val lineName = "M4"
        Mockito.`when`(lineDelayRepository.findByLineLineId(lineId)).thenReturn(LineDelay(Line(lineId, lineName), delayMins, lineId))

        val delay = underTest.findDelay(lineId)

        assertEquals(delayMins, delay!!.delayInMinutes)
        assertEquals(lineId, delay!!.lineDTO.lineId)
        assertEquals(lineName, delay!!.lineDTO.lineName)
    }
}