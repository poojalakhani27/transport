package com.mobimeo.transport.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "stops")
data class Stop(@Id val stopId: Long,
                val x: Float,
                val y: Float)