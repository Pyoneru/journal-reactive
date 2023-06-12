package pl.piotrkniemczuk.journalreactive.repository


import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import pl.piotrkniemczuk.journalreactive.model.Entry
import java.util.UUID

interface EntryRepository : ReactiveCassandraRepository<Entry, UUID> {
}