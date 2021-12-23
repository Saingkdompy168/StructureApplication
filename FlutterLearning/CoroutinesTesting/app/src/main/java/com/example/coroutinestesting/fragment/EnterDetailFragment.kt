package com.example.coroutinestesting.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.coroutinestesting.R

class EnterDetailFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_enter_detail, container, false)
        val btnDetail = rootView.findViewById<Button>(R.id.submit)
        val name = rootView.findViewById<TextView>(R.id.name)
        val pass = rootView.findViewById<TextView>(R.id.pass)
        btnDetail.setOnClickListener {
            var name = name.text.toString()
            var pass = pass.text.toString()
//            var bundle = bundleOf("name" to name, "pass" to pass)
//            findNavController().navigate(
//                R.id.action_enterDetailFragment_to_verifyDetailFragment,
//                bundle
//            )

            findNavController().navigate(EnterDetailFragmentDirections.actionEnterDetailFragmentToVerifyDetailFragment(name),
                navOptions {
                    anim {

                    }
                })
        }
        return rootView
    }
}