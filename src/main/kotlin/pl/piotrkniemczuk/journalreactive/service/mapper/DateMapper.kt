package pl.piotrkniemczuk.journalreactive.service.mapper

import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*

@Component
class DateMapper(
        private val dateFormat: SimpleDateFormat,
) {

    fun toDate(date: String): Date = dateFormat.parse(date)

    fun toString(date: Date): String = dateFormat.format(date)
}