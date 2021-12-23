package com.example.coroutinestesting.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.coroutinestesting.R

class VerifyDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val args: VerifyDetailFragmentArgs by navArgs()

//        Toast.makeText(
//            context,
//            arguments?.getString("name") + "---------" + arguments?.getString("pass"),
//            Toast.LENGTH_LONG
//        ).show()

        Toast.makeText(context, args.name, Toast.LENGTH_LONG).show()
        return inflater.inflate(R.layout.fragment_verify_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}