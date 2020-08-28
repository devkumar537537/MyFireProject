package com.cbitss.nziftaadmin.data.firebase

import android.app.Application
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cbitss.nziftaadmin.pojoclasses.Category
import com.cbitss.nziftaadmin.pojoclasses.Contentdatalcass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import io.reactivex.Completable

class FirebaseSource {
    var arraylist: MutableList<Category> = ArrayList()
    var contentlist: MutableList<Contentdatalcass> = ArrayList()

    lateinit var valueEventListener: ValueEventListener
    lateinit var databaseReference: DatabaseReference
    fun insert(usertype: String) = Completable.create { emitter ->
        var databaseReference = FirebaseDatabase.getInstance().getReference("Usertype")
      var key = databaseReference.push().key
        val usetypehasmap: HashMap<String,String> = HashMap()
        usetypehasmap.put("usertype",usertype)
        usetypehasmap.put("id",key.toString())

        databaseReference.child(key.toString()).setValue(usetypehasmap).addOnCompleteListener {
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
    fun admintLogin(email: String,password:String) = Completable.create { emitter ->
   var firebaseAuth = FirebaseAuth.getInstance()
    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {

    if (!emitter.isDisposed) {
        if (it.isSuccessful)
        {
            emitter.onComplete()
        }
        else {
            emitter.onError(it.exception!!)
        }
    }

}


    }
    fun insertcategory(categor:String) = Completable.create {emitter ->
        var databaseReference = FirebaseDatabase.getInstance().getReference("Categories").child(categor)

        var category: HashMap<String,String> = HashMap()
        category.put("category",categor)

        databaseReference.setValue(category).addOnCompleteListener {
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

    fun insertcontent(item_banner: String,item_title: String,degination: String, category: String,description: String) = Completable.create {emitter ->
        var databaseReference = FirebaseDatabase.getInstance().getReference("Content")
var key = databaseReference.push().key
        var contentmap: HashMap<String,String> = HashMap()
        contentmap.put("id",key.toString())
        contentmap.put("category",category)
        contentmap.put("banner_name",item_banner)
        contentmap.put("item_title",item_title)
        contentmap.put("degination",degination)
        contentmap.put("description",description)
        contentmap.put("imageone","default")

        databaseReference.child(key!!).setValue(contentmap).addOnCompleteListener {
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

fun fetchusetype() : LiveData<List<Category>> {
    var list:MutableLiveData<List<Category>> = MutableLiveData()
    databaseReference  = FirebaseDatabase.getInstance().getReference("Categories")
   valueEventListener= databaseReference.addValueEventListener(object : ValueEventListener{

        override fun onCancelled(error: DatabaseError) {
        }
       override fun onDataChange(snapshot: DataSnapshot) {
           arraylist.clear()
          for(datasnapshot in snapshot.children)
          {
              var listdata = datasnapshot.getValue(Category::class.java)
              arraylist.add(listdata!!)
              list.value = arraylist
          }
        } })
    return list
}

    fun fetchContent() : LiveData<List<Contentdatalcass>> {
        var list:MutableLiveData<List<Contentdatalcass>> = MutableLiveData()
        databaseReference  = FirebaseDatabase.getInstance().getReference("Content")
        valueEventListener= databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                contentlist.clear()
                for(datasnapshot in snapshot.children)
                {
                    var listdata = datasnapshot.getValue(Contentdatalcass::class.java)
                    contentlist.add(listdata!!)
                    list.value = contentlist
                }
            } })
        return list
    }

}