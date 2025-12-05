package id.ac.pnm.anterinapp.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import id.ac.pnm.anterinapp.R

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val bottomAppBar = findViewById<BottomAppBar>(R.id.bottomAppBar)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val containerView = findViewById<View>(R.id.nav_host_fragment)
        val fabMulai = findViewById<CardView>(R.id.fabMulai)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.menu.getItem(2).isEnabled = false

        navController.addOnDestinationChangedListener { _, _, arguments ->
            val params = containerView.layoutParams as CoordinatorLayout.LayoutParams

            val showBottomNav = arguments?.getBoolean("showBottomNav", false) == true

            if (showBottomNav) {
                bottomAppBar.visibility = View.VISIBLE
                fabMulai.visibility = View.VISIBLE

                val density = resources.displayMetrics.density
                params.bottomMargin = (80 * density).toInt()
            } else {
                bottomAppBar.visibility = View.GONE
                fabMulai.visibility = View.GONE

                params.bottomMargin = 0
            }

            containerView.layoutParams = params
        }

        fabMulai.setOnClickListener {
            navController.navigate(R.id.destinationFragment)
        }
    }
}