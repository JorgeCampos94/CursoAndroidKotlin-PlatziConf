package com.example.platzi.network.interfaces

import java.lang.Exception

interface Callback<T>{
    //resultado exitoso
    fun OnSuccess(result: T?)
    //resultado fallido
    fun OnFailed(exception : Exception)
}