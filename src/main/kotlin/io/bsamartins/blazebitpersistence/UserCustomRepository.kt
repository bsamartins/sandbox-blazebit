package io.bsamartins.blazebitpersistence

import com.blazebit.persistence.CriteriaBuilderFactory
import com.blazebit.persistence.PagedList
import javax.persistence.EntityManager

interface UserCustomRepository {
    fun findAllUsers(previousPage: PagedList<User>? = null, maxResults: Int): PagedList<User>
}

class UserCustomRepositoryImpl(
    private val criteriaBuilderFactory: CriteriaBuilderFactory,
    private val entityManager: EntityManager,
) : UserCustomRepository {
    override fun findAllUsers(previousPage: PagedList<User>?, maxResults: Int): PagedList<User> {
        val firstResult = previousPage?.let { it.page * it.maxResults } ?: 0
        val maxRes = previousPage?.maxResults ?: maxResults
        return criteriaBuilderFactory.create(entityManager, User::class.java)
            .orderByAsc(User::name.name)
            .orderByAsc(User::id.name)
            .page(previousPage?.keysetPage, firstResult, maxRes)
            .resultList
    }
}
