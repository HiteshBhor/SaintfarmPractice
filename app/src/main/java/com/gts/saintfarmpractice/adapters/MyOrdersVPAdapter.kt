package com.gts.saintfarmpractice.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gts.saintfarmpractice.ui.fragments.OngoingOrdersFragment
import com.gts.saintfarmpractice.ui.fragments.OrderHistoryFragment

class MyOrdersVPAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when(position){

            0->{
                OngoingOrdersFragment()
            }
            1->{
                OrderHistoryFragment()
            }
            else->{
                Fragment()
            }
        }
    }
}