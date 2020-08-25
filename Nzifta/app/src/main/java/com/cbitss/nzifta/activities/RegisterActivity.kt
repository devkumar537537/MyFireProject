package com.cbitss.nzifta.activities

import android.Manifest
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
      val CAMERA_REQUEST = 1
    val MY_CAMERA_PERMISSION_CODE = 2
    var filePath: Uri? = null


    lateinit var bitmap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewmodel = ViewModelProviders.of(this, factory).get(RegisterViewModel::class.java)
        binding.regitsmodel = viewmodel

        var getvalue = intent.getStringExtra("RegisterAs")
        viewmodel.usertype = getvalue

        viewmodel.visiblitylogic()



          binding.profileImagepicker.setOnClickListener {
              selectimage()
          }

    }



    private fun selectimage() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
        } else {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK)
        {
            if(data != null)
            {
                 bitmap  = data.extras?.get("data") as Bitmap

                binding.profileImagepicker.setImageBitmap(bitmap)

                filePath = getImageUri(applicationContext,bitmap)
                if(filePath != null )
                {
                    viewmodel.imageuri = filePath
                }


            }
            }

        }
    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.getContentResolver(),
            inImage,
            "Title",
            null
        )
        return Uri.parse(path)
    }

    fun radiot_button_click(view: View)
    {
        val radio: RadioButton = findViewById(binding.radioGrp.checkedRadioButtonId)

        viewmodel.gender = radio.text.toString()
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
}


