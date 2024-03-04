package com.carrot.presentation.view.main.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfileAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    var fragments: ArrayList<Fragment> = arrayListOf<Fragment>(
        ProfileGridFragment(),
        ProfileGridFragment()
    )

    override fun getItemCount(): Int = 2


    override fun createFragment(position: Int): Fragment {
//        val fragment = ProfileGridFragment()
        return fragments[position]
    }
}