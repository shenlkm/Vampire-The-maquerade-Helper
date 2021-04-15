package com.example.vampiremasterhelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.vampiremasterhelper.databinding.ActivityMainBinding
import com.example.vampiremasterhelper.model.PointGroupModel
import com.example.vampiremasterhelper.model.PointGroupSetModel
import com.example.vampiremasterhelper.model.PointItemModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fl_container, CharacterCreationFragment.newInstance())
            }
        }
    }
}