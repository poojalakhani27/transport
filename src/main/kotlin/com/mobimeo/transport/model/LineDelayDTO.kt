package com.mobimeo.transport.model

data class LineDelayDTO(val lineDTO: LineDTO, val delayInMinutes : Long) {
    companion object {
        fun fromLineDelay(lineDelay: LineDelay?) : LineDelayDTO?  {
            val lineDTO = LineDTO.fromLine(lineDelay?.line) ?: return null;
            return lineDelay?.let { LineDelayDTO(lineDTO, it.delay ); }
        }
    }
}