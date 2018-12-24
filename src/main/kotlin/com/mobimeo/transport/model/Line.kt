package com.mobimeo.transport.model

import java.sql.Time
import javax.persistence.*
import java.lang.*
@Entity(name = "lines")
data class Line(@Id val lineId: Long,
                val lineName: String)