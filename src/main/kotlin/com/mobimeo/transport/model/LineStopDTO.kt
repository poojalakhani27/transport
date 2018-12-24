package com.mobimeo.transport.model

import java.math.BigInteger
import java.sql.Timestamp


data class LineStopDTO(val lineId: Long, val lineName: String, val stopId: Long, val time: String) {
    companion object {
        fun from(map: Map<String, Any>): LineStopDTO? {
            var lineStopDTO: LineStopDTO? = null
            if (map.isEmpty().not()) {
                val lineId = map.getValue("lineId") as BigInteger
                val lineName = map.getValue("lineName") as String
                val stopId = map.getValue("stopId") as BigInteger
                val time = map.getValue("timing") as Timestamp
                lineStopDTO = LineStopDTO(lineId.longValueExact(), lineName, stopId.longValueExact(), time.toString());
            }
            return lineStopDTO;
        }
    }

}

