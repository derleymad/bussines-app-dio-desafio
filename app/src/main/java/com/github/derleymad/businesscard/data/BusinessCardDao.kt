package com.github.derleymad.businesscard.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BusinessCardDao {
    @Query("SELECT *  FROM BusinessCard")
    fun getAll(): LiveData<List<BusinessCard>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(businessCard: BusinessCard)

    @Delete
    fun delete(businessCard: BusinessCard)

    @Update
    fun update(businessCard: BusinessCard)

    @Query("SELECT * FROM BusinessCard WHERE id = :businessCardId")
    fun findById(businessCardId: Int): BusinessCard

}