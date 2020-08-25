package com.cbitss.nziftaadmin.Fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cbitss.nziftaadmin.R
import com.cbitss.nziftaadmin.adapters.ContentAdapter
import com.cbitss.nziftaadmin.databinding.FragmentAddContentBinding
import com.cbitss.nziftaadmin.modelfactories.ContentViewModelFactory
import com.cbitss.nziftaadmin.pojoclasses.Category
import com.cbitss.nziftaadmin.pojoclasses.Contentdatalcass
import com.cbitss.nziftaadmin.viewmodels.AddContentViewModel
import com.cbitss.nziftaadmin.viewmodels.Authlistener
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


@Suppress("DEPRECATION")
class AddContent : Fragment() ,Authlistener,KodeinAware {
    lateinit var binding: FragmentAddContentBinding
    lateinit var viewmodel: AddContentViewModel
    override val kodein by kodein()
 var usertypearray : MutableList<String> = ArrayList()
    val default_text = "Select Category"
    private val factory: ContentViewModelFactory by instance()


    override fun onStart() {
        super.onStart()
        getusertype()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_content, container, false)
        viewmodel = ViewModelProviders.of(this, factory).get(AddContentViewModel::class.java)
        binding.contentviewmodels = viewmodel
        binding.lifecycleOwner = this
        viewmodel.authListener = this

        initisevalueinspinner()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setrecyclerview(view)
    }

    private fun setrecyclerview(view: View) {


        var list: MutableList<Contentdatalcass> = ArrayList()
        viewmodel.getcontentData().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
           list.clear()
            if (it != null) {

                for (i in it) {
                  list.add(i)
                    binding.apply {
                        contentRecyclerview.layoutManager = LinearLayoutManager(view?.context)
                        var apapter = ContentAdapter(list,view.context)
                        contentRecyclerview.adapter = apapter

                    }
                }
            }
        })

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun initisevalueinspinner() {

         usertypearray.add(0,"Select Category")
        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, usertypearray)
        binding.spinner.adapter = dataAdapter
        binding.spinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                viewmodel.spinnercategory_text = parent.getItemAtPosition(position).toString()
//                Toast.makeText(
//                    parent.context,
//                    " Default ${parent.getItemAtPosition(position)} Selected",
//                    Toast.LENGTH_LONG
//                ).show();
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
                Toast.makeText(requireContext(), "Nothing is selected", Toast.LENGTH_LONG).show();
            }
        })
    }

    override fun OnSuccess() {
        binding.contentProgressbasr.visibility = View.GONE
        Toast.makeText(view?.context, "Content register Successfully", Toast.LENGTH_SHORT).show()
    }

    override fun OnFailed(message: String) {
        binding.contentProgressbasr.visibility = View.GONE
        Toast.makeText(view?.context, "error: ${message}", Toast.LENGTH_LONG).show()
    }

    override fun OnStart() {
        binding.contentProgressbasr.visibility = View.VISIBLE
    }

    private fun getusertype() {
        var list: MutableList<Category> = ArrayList()
        viewmodel.getallusertype().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            usertypearray.clear()
            if (it != null) {

                for (i in it) {
                    usertypearray.add(i.category.toString())
                }
            }
        })


    }


    override fun onDestroyView() {
        super.onDestroyView()
        usertypearray.clear()
    }



}


