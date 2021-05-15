package com.gadsc.customers.objectmothers.model

import com.gadsc.customers.model.Naturalness

object NaturalnessObjectMother {
    fun build(
        cityOfBirth: String? = "Customer City",
        stateOfBirth: String? = "Customer State",
        countryOfBirth: String? = "Customer Country",
        nationality: String = "Customer Nationality"
    ): Naturalness = Naturalness(
        cityOfBirth = cityOfBirth,
        stateOfBirth = stateOfBirth,
        countryOfBirth = countryOfBirth,
        nationality = nationality
    )
}
