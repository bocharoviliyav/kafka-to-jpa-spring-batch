package blog.bocharoviliyav.batch

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableBatchProcessing

class DataPipelineApplication

fun main(args: Array<String>) {
	runApplication<DataPipelineApplication>(*args)
}
