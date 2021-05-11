package com.gadsc.customers.service

import com.gadsc.customers.model.Customer
import com.gadsc.customers.repository.CustomerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(private val customerRepository: CustomerRepository) {
    fun create(customer: Customer): Customer = customerRepository.save(customer)

    fun findById(id: UUID): Customer = customerRepository.findByIdOrNull(id) ?: throw RuntimeException("Customer not found")

    fun delete(id: UUID) {
        val customerToDelete = customerRepository.findByIdOrNull(id)  ?: throw RuntimeException("Customer not found")

        customerRepository.save(customerToDelete.logicalDelete())
    }

    fun findAll() = customerRepository.findAll()

    fun update(id: UUID, customer: Customer): Customer {
        val customerToUpdate = customerRepository.findByIdOrNull(id)  ?: throw RuntimeException("Customer not found")

        return customerRepository.save(customerToUpdate.update(customer))
    }
}
