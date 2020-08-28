package com.cbitss.nziftaadmin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cbitss.nziftaadmin.R
import kotlinx.android.synthetic.main.fragment_typeof_user.*


class TypeofUserFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_typeof_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        show_appliesuser.setOnClickListener {
            findNavController().navigate(R.id.action_typeofUserFragment_to_appliesUser)
        }

        show_regitsteruser.setOnClickListener {
            findNavController().navigate(R.id.action_typeofUserFragment_to_registeredUser)
        }
    }

}