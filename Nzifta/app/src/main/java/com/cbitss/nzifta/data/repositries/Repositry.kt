package com.cbitss.nzifta.data.repositries

import android.net.Uri
import com.cbitss.nzifta.data.firebase.FirebaseFiles

class Repositry(val firebase : FirebaseFiles) {
fun fetchUsertype() = firebase.fetchusetype()

    fun fetchCategory() = firebase.fetcItemCategory()

fun insertrpostryapplieduser(
    email: String,
    password: String,
    name: String,
    number: String,
    city: String,
    aboutword: String,
    usertype: String
) = firebase.inserregisteruser(email, password, name, number, city, aboutword, usertype)
    fun fetchContent(categorytag: String) = firebase.getcategoryContent(categorytag)
fun repositryInsertuser(userid: String,designation: String,expected_salary:String,brief_description: String) = firebase.insertcontent(
    userid,designation,expected_salary,brief_description
)
}