package com.example.weatherapp.data

import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface IRepository<T> {
    fun getAll() : Flow<List<T>>
    fun get(id: Int) : Flow<T>
    fun insert(vararg t:T)
    fun delete(id: Int)
    fun deleteAll()
    fun update(t:T)
}