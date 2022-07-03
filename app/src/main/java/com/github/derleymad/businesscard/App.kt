package com.github.derleymad.businesscard

import android.app.Application
import com.github.derleymad.businesscard.data.AppDatabase
import com.github.derleymad.businesscard.data.BusinessCardRepository

class App : Application() {
    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
    val repository: BusinessCardRepository by lazy {
        BusinessCardRepository(database.businessCardDao())
    }
}