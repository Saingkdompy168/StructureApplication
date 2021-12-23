package com.example.structureapplication.util

open class ApiDataModel<T>(var data: T? = null) {
    var metadata: MetaData? = null

    class MetaData() {
        var total: Int = 0
        var limit: Int = 0
        var offset: Int = 0
    }
}