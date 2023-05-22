package com.example.fundamentalexercise2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var fragmentContainer: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.fragmentContainer)

        val fragment = NewsListFragment()
        supportFragmentManager.beginTransaction()
            .replace(fragmentContainer!!.id, fragment)
            .addToBackStack("null")
            .commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val fragment = AboutMeFragment()
        when (item.itemId) {
            R.id.aboutAuthorMenu -> supportFragmentManager.beginTransaction()
                .replace(fragmentContainer!!.id, fragment)
                .addToBackStack("null")
                .commit()
        }
        return super.onOptionsItemSelected(item)
    }
}
