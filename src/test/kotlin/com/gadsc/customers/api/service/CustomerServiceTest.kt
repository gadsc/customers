package com.gadsc.customers.api.service

import com.gadsc.customers.api.CustomerIndexerPublisher
import com.gadsc.customers.api.CustomerRemoverPublisher
import com.gadsc.customers.api.exception.ResourceNotFoundException
import com.gadsc.customers.api.model.Customer
import com.gadsc.customers.objectmothers.model.CustomerObjectMother
import com.gadsc.customers.api.repository.CustomerRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.repository.findByIdOrNull
import java.util.*

class CustomerServiceTest {
    private lateinit var subject: CustomerService
    private val customerRepository = mockk<CustomerRepository>(relaxed = true)
    private val customerIndexerPublisher = mockk<CustomerIndexerPublisher>(relaxed = true)
    private val customerRemoverPublisher = mockk<CustomerRemoverPublisher>(relaxed = true)

    @BeforeEach
    fun init() {
        subject = CustomerService(
            customerRepository = customerRepository,
            customerIndexerPublisher = customerIndexerPublisher,
            customerRemoverPublisher = customerRemoverPublisher
        )
    }

    @Nested
    inner class `Create customer` {
        @Test
        fun `should create customer`() {
            val customer = CustomerObjectMother.build()

            every { customerRepository.save(customer) } returns customer

            subject.create(customer)

            verify { customerRepository.save(customer) }
            verify { customerIndexerPublisher.publish(any()) }
        }
    }

    @Nested
    inner class `Find customer` {
        @Test
        fun `should find customer`() {
            val customerId = UUID.randomUUID()
            val customer = CustomerObjectMother.build()

            every { customerRepository.findByIdOrNull(customerId) } returns customer

            val foundCustomer = subject.findById(customerId)

            verify { customerRepository.findById(customerId) }

            assertEquals(customer.name, foundCustomer.name)
        }

        @Test
        fun `should throw ResourceNotFoundException when customer doesn't exists`() {
            val customerId = UUID.randomUUID()
            val expectedExceptionMessage = "Customer not found"

            every { customerRepository.findByIdOrNull(customerId) } returns null

            val exception= assertThrows<ResourceNotFoundException> {
                subject.findById(customerId)
            }

            verify { customerRepository.findById(customerId) }

            assertEquals(expectedExceptionMessage, exception.message)
        }
    }

    @Nested
    inner class `Delete customer` {
        @Test
        fun `should delete customer`() {
            val customerId = UUID.randomUUID()
            val customer = mockk<Customer>()
            val deletedCustomer = mockk<Customer>(relaxed = true)

            every { customerRepository.findByIdOrNull(customerId) } returns customer
            every { customer.logicalDelete() } returns deletedCustomer
            every { customerRepository.save(deletedCustomer) } returns deletedCustomer

            subject.delete(customerId)

            verify { customerRepository.save(deletedCustomer) }
            verify { customerRemoverPublisher.publish(any()) }
        }

        @Test
        fun `should throw ResourceNotFoundException when customer doesn't exists`() {
            val customerId = UUID.randomUUID()
            val expectedExceptionMessage = "Customer not found"

            every { customerRepository.findByIdOrNull(customerId) } returns null

            val exception= assertThrows<ResourceNotFoundException> {
                subject.delete(customerId)
            }

            verify { customerRepository.findById(customerId) }

            assertEquals(expectedExceptionMessage, exception.message)
        }
    }

    @Nested
    inner class `Find all customers` {
        @Test
        fun `should find all customers`() {
            val customers = emptyList<Customer>()

            every { customerRepository.findAll() } returns customers

            val existingCustomers = subject.findAll()

            verify { customerRepository.findAll() }
            assertTrue(existingCustomers.isEmpty())
        }
    }

    @Nested
    inner class `Update customer` {
        @Test
        fun `should update customer`() {
            val customerId = UUID.randomUUID()
            val customer = mockk<Customer>()
            val updatedCustomer = mockk<Customer>(relaxed = true)

            every { customerRepository.findByIdOrNull(customerId) } returns customer
            every { customer.update(updatedCustomer) } returns updatedCustomer
            every { customerRepository.save(updatedCustomer) } returns updatedCustomer

            subject.update(customerId, updatedCustomer)

            verify { customerRepository.save(updatedCustomer) }
            verify { customerIndexerPublisher.publish(any()) }
        }

        @Test
        fun `should throw ResourceNotFoundException when customer doesn't exists`() {
            val customerId = UUID.randomUUID()
            val customer = CustomerObjectMother.build()
            val expectedExceptionMessage = "Customer not found"

            every { customerRepository.findByIdOrNull(customerId) } returns null

            val exception= assertThrows<ResourceNotFoundException> {
                subject.update(customerId, customer)
            }

            verify { customerRepository.findById(customerId) }

            assertEquals(expectedExceptionMessage, exception.message)
        }
    }
}
