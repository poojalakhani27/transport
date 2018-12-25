package com.mobimeo.transport

import com.mobimeo.transport.model.Line
import com.mobimeo.transport.model.LineDelay
import com.mobimeo.transport.model.LineTiming
import com.mobimeo.transport.model.Stop
import com.mobimeo.transport.repository.LineDelayRepository
import com.mobimeo.transport.repository.LineRepository
import com.mobimeo.transport.repository.LineTimingRepository
import com.mobimeo.transport.repository.StopRepository
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.util.ResourceUtils
import java.io.File
import java.nio.charset.Charset
import java.sql.Time


@Component
class DBInitializer(private val lineRepository: LineRepository,
                    private val stopRepository: StopRepository,
                    private val lineTimingRepository: LineTimingRepository,
                    private val lineDelayRepository: LineDelayRepository) : ApplicationRunner {


    override fun run(args: ApplicationArguments?) {
        loadLines()
        loadStops()
        loadLineTimings()
        loadDelays()
    }

    private fun loadLines() {
        val csvContent = parseCsv("lines.csv")
        val lines = csvContent.drop(1).map {
            Line(it.get(0).toLong(), it.get(1))
        }

        lineRepository.saveAll(lines)
    }

    private fun loadStops() {
        val csvContent = parseCsv("stops.csv")
        val stops = csvContent.drop(1).map {
            Stop(it.get(0).toLong(), it.get(1).toFloat(), it.get(2).toFloat());
        }

        stopRepository.saveAll(stops)
    }

    private fun loadLineTimings() {
        val csvContent = parseCsv("times.csv")
        val lineTimings = csvContent.drop(1).map {
            val line = lineRepository.findById(it.get(0).toLong()).get()
            val stop = stopRepository.findById(it.get(1).toLong()).get()
            LineTiming(line, stop, Time.valueOf(it.get(2)))
        }

        lineTimingRepository.saveAll(lineTimings)
    }

    private fun loadDelays() {
        val csvContent = parseCsv("delays.csv")
        val lineDelays = csvContent.drop(1).map {
            val line = lineRepository.findByLineName(it.get(0))
            val lineDelay = LineDelay(line, it.get(1).toLong(), line.lineId)
            lineDelay
        }

        lineDelayRepository.saveAll(lineDelays)
    }

    private fun parseCsv(fileName: String): CSVParser {
        val file: File = ResourceUtils.getFile("classpath:" + fileName);
        return CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.EXCEL)
    }
}