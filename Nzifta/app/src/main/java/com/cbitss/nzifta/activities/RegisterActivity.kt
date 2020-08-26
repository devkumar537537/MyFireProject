package com.cbitss.nzifta.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.cbitss.nzifta.R
import com.cbitss.nzifta.databinding.ActivityRegisterBinding
import com.cbitss.nzifta.modelfactries.RegisterViewModelFactory
import com.cbitss.nzifta.viewmodels.Authlistener
import com.cbitss.nzifta.viewmodels.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.io.ByteArrayOutputStream


@Suppress("DEPRECATION")
class RegisterActivity : AppCompatActivity(),KodeinAware,Authlistener {

    lateinit var viewmodel : RegisterViewModel
    lateinit var binding: ActivityRegisterBinding
    override val kodein by kodein()
    private val factory: RegisterViewModelFactory by instance()
// lateinit var gender :String
    var filePath: Uri? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewmodel = ViewModelProviders.of(this, factory).get(RegisterViewModel::class.java)
        binding.regitsmodel = viewmodel
        viewmodel.authListener = this

        var getvalue = intent.getStringExtra("RegisterAs")
        viewmodel.usertype = getvalue





          binding.profileImagepicker.setOnClickListener {
              selectimage()
          }

//        radioGrp.setOnCheckedChangeListener { group, checkedId ->
//            if (checkedId != -1) {
//                var id = (findViewById<View>(checkedId) as RadioButton).getText().toString()
//              gender = id
//                Toast.makeText(applicationContext,"valeus is ${gender}",Toast.LENGTH_SHORT).show()
////                viewmodel.changevalue(gender)
//
//            } else {
////                viewmodel.gender = "Male"
//                Toast.makeText(applicationContext,"nothing is selected",Toast.LENGTH_SHORT).show()
//
//            }
  //      }

    }



    private fun selectimage() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.CAMERA),
                2
            )
        } else {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, 2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 2 && resultCode == Activity.RESULT_OK && data !=null )
        {


            val extras = data.extras
            val imageBitmap = extras!!["data"] as Bitmap?
            binding.profileImagepicker.setImageBitmap(imageBitmap)
            filePath = getImageUriFromBitmap(applicationContext,imageBitmap!!)
            viewmodel.imageuri = filePath

        }

    }




    override fun OnStart() {
        binding.registerProgressbar.visibility = View.VISIBLE
        Toast.makeText(applicationContext,"Started the registaration",Toast.LENGTH_SHORT).show()
    }

    override fun OnSuccess() {
        binding.registerProgressbar.visibility = View.GONE
        Toast.makeText(applicationContext,"Completed Success fully",Toast.LENGTH_SHORT).show()
    }

    override fun OnFailed(message: String) {
       binding.registerProgressbar.visibility = View.GONE
        Toast.makeText(applicationContext,"error $message",Toast.LENGTH_SHORT).show()
    }
    fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri{
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path.toString())
    }
}


