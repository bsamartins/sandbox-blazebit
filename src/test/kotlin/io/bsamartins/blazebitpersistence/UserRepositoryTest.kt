package io.bsamartins.blazebitpersistence

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Import(PersistenceConfiguration::class)
@TestPropertySource(
    properties = [
        "spring.datasource.url=jdbc:tc:postgresql:13.2:////test",
        "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
    ]
)
class UserRepositoryTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    fun test() {
        (1..30).forEach {
            userRepository.save(User(name = it.toString()))
        }
        userRepository.findAll().forEach {
            println(it)
        }
    }
}
