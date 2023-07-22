package com.example.weatherapp.data

import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface IRepository<T> {
    fun get() : Flow<T>
    suspend fun insert( t:T)
    suspend fun delete(id: Int)
   suspend fun update(t:T)
}