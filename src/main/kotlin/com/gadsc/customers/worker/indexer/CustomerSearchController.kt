package com.gadsc.customers.worker.indexer

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/search/customers")
class CustomerSearchController(
    private val customerSearchService: CustomerSearchService
) {
//    @PostMapping
//    fun create(@RequestBody product: CustomerSearch) = customerSearchService.createProduct(product)

    @PostMapping
    fun search(@RequestBody searchCustomerDTO: SearchCustomerDTO) =
        customerSearchService.findBy(searchCustomerDTO)

    @GetMapping
    fun findByName(@RequestParam(name = "name") name: String) =
        customerSearchService.findByNameOrCategory(name)
}
