package com.gadsc.customers.infra

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import kotlin.reflect.KClass

@Component
class EntityManagerAdapter(private val entityManager: EntityManager) {
    @Transactional
    fun deleteAll(givenEntity: KClass<*>) {
        val deleteQuery = "TRUNCATE ${givenEntity.simpleName} RESTART IDENTITY CASCADE"
        println(deleteQuery)
        entityManager.createNativeQuery(deleteQuery).executeUpdate()
        flushAndClear()
    }

    private fun flushAndClear() {
        entityManager.flush()
        entityManager.clear()
    }
}
