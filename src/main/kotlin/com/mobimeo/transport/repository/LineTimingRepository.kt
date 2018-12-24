package com.mobimeo.transport.repository

import com.mobimeo.transport.model.Line
import com.mobimeo.transport.model.LineStopDTO
import com.mobimeo.transport.model.LineTiming
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import java.sql.Time
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.query.Param


interface LineTimingRepository: CrudRepository<LineTiming, Long> {

    @Query("SELECT l.line FROM LineTiming l " +
            "WHERE l.time = :time AND l.stop.x = :x AND l.stop.y = :y")
    fun findByTimeAndXAndY(time : Time, x : Float, y : Float): Line?

    @Query("SELECT sub.line_id as lineId, sub.line_name as lineName, sub.stop_id as stopId, timing FROM " +
                        "(SELECT lt.line_id, l.line_name, lt.stop_id, DATEADD('MINUTE', ld.delay, lt.time) as timing " +
                        "FROM line_timings lt, line_delays ld, lines l " +
                        "WHERE lt.line_id = ld.id " +
                        "AND l.line_id = lt.line_id " +
                        "AND lt.stop_id = :stopId) sub " +
                "WHERE timing > :time " +
                "ORDER BY timing limit 1", nativeQuery = true )
    fun findLineArrivingAfter(time : Time, stopId : Long): Map<String, Any>


}