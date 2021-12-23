package com.example.coroutinestesting.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutinestesting.adapter.Adapter
import com.example.coroutinestesting.databinding.ActivityGroupDataBinding
import com.example.coroutinestesting.model.DateItem
import com.example.coroutinestesting.model.GeneralItem
import com.example.coroutinestesting.model.ListItem
import com.example.coroutinestesting.model.PojoOfJsonArray

class GroupDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupDataBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroupDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.list.setHasFixedSize(true)

        val myOptions = listOf(
            PojoOfJsonArray("name 1", "Order"),
            PojoOfJsonArray("name 2", "2016-06-05"),
            PojoOfJsonArray("name 2", "Order"),
            PojoOfJsonArray("name 3", "2016-05-17"),
            PojoOfJsonArray("name 3", "Order"),
            PojoOfJsonArray("name 3", "2016-05-17"),
            PojoOfJsonArray("name 3", "2016-05-17"),
            PojoOfJsonArray("name 2", "POSM"),
            PojoOfJsonArray("name 3", "posm")
        )

        val groupedMapMap: Map<String, List<PojoOfJsonArray>> = myOptions.groupBy {
            it.date
        }

        val consolidatedList = mutableListOf<ListItem>()
        for (date: String in groupedMapMap.keys) {
            consolidatedList.add(DateItem(date))
            val groupItems: List<PojoOfJsonArray>? = groupedMapMap[date]
            groupItems?.forEach {
                consolidatedList.add(GeneralItem(it.name))
            }
        }

        val adapter = Adapter(consolidatedList)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.list.layoutManager = layoutManager
        binding.list.adapter = adapter

    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(Intent(context, GroupDataActivity::class.java))
        }
    }
}