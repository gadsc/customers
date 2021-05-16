package com.gadsc.customers.infra.sns

interface SnsClient {
    fun sendNotification(snsNotification: SnsNotification)
}
