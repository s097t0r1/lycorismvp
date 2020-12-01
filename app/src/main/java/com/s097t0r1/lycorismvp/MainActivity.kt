package com.s097t0r1.lycorismvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.s097t0r1.lycorismvp.ui.Screens
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(this, R.id.nav_host_fragment)

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigationBar()
    }

    private fun setupNavigationBar() {

        nav_view.selectedItemId = R.id.navigation_feed
        router.replaceScreen(Screens.Feed())

        nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_feed -> router.replaceScreen(Screens.Feed())
                R.id.navigation_favorites -> router.replaceScreen(Screens.Favorites())
                R.id.navigation_about -> router.replaceScreen(Screens.Favorites())
            }
            true
        }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }
}