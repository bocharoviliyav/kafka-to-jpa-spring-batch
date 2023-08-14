package blog.bocharoviliyav.batch.reader

import org.springframework.batch.item.kafka.KafkaItemReader
import org.springframework.batch.item.kafka.builder.KafkaItemReaderBuilder
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class KafkaItemReaderConfiguration(
    private val properties: KafkaProperties,
) {
    @Bean
    fun kafkaItemReader(): KafkaItemReader<String, String> {
        val props = Properties()
        props.putAll(properties.buildConsumerProperties())

        return KafkaItemReaderBuilder<String, String>()
            .partitions(0)
            .consumerProperties(props)
            .name("customers-reader")
            .saveState(true)
            .topic("test-consumer")
            .build()
    }

}