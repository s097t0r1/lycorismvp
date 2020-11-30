package com.s097t0r1.lycorismvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.s097t0r1.lycorismvp.ui.Screens
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(this, R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//
//        val navController = findNavController(R.id.nav_host_fragment)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(setOf(
//                R.id.navigation_feed, R.id.navigation_about, R.id.navigation_favorites))
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        setupNavigationBar()
    }

    private fun setupNavigationBar() {

        nav_view.selectedItemId = R.id.navigation_feed
        App.INSTANCE.router.replaceScreen(Screens.Feed())

        nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_feed -> App.INSTANCE.router.replaceScreen(Screens.Feed()) //envia comandos para o navigator
                R.id.navigation_favorites -> App.INSTANCE.router.replaceScreen(Screens.Favorites())
                R.id.navigation_about -> App.INSTANCE.router.replaceScreen(Screens.Favorites())
            }
            true
        }
    }

    override fun onPause() {
        App.INSTANCE.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.INSTANCE.navigatorHolder.setNavigator(navigator)
    }
}