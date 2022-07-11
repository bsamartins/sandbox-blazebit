package io.bsamartins.blazebitpersistence

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Repository
interface UserRepository : PagingAndSortingRepository<User, Long>, UserCustomRepository

@Entity
@Table(name = "\"user\"")
data class User(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var name: String
)
