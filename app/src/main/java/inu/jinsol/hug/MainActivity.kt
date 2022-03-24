package inu.jinsol.hug

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
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

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_shelter_info,
                R.id.navigation_shelter_list, R.id.navigation_shelter_search)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener (onBottomNavItemSelectedListener)
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }   // 뒤로가기 눌렀을 때 이전 페이지 나타나도록 구현 필요

    // 하단 메뉴 클릭 리스너
   private val onBottomNavItemSelectedListener = NavigationBarView.OnItemSelectedListener {
        when(it.itemId){
            R.id.navigation_home ->supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()
            R.id.navigation_shelter_list ->supportFragmentManager.beginTransaction().replace(R.id.container, ShelterListFragment()).commit()
            R.id.navigation_shelter_search ->supportFragmentManager.beginTransaction().replace(R.id.container, ShelterSearchFragment()).commit()
            R.id.navigation_shelter_list ->supportFragmentManager.beginTransaction().replace(R.id.container, ShelterListFragment()).commit()
            R.id.navigation_shelter_info ->supportFragmentManager.beginTransaction().replace(R.id.container, ShelterInfoFragment()).commit()
            R.id.navigation_chat-> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cyber1388.kr:447/"))
                startActivity(intent)
            }
        }
        true
    }

}