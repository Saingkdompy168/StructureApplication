package com.example.structureapplication.model

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class TestingModel {


}

class Company {
    var name: String = "GeeksforGeeks"
    var founder: String = "Sandeep Jain"
    var objective: String = "A computer science portal for Geeks"
}

fun main() {
    val company = Company()
    val data = company.also {
        it.name = "darta"
    }
    print("Return result : ${data.name}")
}
