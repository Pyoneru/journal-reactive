package pl.piotrkniemczuk.journalreactive.service

import pl.piotrkniemczuk.journalreactive.handler.PaginationHandler
import pl.piotrkniemczuk.journalreactive.model.DeleteOperationResult
import pl.piotrkniemczuk.journalreactive.model.EntryDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

interface EntryService {

    fun save(input: EntryDTO): Mono<EntryDTO>

    fun update(input: EntryDTO, id: UUID): Mono<EntryDTO>

    fun findAll(pagination: PaginationHandler): Flux<EntryDTO>

    fun findById(id: UUID): Mono<EntryDTO>

    fun deleteById(id: UUID): Mono<DeleteOperationResult>
}