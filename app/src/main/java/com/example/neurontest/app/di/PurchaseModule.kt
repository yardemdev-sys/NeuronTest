package com.example.neurontest.app.di

import com.example.data.purchase.repository.PurchaseRepositoryImpl
import com.example.domain.repository.PurchaseRepository
import com.example.domain.usecase.purchase.GetPurchasesUseCase
import org.koin.dsl.module

val purchaseModule = module {
    single<PurchaseRepository> {
        PurchaseRepositoryImpl()
    }

    factory<GetPurchasesUseCase> {
        GetPurchasesUseCase(get())
    }
}