package pl.piotrkniemczuk.journalreactive.model

import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.Date
import java.util.UUID

@Table("entry")
class Entry(
        @PrimaryKey
        val id: UUID?,
        var title: String,
        var content: String,
        val createdAt: Date
){

        override fun toString(): String {
                return "Entry(id=$id, title='$title', content='$content', createdAt=$createdAt)"
        }
}