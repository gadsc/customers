package com.gadsc.customers

import com.gadsc.customers.model.Customer
import com.gadsc.customers.service.CustomerService
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
    fun create(@RequestBody customer: Customer) = customerService.create(customer)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll() = customerService.findAll()

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable("id") id: UUID) =
        customerService.findById(id) ?: throw RuntimeException("Customer not found")

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(@PathVariable("id") id: UUID, @RequestBody customer: Customer) =
        customerService.update(customer)

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun partialUpdate(@PathVariable("id") id: UUID, @RequestBody customer: Customer) =
        customerService.update(customer)
}
