package com.mobimeo.transport.service

import com.mobimeo.transport.model.LineStopDTO
import com.mobimeo.transport.repository.LineTimingRepository
import com.mobimeo.transport.utils.TimeFactory
import org.springframework.stereotype.Service


@Service
class LineStopService(val lineTimingRepository: LineTimingRepository, val timeFactory: TimeFactory) {
    fun findLineArrivingNextAt(stopId: Long): LineStopDTO? {
        val map = lineTimingRepository.findLineArrivingAfter(timeFactory.now(), stopId)
        return LineStopDTO.from(map)
    }
}