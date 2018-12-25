package com.mobimeo.transport.model

import javax.persistence.*

@Entity
@Table(name = "line_delays")
data class LineDelay(@OneToOne(fetch = FetchType.LAZY, optional = false)
                     @PrimaryKeyJoinColumn
                     val line: Line, val delay: Long, @Id val id: Long? = null)