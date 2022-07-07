package io.bsamartins.blazebitpersistence

import com.blazebit.persistence.Criteria
import com.blazebit.persistence.CriteriaBuilderFactory
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.Lazy
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.persistence.EntityManagerFactory

@SpringBootApplication
@Import(PersistenceConfiguration::class)
class BlazebitPersistenceApplication

fun main(args: Array<String>) {
    runApplication<BlazebitPersistenceApplication>(*args)
}
