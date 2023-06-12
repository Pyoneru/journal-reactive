package pl.piotrkniemczuk.journalreactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JournalReactiveApplication

fun main(args: Array<String>) {
    runApplication<JournalReactiveApplication>(*args)
}
