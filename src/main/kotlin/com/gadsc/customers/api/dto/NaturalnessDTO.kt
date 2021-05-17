package com.gadsc.customers.api.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.gadsc.customers.api.model.Naturalness

class NaturalnessDTO(
    @JsonProperty("city_of_birth")
    val cityOfBirth: String?,
    @JsonProperty("state_of_birth")
    val stateOfBirth: String?,
    @JsonProperty("country_of_birth")
    val countryOfBirth: String?,
    @JsonProperty("nationality")
    val nationality: String
) {
    companion object {
        fun fromDomain(naturalness: Naturalness): NaturalnessDTO = NaturalnessDTO(
            cityOfBirth = naturalness.cityOfBirth,
            stateOfBirth = naturalness.stateOfBirth,
            countryOfBirth = naturalness.countryOfBirth,
            nationality = naturalness.nationality
        )
    }

    fun toDomain(): Naturalness = Naturalness(
        cityOfBirth = this.cityOfBirth,
        stateOfBirth = this.stateOfBirth,
        countryOfBirth = this.countryOfBirth,
        nationality = this.nationality
    )
}
