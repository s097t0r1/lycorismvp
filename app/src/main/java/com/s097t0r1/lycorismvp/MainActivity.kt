package com.s097t0r1.lycorismvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.s097t0r1.lycorismvp.databinding.ActivityMainBinding
import com.s097t0r1.lycorismvp.ui.BackButtonListener
import com.s097t0r1.lycorismvp.ui.Screens
import toothpick.ktp.KTP
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(this, R.id.nav_host_fragment)

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        KTP.openScope(App::class).inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigationBar()
    }

    private fun setupNavigationBar() {

        binding.navView.selectedItemId = R.id.navigation_feed
        router.replaceScreen(Screens.Feed())

        binding.navView.setOnNavigationItemSelectedListener { item ->
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

    override fun onBackPressed() {
        super.onBackPressed()
        val fm = supportFragmentManager
        var fragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                fragment = f
                break
            }
        }
        if (fragment != null && fragment is BackButtonListener) {
            (fragment as BackButtonListener).onBackPressed()

    }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }
}