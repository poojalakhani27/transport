package com.mobimeo.transport.service

import com.mobimeo.transport.model.LineDelayDTO
import com.mobimeo.transport.repository.LineDelayRepository
import org.springframework.stereotype.Service


@Service
class LineDelayService(val lineDelayRepository: LineDelayRepository) {
    fun findDelay(lineId: Long): LineDelayDTO? {
        val lineDelay = lineDelayRepository.findByLineLineId(lineId)
        val lineDelayDTO = LineDelayDTO.fromLineDelay(lineDelay);
        return lineDelayDTO
    }

}