package pl.piotrkniemczuk.journalreactive.config

import io.github.serpro69.kfaker.Faker
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import pl.piotrkniemczuk.journalreactive.model.Entry
import pl.piotrkniemczuk.journalreactive.repository.EntryRepository
import pl.piotrkniemczuk.journalreactive.utils.Utils
import java.util.UUID
import kotlin.random.Random

@Component
class DBInitializer(
        val entryRepository: EntryRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val faker = Faker()

        val entries = mutableSetOf<Entry>()
        for(i in 0 until 1000){
            val entry = Entry(
                    id = UUID.randomUUID(),
                    title = title(faker),
                    content = content(faker),
                    createdAt = Utils.now()
            )
            entries.add(entry)
        }

        entryRepository.saveAll(entries)
                .subscribe(::println)
    }

    private fun title(faker: Faker): String {
        return (faker.witcher.witchers() + "-" + faker.witcher.characters() + "-" + faker.witcher.schools())
    }

    private fun content(faker: Faker): String {

        val builder = StringBuilder()
        for(i in 0 until Random.nextInt(20, 100)){
            builder.append(faker.lorem.words())
            builder.append(' ')
        }
        return builder.toString()
    }
}