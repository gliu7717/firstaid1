package com.derek.firstaidgame

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.derek.firstaidgame.databinding.ActivityMainBinding
import com.derek.firstaidgame.ui.learn.Dictionary
import java.io.IOException
import java.io.InputStream
import java.util.Scanner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        readCardTexts()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_flashcards, R.id.navigation_learn, R.id.navigation_test, R.id.navigation_game
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun readCardTexts(){
        // Declaring an input stream to read data
        val myInputStream: InputStream
        // Try to open the text file, reads
        // the data and stores it in the string
        try {
            myInputStream = assets.open("qa.txt")
            val scan = Scanner(myInputStream)
            while(scan.hasNextLine()){
                val question = scan.nextLine()
                val answer = scan.nextLine()
                Dictionary.dict.put(question, answer)
            }
        } catch (e: IOException) {
            // Exception
            e.printStackTrace()
        }
    }

}