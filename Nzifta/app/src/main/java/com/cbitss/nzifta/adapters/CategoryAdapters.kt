package com.cbitss.nzifta.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cbitss.nzifta.Fragments.CategoryButtonFragment
import com.cbitss.nzifta.R
import com.cbitss.nzifta.pojoclass.Category

class CategoryAdapters (val list: MutableList<Category>,var view: View ) : RecyclerView.Adapter<CategoryAdapters.MyViewHolder>()
{
    inner class MyViewHolder(itemview: View): RecyclerView.ViewHolder(itemview)
    {
        lateinit var CategoryButton: TextView
        init {
            this.CategoryButton = itemview.findViewById(R.id.categoryButton)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.catgoryformat,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       var list = list.get(position)
        holder.CategoryButton.text = list.category
        holder.CategoryButton.setOnClickListener {
val bundle = bundleOf("categoryname" to list.category.toString())
            view.findNavController().navigate(R.id.action_categoryButtonFragment_to_listScreen,bundle)

        }
    }

    override fun getItemCount(): Int {
     return list.size
    }
}