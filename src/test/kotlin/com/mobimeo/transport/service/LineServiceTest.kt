package com.mobimeo.transport.service

import com.mobimeo.transport.model.Line
import com.mobimeo.transport.model.LineDTO
import com.mobimeo.transport.repository.LineTimingRepository
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.sql.Time

@RunWith(MockitoJUnitRunner::class)
internal class LineServiceTest {
    @Mock
    private lateinit var lineTimingRepository: LineTimingRepository

    private lateinit var underTest: LineService

    @Before
    internal fun setUp() {
        underTest = LineService(lineTimingRepository)
    }

    @Test
    fun findLineByTimeAndCoordinatesShouldReturnNullWhenNotFound() {
        val time = "10:00:00"
        val x = 1F
        val y = 1F
        `when`(lineTimingRepository.findByTimeAndXAndY(Time.valueOf(time), x, y)).thenReturn(null)

        val actual = underTest.findLineByTimeAndCoordinates(time, x, y)

        Assertions.assertNull(actual)
    }

    @Test
    fun findLineByTimeAndCoordinatesShouldReturnLineDTOWhenFound() {
        val time = "10:00:00"
        val x = 1F
        val y = 1F
        val line = Line(1, "M4")
        `when`(lineTimingRepository.findByTimeAndXAndY(Time.valueOf(time), x, y)).thenReturn(line)

        val actual = underTest.findLineByTimeAndCoordinates(time, x, y)

        Assertions.assertEquals(LineDTO(1, "M4"), actual)
    }


}