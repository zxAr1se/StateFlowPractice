package com.example.stateflowpractice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stateflowpractice.ui.ViewPagerAdapter
import com.example.stateflowpractice.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initTabsAndAnim()
    }


    private fun initTabsAndAnim(){
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Login"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Signup"))
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val mAdapter = ViewPagerAdapter(
                supportFragmentManager,
                binding.tabLayout.tabCount
        )
        binding.viewPager.adapter = mAdapter

        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = binding.tabLayout.selectedTabPosition
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}