package id.ac.pnm.anterinapp.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.ac.pnm.anterinapp.R

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val bottomAppBar = findViewById<BottomAppBar>(R.id.bottomAppBar)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val containerView = findViewById<View>(R.id.nav_host_fragment)

        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.menu.getItem(2).isEnabled = false

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val params = containerView.layoutParams as CoordinatorLayout.LayoutParams

            when (destination.id) {
                R.id.combinationFragment -> {
                    bottomAppBar.visibility = View.GONE
                    params.bottomMargin = 0
                }
                R.id.scheduleFragment -> {
                    bottomAppBar.visibility = View.GONE
                    params.bottomMargin = 0
                }
                R.id.daftarPesananFragment -> {
                    bottomAppBar.visibility = View.GONE
                    params.bottomMargin = 0
                }
                R.id.addTripFragment -> {
                    bottomAppBar.visibility = View.GONE
                    params.bottomMargin = 0
                }
                R.id.destinationFragment -> {
                    bottomAppBar.visibility = View.GONE
                    params.bottomMargin = 0
                }
                R.id.paymentFragment -> {
                    bottomAppBar.visibility = View.GONE
                    params.bottomMargin = 0
                }
                R.id.successFragment -> {
                    bottomAppBar.visibility = View.GONE
                    params.bottomMargin = 0
                }

                R.id.homeFragment,
                R.id.voucherFragment,
                R.id.historyFragment,
                R.id.profileFragment -> {
                    bottomAppBar.visibility = View.VISIBLE
                }

                else -> {
                    bottomAppBar.visibility = View.VISIBLE
                    val density = resources.displayMetrics.density
                    params.bottomMargin = (80 * density).toInt()
                }
            }
        }
    }
}