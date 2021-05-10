package com.gadsc.customers.service

import com.gadsc.customers.model.Customer
import com.gadsc.customers.repository.CustomerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(private val customerRepository: CustomerRepository) {
    fun create(customer: Customer): Customer = customerRepository.save(customer)

    fun findById(id: UUID): Customer? = customerRepository.findByIdOrNull(id)

    fun delete(customer: Customer) = customerRepository.delete(customer)

    fun findAll() = customerRepository.findAll()

    fun update(customer: Customer) = customerRepository.save(customer)
}
