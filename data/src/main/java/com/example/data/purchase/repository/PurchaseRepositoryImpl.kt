package com.example.data.purchase.repository

import com.example.data.purchase.mappers.toDomain
import com.example.data.purchase.model.Root
import com.example.domain.model.Purchase
import com.example.domain.repository.PurchaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.json.Json

class PurchaseRepositoryImpl: PurchaseRepository {
    private val rawJson = """
    {
       "data":[
          {
             "date":"2022-09-10T21:55:33Z",
             "name":[
                "123",
                "321"
             ]
          },
          {
             "date":"2022-09-10T21:50:33Z",
             "name":[
                "1234",
                "4321"
             ]
          },
          {
             "date":"2022-09-08T01:55:33Z",
             "name":[
                "12345",
                "54321"
             ]
          },
          {
             "date":"2022-09-07T21:55:33Z",
             "name":[
                "123456",
                "654321"
             ]
          },
          {
             "date":"2022-09-07T11:55:33Z",
             "name":[
                "1234567",
                "7654321"
             ]
          }
       ]
    }
    """.trimIndent()

    override fun getPurchases(): Flow<List<Purchase>> = flow {
        val root = Json.decodeFromString<Root>(rawJson)
        emit(root.data.map { it.toDomain() })
    }.flowOn(Dispatchers.IO)
}
