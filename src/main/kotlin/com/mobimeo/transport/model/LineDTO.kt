package com.mobimeo.transport.model

data class LineDTO(val lineId: Long, val lineName: String) {

    companion object {
        fun fromLine(line: Line?): LineDTO? {
            return line?.let {
                return LineDTO(it.lineId, it.lineName);
            }
        }

    }
}
