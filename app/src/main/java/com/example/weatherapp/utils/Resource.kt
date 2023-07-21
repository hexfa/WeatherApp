package com.example.weatherapp.utils

class Resource<out T> constructor(val state: State,val msg:String?,val data:T?) {
        companion object{
            fun<T> success(data:T?) = Resource<T>(State.SUCCESS,null,data)
            fun<T> loading() = Resource<T>(State.LOADING,null,null)
            fun<T> error(msg: String?) = Resource<T>(State.SUCCESS,msg,null)
        }
}