package com.mobimeo.transport.utils

import org.springframework.stereotype.Component
import java.sql.Time
import java.time.LocalTime


@Component
class TimeFactory {
    fun now(): Time {
        val instant = LocalTime.now()
        return Time.valueOf(instant.toString())
    }
}