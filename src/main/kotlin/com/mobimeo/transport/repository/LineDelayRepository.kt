package com.mobimeo.transport.repository

import com.mobimeo.transport.model.LineDelay
import org.springframework.data.repository.CrudRepository

interface LineDelayRepository : CrudRepository<LineDelay, Long> {
    fun findByLineLineId(lineId: Long): LineDelay?
}