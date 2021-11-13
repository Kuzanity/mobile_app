package com.example.prelim_examapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.prelim_examapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main)

        drawerLayout = binding.drawerLayout
        val navController1 =this.findNavController(R.id.myNavHostFragment)
        navController1.navigate(R.id.login2)
        NavigationUI.setupActionBarWithNavController(this,navController1,drawerLayout)


        NavigationUI.setupWithNavController(binding.navView,navController1)
        navController1.addOnDestinationChangedListener{ _,destination,_ ->
            if (destination.id== R.id.login2){supportActionBar?.setDisplayHomeAsUpEnabled(false)}
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController,drawerLayout)
    }
}

