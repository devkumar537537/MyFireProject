package com.cbitss.nzifta.viewmodels

interface Authlistener {
    fun OnStart()
    fun OnSuccess()
    fun OnFailed(message: String)
}