package com.gadsc.customers.searcher

import com.gadsc.customers.searcher.model.SearchableCustomer
import com.gadsc.customers.searcher.query.dto.CustomerQueryDTO
import com.gadsc.customers.searcher.service.CustomerSearchService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/search/customers")
class CustomerSearchController(
    private val customerSearchService: CustomerSearchService
) {
//    @PostMapping
//    fun create(@RequestBody product: SearchableCustomer) = customerSearchService.createProduct(product)

    @PostMapping
    fun search(@RequestBody customerQueryDTO: CustomerQueryDTO) =
        customerSearchService.findBy(customerQueryDTO)
}
