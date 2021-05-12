package com.gadsc.customers

import com.gadsc.customers.infra.EntityManagerAdapter
import com.gadsc.customers.model.Customer
import com.gadsc.customers.repository.CustomerRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
internal class CustomerControllerTest {

    @Autowired
    private lateinit var entityManager: EntityManagerAdapter

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var personRepository: CustomerRepository

    @Nested
    inner class `Create Customer` {

        @Test
        fun `should create and return status created for customer with all fields`(){
        }

        @Test
        fun `should create and return status created for customer without optional fields`(){
        }

        @Test
        fun `shouldn't create and return status bad request for customer with required fields`(){
        }
    }

    @AfterEach
    fun afterEach() {
        entityManager.deleteAll(Customer::class)
    }
}
