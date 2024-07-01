package com.hexfa.weatherapp.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ResourceState<T> (value : T) {
    private var _value by mutableStateOf(value)

    var value: T
        get() = _value
        set(newValue) {
            _value = newValue
        }
}