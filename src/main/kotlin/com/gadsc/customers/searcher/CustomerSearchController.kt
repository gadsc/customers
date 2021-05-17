package com.gadsc.customers.searcher

import com.gadsc.customers.searcher.dto.SearchCustomerDTO
import com.gadsc.customers.searcher.service.CustomerSearchService
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
}
