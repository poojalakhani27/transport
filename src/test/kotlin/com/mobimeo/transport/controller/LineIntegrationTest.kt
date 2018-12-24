package com.mobimeo.transport.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.mobimeo.transport.model.LineDTO
import com.mobimeo.transport.model.LineStopDTO
import com.mobimeo.transport.utils.TimeFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mockito
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.sql.Time
import java.time.*


@ExtendWith(SpringExtension::class)
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureMockMvc
internal class LineIntegrationTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Autowired
    lateinit var env: Environment;

    @Autowired
    lateinit var mvc:MockMvc;

    @MockBean
    lateinit var timeFactory: TimeFactory;

    @Test
    fun getOfLineWithFiltersShouldReturnOkWhenFound() {
         mvc.perform(MockMvcRequestBuilders.get("/lines")
                .contentType(MediaType.APPLICATION_JSON)
                .param("time", "10:0:0")
                .param("x", "1")
                .param("y", "1"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.lineId").value(0))
                .andExpect(jsonPath("$.lineName").value("M4"));
    }


    @Test
    fun getOfLineWithFiltersShouldReturn404WhenNotFound() {
        mvc.perform(MockMvcRequestBuilders.get("/lines")
                .contentType(MediaType.APPLICATION_JSON)
                .param("time", "11:01:00")
                .param("x", "3")
                .param("y", "1"))
                .andExpect(status().isNotFound);
    }

    @Test
    fun getOfNextLineForAStopShouldReturnOkWhenFound() {
        Mockito.`when`(timeFactory.now()).thenReturn(Time.valueOf("10:07:59"))

        mvc.perform(MockMvcRequestBuilders.get("/lines/stops/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.lineId").value(0))
                .andExpect(jsonPath("$.lineName").value("M4"));
    }

    @Test
    fun getOfNextLineForAStopShouldReturn404WhenNotFound() {
        Mockito.`when`(timeFactory.now()).thenReturn(Time.valueOf("13:07:59"))

        mvc.perform(MockMvcRequestBuilders.get("/lines/stops/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound)
    }

    @Test
    fun getOfDelayForALineShouldReturnWhenFound() {
        mvc.perform(MockMvcRequestBuilders.get("/lines/0/delays")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.lineDTO.lineId").value(0))
                .andExpect(jsonPath("$.delayInMinutes").value(1))
    }
}