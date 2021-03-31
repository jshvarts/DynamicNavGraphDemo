package io.valueof.dynamicnavgraph

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.valueof.dynamicnavgraph.databinding.FragmentMainBinding
import kotlin.random.Random

class MainFragment : Fragment(R.layout.fragment_main) {

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val binding = FragmentMainBinding.bind(view)

    binding.profileButton.setOnClickListener {
      val navController = findNavController()
      val navGraph = navController.navInflater.inflate(R.navigation.navigation_profile)
      navController.graph = navGraph
    }

    binding.forgotPasswordButton.setOnClickListener {
      val navController = findNavController()
      val navGraph = navController.navInflater.inflate(R.navigation.navigation_login).apply {
        startDestination = R.id.forgotPassword
      }
      navController.graph = navGraph
    }

    binding.loginButton.setOnClickListener {
      val didForgetPassword = Random.nextBoolean()

      val (startDestinationId, navGraphId) = if (didForgetPassword) {
        Pair(R.id.forgotPassword, R.navigation.navigation_login)
      } else {
        Pair(R.id.login, R.navigation.navigation_login)
      }

      val navController = findNavController()
      val navGraph = navController.navInflater.inflate(navGraphId).apply {
        startDestination = startDestinationId
      }
      navController.graph = navGraph
    }
  }
}