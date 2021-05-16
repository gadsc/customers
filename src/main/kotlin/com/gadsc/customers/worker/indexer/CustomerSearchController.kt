package com.gadsc.customers.worker.indexer

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/search/customers")
class CustomerSearchController(
    private val customerSearchService: CustomerSearchService
) {
    @PostMapping
    fun create(@RequestBody product: CustomerSearch) = customerSearchService.createProduct(product)

    @GetMapping
    fun findByName(@RequestParam(name = "name") name: String) = customerSearchService.findByNameOrCategory(name)
}
