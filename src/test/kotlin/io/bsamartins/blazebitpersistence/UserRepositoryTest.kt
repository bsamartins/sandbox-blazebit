package io.bsamartins.blazebitpersistence

import com.blazebit.persistence.PagedList
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.test.context.TestPropertySource

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Import(PersistenceConfiguration::class)
@TestPropertySource(
    properties = [
        "debug=true",
        "spring.datasource.url=jdbc:tc:postgresql:13.2:////test",
        "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
    ]
)
class UserRepositoryTest {

    private val pageSize = 5

    @Autowired
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setup() {
        (1..1000).forEach {
            userRepository.save(User(name = it.toString()))
        }
    }

    @Test
    fun testKeyset() {
        var result: PagedList<User>? = null
        timed {
            do {
                result = userRepository.findAllUsers(previousPage = result, maxResults = pageSize)
            } while (result!!.hasMore())
        }
    }

    @Test
    fun testPaged() {
        var result: Page<User>?
        var pageable = Pageable.ofSize(pageSize)
        timed {
            do {
                result = userRepository.findAll(pageable)
                pageable = result!!.nextPageable()
            } while (result!!.hasNext())
        }
    }
}

private fun <T> PagedList<T>.hasMore(): Boolean {
    return page < totalPages
}

private fun timed(block: () -> Unit) {
    val startTime = System.currentTimeMillis()
    block()
    val elapsed = System.currentTimeMillis() - startTime
    println("Execution time: ${elapsed}ms")
}
