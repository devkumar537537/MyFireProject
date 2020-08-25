package com.cbitss.nzifta.data.repositries

import android.net.Uri
import com.cbitss.nzifta.data.firebase.FirebaseFiles

class Repositry(val firebase : FirebaseFiles) {
fun fetchUsertype() = firebase.fetchusetype()

    fun fetchCategory() = firebase.fetcItemCategory()

    fun insertUserr(
        email: String,
        password: String,
        name: String,
        number: String,
        city:String,
        age:String,
        number_of_filsm: String,
        gender:String,
          usertype : String,
        aboutwork:String,
        imaguri: Uri

    ) = firebase.insertUser(email, password, name, number, city, age, number_of_filsm, gender, aboutwork,imaguri,usertype)
    fun fetchContent(categorytag: String) = firebase.getcategoryContent(categorytag)
fun insertappliesuser(userid: String,designation: String,expected_salary: String,bried_description: String) = firebase.insertuser(userid,designation,expected_salary,bried_description)
}