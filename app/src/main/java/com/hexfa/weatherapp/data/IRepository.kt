package com.hexfa.weatherapp.data

import kotlinx.coroutines.flow.Flow

interface IRepository<T> {
    fun get() : Flow<T>
    suspend fun insert( t:T)
    suspend fun delete(id: Int)
   suspend fun update(t:T)
}