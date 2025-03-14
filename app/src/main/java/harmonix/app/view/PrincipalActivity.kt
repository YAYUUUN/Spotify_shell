package harmonix.app.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import harmonix.app.R

class PrincipalActivity : AppCompatActivity() {

    // Fragments
    private lateinit var homeFragment: HomeFragment
    private lateinit var libraryFragment: LibraryFragment
    private lateinit var searchFragment: SearchFragment
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal_activity)

        // Inicializar fragments
        homeFragment = HomeFragment()
        libraryFragment = LibraryFragment()
        searchFragment = SearchFragment()

        // Añadir todos los fragments al contenedor (inicialmente ocultos)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, homeFragment, "Home")
            .add(R.id.fragment_container, libraryFragment, "Library").hide(libraryFragment)
            .add(R.id.fragment_container, searchFragment, "Search").hide(searchFragment)
            .commit()

        currentFragment = homeFragment

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> switchFragment(homeFragment)
                R.id.navigation_library -> switchFragment(libraryFragment)
                R.id.navigation_search -> switchFragment(searchFragment)
                else -> return@setOnItemSelectedListener false
            }
            Toast.makeText(this, "${item.title} clicked", Toast.LENGTH_SHORT).show()
            true
        }

        // Seleccionar "Home" por defecto
        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.navigation_home
        }
    }

    private fun switchFragment(targetFragment: Fragment) {
        if (targetFragment == currentFragment) return

        supportFragmentManager.beginTransaction().apply {
            hide(currentFragment!!)
            if (targetFragment.isAdded) {
                show(targetFragment)
            } else {
                add(R.id.fragment_container, targetFragment, targetFragment.tag)
            }
            commit()
        }

        currentFragment = targetFragment
    }
}