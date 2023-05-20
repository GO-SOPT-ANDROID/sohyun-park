package org.android.go.sopt.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.presentation.main.gallery.GalleryFragment
import org.android.go.sopt.presentation.main.home.HomeFragment
import org.android.go.sopt.presentation.auth.MyPageFragment
import org.android.go.sopt.presentation.main.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startFragment(HomeFragment())
        clickNavigationItem()
        scrollToTop()
    }


    private fun clickNavigationItem() {
        binding.bnvMain.setOnItemSelectedListener { item ->
            changeFragment(
                when (item.itemId) {
                    R.id.menu_home -> {
                        HomeFragment()
                    }
                    R.id.menu_gallery -> {
                        GalleryFragment()
                    }
                    R.id.menu_search -> {
                        SearchFragment()
                    }
                    else -> {
                        MyPageFragment()
                    }
                }
            )
            true
        }
    }


    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }

    private fun scrollToTop() {
        binding.bnvMain.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    (supportFragmentManager.findFragmentById(R.id.fcv_main) as? HomeFragment)?.scrollToTop()
                }
                else -> {
                    return@setOnItemReselectedListener
                }
            }
        }
    }


}