package com.mobimeo.transport.controller

import com.mobimeo.transport.model.LineDTO
import com.mobimeo.transport.service.LineService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lines")
class LineController(val lineService: LineService) {

    @GetMapping()
    fun findByFilters(@RequestParam time: String, @RequestParam x: Float, @RequestParam y: Float): ResponseEntity<LineDTO> {
        val lineDTO = lineService.findLineByTimeAndCoordinates(time, x, y)
        var responseEntity: ResponseEntity<LineDTO>
        if (lineDTO == null)
            responseEntity = ResponseEntity(HttpStatus.NOT_FOUND)
        else
            responseEntity = ResponseEntity.ok(lineDTO)
        return responseEntity
    }

}