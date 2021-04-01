package com.example.vampiremasterhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vampiremasterhelper.databinding.ActivityMainBinding
import com.example.vampiremasterhelper.views.PointItemModel
import com.example.vampiremasterhelper.views.PointSetAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataSet().let {
            binding.rvFisicalAttributes.layoutManager  = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.rvFisicalAttributes.adapter = PointSetAdapter(it)
        }
    }

    private fun getDataSet() : Array<PointItemModel> {
        return arrayOf(PointItemModel("Fuerza"), PointItemModel("Destreza"), PointItemModel("Resistencia"))
    }
}