package com.carrot.carrotdiary.view.main.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfileAdapter(fragment: Fragment, private val viewModel: ProfileViewModel) :
    FragmentStateAdapter(fragment) {

    var fragments: ArrayList<Fragment> = arrayListOf<Fragment>(
        ProfileGridFragment(viewModel),
        ProfileGridFragment(viewModel)
    )

    override fun getItemCount(): Int = 2


    override fun createFragment(position: Int): Fragment {
        val fragment = ProfileGridFragment(viewModel)
        return fragments[position]
    }
}