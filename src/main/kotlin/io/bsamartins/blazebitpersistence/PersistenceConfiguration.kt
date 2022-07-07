package io.bsamartins.blazebitpersistence

import com.blazebit.persistence.Criteria
import com.blazebit.persistence.CriteriaBuilderFactory
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Scope
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.persistence.EntityManagerFactory

@Configuration
@EnableJpaRepositories(basePackages = ["io.bsamartins.blazebitpersistence"])
class PersistenceConfiguration {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Lazy(false)
    fun createCriteriaBuilderFactory(entityManagerFactory: EntityManagerFactory): CriteriaBuilderFactory? {
        val config = Criteria.getDefault()
        return config.createCriteriaBuilderFactory(entityManagerFactory)
    }
}
