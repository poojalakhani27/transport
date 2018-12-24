package com.mobimeo.transport.model

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.sql.Time
import javax.persistence.*

@Entity
@Table(name = "line_timings")
data class LineTiming(@ManyToOne(fetch = FetchType.LAZY, optional = false)
                      @JoinColumn(name = "line_id", nullable = false)
                      @OnDelete(action = OnDeleteAction.CASCADE)
                      val line: Line,

                      @ManyToOne(fetch = FetchType.LAZY, optional = false)
                      @JoinColumn(name = "stop_id", nullable = false)
                      @OnDelete(action = OnDeleteAction.CASCADE)
                      val stop: Stop,

                      val time: Time,

                      @Id @GeneratedValue val id: Long? = null)