package com.mobimeo.transport.repository

import com.mobimeo.transport.model.Line
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.sql.Time

interface LineRepository: CrudRepository<Line, Long> {
    fun findByLineName(lineName: String): Line

}