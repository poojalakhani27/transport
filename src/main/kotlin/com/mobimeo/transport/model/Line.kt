package com.mobimeo.transport.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "lines")
data class Line(@Id val lineId: Long,
                val lineName: String)