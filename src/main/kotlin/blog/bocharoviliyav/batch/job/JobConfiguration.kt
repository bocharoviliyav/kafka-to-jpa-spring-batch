package blog.bocharoviliyav.batch.job


import org.springframework.batch.core.Job
import org.springframework.batch.core.Step

import org.springframework.batch.core.job.builder.JobBuilder

import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import blog.bocharoviliyav.batch.model.Message
import blog.bocharoviliyav.batch.processor.TransformationItemProcessorConfiguration
import blog.bocharoviliyav.batch.reader.KafkaItemReaderConfiguration
import blog.bocharoviliyav.batch.writer.DbItemWriterConfiguration


@Configuration
class JobConfiguration (
    private val transactionManager: PlatformTransactionManager,
    private val kafkaItemReaderConfiguration: KafkaItemReaderConfiguration,
    private val itemProcessor: TransformationItemProcessorConfiguration,
    private val dbItemWriterConfiguration: DbItemWriterConfiguration

) {


    @Bean
    fun jobWithRetry(jobRepository: JobRepository?): Job {
        return JobBuilder("jobWithRetry", jobRepository!!)
            .start(mainStep(jobRepository))
            .build()
    }

    @Bean
    protected fun mainStep(jobRepository: JobRepository?): Step {
        return StepBuilder("mainStep", jobRepository!!)
            .chunk<String, Message>(100, transactionManager)
            .reader(kafkaItemReaderConfiguration.kafkaItemReader())
            .processor(itemProcessor)
            .writer(dbItemWriterConfiguration.itemWriter())
            .faultTolerant()
            .retry(Exception::class.java)
            .retryLimit(3)
            .build()
    }


}