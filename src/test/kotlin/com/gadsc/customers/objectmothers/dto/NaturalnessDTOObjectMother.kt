package com.gadsc.customers.objectmothers.dto

import com.gadsc.customers.api.dto.NaturalnessDTO

object NaturalnessDTOObjectMother {
    fun build(
        cityOfBirth: String? = "Customer City",
        stateOfBirth: String? = "Customer State",
        countryOfBirth: String? = "Customer Country",
        nationality: String = "Customer Nationality"
    ): NaturalnessDTO = NaturalnessDTO(
        cityOfBirth = cityOfBirth,
        stateOfBirth = stateOfBirth,
        countryOfBirth = countryOfBirth,
        nationality = nationality
    )
}
