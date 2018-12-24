package com.mobimeo.transport.service

import com.mobimeo.transport.model.LineDTO
import com.mobimeo.transport.repository.LineTimingRepository
import org.springframework.stereotype.Service
import java.sql.Time


@Service
class LineService(val lineTimingRepository: LineTimingRepository) {
    fun findLineByTimeAndCoordinates(time: String, x: Float, y: Float): LineDTO? {
        val sqlTime = Time.valueOf(time)
        val line = lineTimingRepository.findByTimeAndXAndY(sqlTime, x, y);
        return LineDTO.fromLine(line)
    }
}