package com.mobimeo.transport.controller

import com.mobimeo.transport.model.LineDelayDTO
import com.mobimeo.transport.service.LineDelayService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lines/{lineId}/delays")
class LineDelayController(val lineDelayService: LineDelayService) {

    @GetMapping()
    fun findDelay(@PathVariable lineId: Long): ResponseEntity<LineDelayDTO> {
        val lineDelayDTO = lineDelayService.findDelay(lineId);
        var responseEntity: ResponseEntity<LineDelayDTO>
        if (lineDelayDTO == null)
            responseEntity = ResponseEntity(HttpStatus.NOT_FOUND)
        else
            responseEntity = ResponseEntity.ok(lineDelayDTO);
        return responseEntity
    }
}