package com.gadsc.customers.objectmothers.dto

import com.gadsc.customers.api.dto.PhoneDTO

object PhoneDTOObjectMother {
    fun build(number: String = "+5511999999999", type: String = "CELLPHONE"): PhoneDTO =
        PhoneDTO(number = number, type = type)
}
