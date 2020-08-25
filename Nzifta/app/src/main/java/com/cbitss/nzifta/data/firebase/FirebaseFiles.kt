package com.cbitss.nzifta.data.firebase

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cbitss.nzifta.pojoclass.Category
import com.cbitss.nzifta.pojoclass.ContentClass
import com.cbitss.nzifta.pojoclass.RegisterAs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Completable
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class FirebaseFiles : AppCompatActivity() {

    //    var arraylist: MutableList<Category> = ArrayList()
    var usertypelist: MutableList<RegisterAs> = ArrayList()
    var ItemCategorylist: MutableList<Category> = ArrayList()
    var ItemContentList: MutableList<ContentClass> = ArrayList()
    lateinit var valueEventListener: ValueEventListener
    lateinit var databaseReference: DatabaseReference

    fun fetchusetype(): LiveData<List<RegisterAs>> {
        var list: MutableLiveData<List<RegisterAs>> = MutableLiveData()
        databaseReference = FirebaseDatabase.getInstance().getReference("Usertype")
        valueEventListener = databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                usertypelist.clear()
                for (datasnapshot in snapshot.children) {
                    var listdata = datasnapshot.getValue(RegisterAs::class.java)
                    usertypelist.add(listdata!!)
                    list.value = usertypelist
                }
            }
        })
        return list
    }
    fun fetcItemCategory(): LiveData<List<Category>> {
        var list: MutableLiveData<List<Category>> = MutableLiveData()
        databaseReference = FirebaseDatabase.getInstance().getReference("Categories")
        valueEventListener = databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                ItemCategorylist.clear()
                for (datasnapshot in snapshot.children) {
                    var listdata = datasnapshot.getValue(Category::class.java)
                    ItemCategorylist.add(listdata!!)
                    list.value = ItemCategorylist
                }
            }
        })
        return list
    }
    fun getcategoryContent(categorytag: String): LiveData<List<ContentClass>> {
        var list: MutableLiveData<List<ContentClass>> = MutableLiveData()
        databaseReference = FirebaseDatabase.getInstance().getReference("Content")
        valueEventListener = databaseReference.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                ItemCategorylist.clear()
                for (datasnapshot in snapshot.children) {
                    var listdata = datasnapshot.getValue(ContentClass::class.java)
                    if(listdata!!.category.equals(categorytag))
                    {
                        ItemContentList.add(listdata)
                        list.value = ItemContentList
                    }

                }
            }
        })
        return list
    }
    fun insertUser(
        email: String,
        password: String,
        name: String,
        number: String,
        city: String,
        age: String,
        number_of_filsm: String,
        gender: String,
        aboutwork: String,
        profileimage: Uri,
        usertype: String


    ) = Completable.create { emitter ->
        var userid = FirebaseAuth.getInstance().currentUser!!.uid
        var databaseReference =
            FirebaseDatabase.getInstance().getReference("Register").child(userid)

        var registhastmap: HashMap<String, String> = HashMap()
        registhastmap.put("id", userid)
        registhastmap.put("email", email)
        registhastmap.put("password", password)
        registhastmap.put("name", name)
        registhastmap.put("number", number)
        registhastmap.put("city", city)
        registhastmap.put("age", age)
        registhastmap.put("number_of_filsm", number_of_filsm)
        registhastmap.put("gender", gender)
        registhastmap.put("aboutwork", aboutwork)
        registhastmap.put("usertype", usertype)
        registhastmap.put("imageurl", "default")

        databaseReference.setValue(registhastmap).addOnCompleteListener {
            if(!emitter.isDisposed)
            {
                if(it.isSuccessful)
                {
                    emitter.onComplete()
                }else
                {
                    emitter.onError(it.exception!!)
                }

        }

            if (it.isSuccessful) {
              var storageReference = FirebaseStorage.getInstance().getReference("UserImages").child(userid).child("images/"+ UUID.randomUUID().toString())
storageReference.putFile(profileimage).addOnCompleteListener {



        if (it.isSuccessful) {
            storageReference.getDownloadUrl().addOnSuccessListener {
                if (it != null) {
                    var imagurl = it.toString()
                    var databaseReference =
                        FirebaseDatabase.getInstance().getReference("Register").child(userid)
                    databaseReference.child("imageurl").setValue(imagurl)
                  emitter.onComplete()
                }

            }
        }
    }
}




        }


    }


    fun insertcontent(userid: String,designation: String,expectedsalar: String,brief_descripton:String) = Completable.create {emitter ->
        var databaseReference = FirebaseDatabase.getInstance().getReference("AppliedUsers")

        var contentmap: HashMap<String,String> = HashMap()
        contentmap.put("userid",userid)
        contentmap.put("desigantion",designation)
        contentmap.put("expected_Salary",expectedsalar)
        contentmap.put("brief_description",brief_descripton)

        databaseReference.child(userid).setValue(contentmap).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                {
                    emitter.onComplete()
                }
                else
                    emitter.onError(it.exception!!)
            }
        }
    }

}