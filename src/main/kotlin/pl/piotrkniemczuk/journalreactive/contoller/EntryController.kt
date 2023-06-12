package pl.piotrkniemczuk.journalreactive.contoller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.piotrkniemczuk.journalreactive.handler.PaginationHandler
import pl.piotrkniemczuk.journalreactive.model.DeleteOperationResult
import pl.piotrkniemczuk.journalreactive.model.EntryDTO
import pl.piotrkniemczuk.journalreactive.service.EntryService
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.*

@RestController
@RequestMapping("/api/entry")
class EntryController(
        val entryService: EntryService
) {

    @GetMapping
    fun findAll(pagination: PaginationHandler): Flux<EntryDTO> {
        return entryService.findAll(pagination).delayElements(Duration.ofSeconds(1L))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): Mono<EntryDTO> {
        return entryService.findById(id)
    }

    @PostMapping
    fun save(@RequestBody input: EntryDTO): Mono<EntryDTO> {
        return entryService.save(input)
    }

    @PutMapping("/{id}")
    fun update(@RequestBody input: EntryDTO, @PathVariable id: UUID): Mono<EntryDTO> {
        return entryService.update(input, id)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): Mono<DeleteOperationResult> {
        return entryService.deleteById(id)
    }
}