package com.cbitss.nziftaadmin.viewmodels

interface Authlistener {
    fun OnSuccess()
    fun OnFailed(message:String)
    fun OnStart()
}