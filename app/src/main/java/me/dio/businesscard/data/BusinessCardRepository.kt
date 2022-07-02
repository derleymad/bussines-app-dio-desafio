package me.dio.businesscard.data

import android.content.pm.LauncherActivityInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch

class BusinessCardRepository (private val dao : BusinessCardDao){
    fun insert(businessCard: BusinessCard) = runBlocking {
        launch(Dispatchers.IO){
           dao.insert(businessCard)
        }
    }

    fun getAll() = dao.getAll()

}