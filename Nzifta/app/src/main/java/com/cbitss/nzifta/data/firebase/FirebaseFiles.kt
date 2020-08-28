package com.cbitss.nzifta.data.firebase

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cbitss.nzifta.pojoclass.Category
import com.cbitss.nzifta.pojoclass.ContentClass
import com.cbitss.nzifta.pojoclass.RegisterAs
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Completable
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class FirebaseFiles {



    //    var arraylist: MutableList<Category> = ArrayList()
    var usertypelist: MutableList<RegisterAs> = ArrayList()
    var ItemCategorylist: MutableList<Category> = ArrayList()
    var ItemContentList: MutableList<ContentClass> = ArrayList()


    fun fetchusetype(): LiveData<List<RegisterAs>> {
        var list: MutableLiveData<List<RegisterAs>> = MutableLiveData()
      val  databaseReference = FirebaseDatabase.getInstance().getReference("Usertype")
      databaseReference.addValueEventListener(object : ValueEventListener {

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
       val  databaseReference = FirebaseDatabase.getInstance().getReference("Categories")
         databaseReference.addValueEventListener(object : ValueEventListener {

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
       val databaseReference = FirebaseDatabase.getInstance().getReference("Content")
         databaseReference.addValueEventListener(object : ValueEventListener {

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

fun inserregisteruser(
    email: String,
    password: String,
    name: String,
    number: String,
    city: String,
    aboutword: String,
    usertype: String
) = Completable.create {emitter ->
    val userid = FirebaseAuth.getInstance().currentUser!!.uid
     val databaseReference = FirebaseDatabase.getInstance().getReference("RegisterUser").child(userid)

  var registmap: MutableMap<String, String> = hashMapOf(
      "useremail" to email,
      "userPassword" to password,
      "userName" to name,
      "userNumber" to number,
      "userCity" to city,
      "aboutword" to aboutword,
      "usertype" to usertype,
      "imagerurl" to "default",
      "gender" to "gender",
      "working_status" to "Not working",
      "Which_project" to "AbcdProjcet"

  )
   databaseReference.setValue(registmap).addOnCompleteListener {

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

   }


}

    fun insertcontent(useridd: String,designation: String,expectedsalar: String,brief_descripton:String) = Completable.create {emitter ->

         var userId  = FirebaseAuth.getInstance().currentUser!!.uid
        var databaseReference = FirebaseDatabase.getInstance().getReference("AppliedUsers").child(userId)

        var contentmap: HashMap<String,String> = HashMap()
        contentmap.put("userid",userId)
        contentmap.put("desigantion",designation)
        contentmap.put("expected_Salary",expectedsalar)
        contentmap.put("brief_description",brief_descripton)
        contentmap.put("nodeId",useridd)

        databaseReference.setValue(contentmap).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful)
                {
                    emitter.onComplete()
                }
                else
                   { emitter.onError(it.exception!!)}
            }
        }
    }

}

