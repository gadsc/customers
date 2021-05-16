package com.gadsc.customers.service

import com.gadsc.customers.CustomerIndexerPublisher
import com.gadsc.customers.dto.CustomerDTO
import com.gadsc.customers.exception.ResourceNotFoundException
import com.gadsc.customers.model.Customer
import com.gadsc.customers.repository.CustomerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val customerIndexerPublisher: CustomerIndexerPublisher
) {
    fun create(customer: Customer): Customer =
        customerRepository.save(customer)
            .apply { customerIndexerPublisher.publish(CustomerDTO.fromDomain(this)) }

    fun findById(id: UUID): Customer = customerRepository.findByIdOrNull(id) ?: throw ResourceNotFoundException("Customer not found")

    fun delete(id: UUID) {
        val customerToDelete = customerRepository.findByIdOrNull(id)  ?: throw ResourceNotFoundException("Customer not found")

        customerRepository.save(customerToDelete.logicalDelete())
    }

    fun findAll(): List<Customer> = customerRepository.findAll().toList()

    fun update(id: UUID, customer: Customer): Customer {
        val customerToUpdate = customerRepository.findByIdOrNull(id)  ?: throw ResourceNotFoundException("Customer not found")

        return customerRepository.save(customerToUpdate.update(customer))
    }
}
