package com.example.stateflowpractice.ui


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.coroutines.InternalCoroutinesApi


class ViewPagerAdapter(
    fm: FragmentManager,
    totalTabs: Int
) : FragmentPagerAdapter(fm,totalTabs) {

    private val total = totalTabs

    override fun getCount(): Int = total

    @InternalCoroutinesApi
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> LoginFragment()
            1 -> SignupFragment()
            else -> LoginFragment()
        }
    }

}