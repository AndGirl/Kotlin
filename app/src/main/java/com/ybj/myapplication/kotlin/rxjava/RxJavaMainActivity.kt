package com.ybj.myapplication.kotlin.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.rxjava.fragments.ElementaryFragment
import com.ybj.myapplication.kotlin.rxjava.fragments.MapFragment
import com.ybj.myapplication.kotlin.rxjava.fragments.TokenFragment
import com.ybj.myapplication.kotlin.rxjava.fragments.ZipFragment
import kotlinx.android.synthetic.main.activity_rx_java_main.*

class RxJavaMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java_main)

        setSupportActionBar(tool_bar)
        view_pager.adapter = object : FragmentPagerAdapter(
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
            override fun getItem(position: Int): Fragment  = when(position){
                0 -> ElementaryFragment()
                1 -> MapFragment()
                2 -> ZipFragment()
                3 -> TokenFragment()
//                4 -> TokenAdvancedFragment()
//                5 -> CacheFragment()
                else -> ElementaryFragment()
            }

            override fun getCount(): Int = 6

            override fun getPageTitle(position: Int): CharSequence = when(position){
                0 -> getString(R.string.title_elementary)
                1 -> getString(R.string.title_map)
                2 -> getString(R.string.title_zip)
                3 -> getString(R.string.title_token)
//                4 -> getString(R.string.title_token_advanced)
//                5 -> getString(R.string.title_cache)
                else -> getString(R.string.title_elementary)
            }
        }

        tabs.setupWithViewPager(view_pager)

    }
}
