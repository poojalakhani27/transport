package com.mobimeo.transport.repository

import com.mobimeo.transport.model.Line
import org.springframework.data.repository.CrudRepository

interface LineRepository : CrudRepository<Line, Long> {
    fun findByLineName(lineName: String): Line
}