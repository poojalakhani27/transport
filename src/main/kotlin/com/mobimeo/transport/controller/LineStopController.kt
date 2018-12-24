package com.mobimeo.transport.controller

import com.mobimeo.transport.model.LineStopDTO
import com.mobimeo.transport.service.LineStopService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lines/stops")
class LineStopController(val lineStopService: LineStopService) {

    @GetMapping("/{stopId}")
    fun findNextArrivingAtStop(@PathVariable stopId: Long): ResponseEntity<LineStopDTO> {
        val lineStopDTO = lineStopService.findLineArrivingNextAt(stopId);
        var responseEntity: ResponseEntity<LineStopDTO>
        if (lineStopDTO == null)
            responseEntity = ResponseEntity(HttpStatus.NOT_FOUND)
        else
            responseEntity = ResponseEntity.ok(lineStopDTO)
        return responseEntity
    }
}