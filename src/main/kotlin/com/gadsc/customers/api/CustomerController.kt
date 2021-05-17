package com.gadsc.customers.api

import com.gadsc.customers.api.dto.CustomerDTO
import com.gadsc.customers.api.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerService: CustomerService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: CustomerDTO) =
        CustomerDTO.fromDomain(customerService.create(customer.toDomain()))

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll() = customerService.findAll().map { CustomerDTO.fromDomain(it) }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable("id") id: UUID): CustomerDTO =
        CustomerDTO.fromDomain(customerService.findById(id))

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable("id") id: UUID, @RequestBody customer: CustomerDTO) =
        CustomerDTO.fromDomain(customerService.update(id, customer.toDomain()))

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable("id") id: UUID) =
        customerService.delete(id)
}
