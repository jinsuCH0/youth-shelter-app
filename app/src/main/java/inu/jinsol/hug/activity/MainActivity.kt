package inu.jinsol.hug.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import inu.jinsol.hug.R
import inu.jinsol.hug.databinding.ActivityMainBinding
import inu.jinsol.hug.ui.home.HomeFragment
import inu.jinsol.hug.ui.shelterinfo.ShelterInfoFragment
import inu.jinsol.hug.ui.shelterlist.ShelterListFragment
import inu.jinsol.hug.ui.sheltersearch.ShelterSearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "MainActivity - onCreate() called")

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_shelter_info,
                R.id.navigation_shelter_list, R.id.navigation_shelter_search
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener (onBottomNavItemSelectedListener)
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//
//    }   // 뒤로가기 눌렀을 때 이전 페이지 나타나도록 구현 필요

    // 하단 메뉴 클릭 리스너
   private val onBottomNavItemSelectedListener = NavigationBarView.OnItemSelectedListener {
        val current: Fragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)

        when(it.itemId){
            R.id.navigation_home -> {
                Log.d(TAG, "MainActivity - 홈버튼 클릭!")


                supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
                Log.d(TAG, "current fragment - ${current.toString()}")
            }
            R.id.navigation_shelter_list -> {
                Log.d(TAG, "MainActivity - 청소년 쉼터 리스트 클릭")
                supportFragmentManager.beginTransaction().replace(R.id.container, ShelterListFragment()).commit()
                Log.d(TAG, "current fragment - ${current.toString()}")
            }
            R.id.navigation_shelter_search -> {
                Log.d(TAG, "MainActivity - 가까운 쉼터 찾기 클릭")
                supportFragmentManager.beginTransaction().replace(R.id.container, ShelterSearchFragment()).commit()
                Log.d(TAG, "current fragment - ${current.toString()}")
            }
            R.id.navigation_shelter_info -> {
                Log.d(TAG, "MainActivity - 청소년 쉼터란? 클릭")
                supportFragmentManager.beginTransaction().replace(R.id.container, ShelterInfoFragment()).commit()
                Log.d(TAG, "current fragment - ${current.toString()}")
            }
            R.id.navigation_chat -> {
                Log.d(TAG, "MainActivity - 1대1 채팅 클릭")
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cyber1388.kr:447/"))
                startActivity(intent)
            }
        }
        true
    }

}