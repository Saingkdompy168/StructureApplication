package com.example.structureapplication.model

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