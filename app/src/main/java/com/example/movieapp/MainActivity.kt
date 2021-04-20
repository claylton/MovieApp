package com.example.movieapp
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    private fun provideStartArgBundle() = Bundle()
    private fun setStartFragment(): Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val graph = getNavController().navInflater.inflate(R.navigation.navigation)
        if (setStartFragment() != 0) {
            graph.startDestination = setStartFragment()
        }

        getNavController().setGraph(graph, provideStartArgBundle())

    }

    fun FragmentActivity.getNavController(): NavController {
        return Navigation.findNavController(this, R.id.myNavHostFragment)
    }
}