package com.mobimeo.transport.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "stops")
data class Stop(@Id val stopId : Long,
                val x: Float,
                val y: Float)