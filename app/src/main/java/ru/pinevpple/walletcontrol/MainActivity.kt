package ru.pinevpple.walletcontrol

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    private lateinit var mainFragment: MainFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addingMainFragment()
    }

    private fun addingMainFragment() {
        Log.d("M_fragments", "added first fragment")
        mainFragment = MainFragment()
        val t: FragmentTransaction = supportFragmentManager.beginTransaction()
        t.add(R.id.root_container, mainFragment, "fragment_main")
        t.commit()
//        startingMainFragment()
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
            Log.d("M_Fragments", "In backStack now ${supportFragmentManager.backStackEntryCount}")
        }
    }
}