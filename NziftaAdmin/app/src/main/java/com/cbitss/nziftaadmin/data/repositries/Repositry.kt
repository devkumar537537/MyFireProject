package com.cbitss.nziftaadmin.data.repositries

import com.cbitss.nziftaadmin.data.firebase.FirebaseSource

class Repositry (
    private val firebastmethod: FirebaseSource
) {

    fun insertusertype(usertype: String) = firebastmethod.insert(usertype)
    fun insertcategory(category:String) =  firebastmethod.insertcategory(category)
    fun fetchContent() = firebastmethod.fetchContent()

    fun fetchusertype() = firebastmethod.fetchusetype()
    fun adminLogin(email: String,password : String)  =  firebastmethod.admintLogin(email, password)

    fun insertcontent(itembanner: String,item_title: String,degination: String,category: String,description: String) =firebastmethod.insertcontent(itembanner,item_title, degination, category, description)
}