package com.example.stateflowpractice.ui

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.stateflowpractice.databinding.LoginTabFragmentBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

@InternalCoroutinesApi
class LoginFragment : Fragment() {

    private var _binding: LoginTabFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
            : View {

        _binding = LoginTabFragmentBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener {
            viewModel.login(
                    binding.email.text.toString(),
                    binding.psw.text.toString()
                    )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.loginUiState.collect {
                when(it){
                    is LoginViewModel.LoginUiState.Success -> {
                        Snackbar.make(
                                binding.root,
                                "Successfully logged in",
                                Snackbar.LENGTH_LONG
                        ).show()
                        binding.progressBar.isVisible = false
                    }
                    is LoginViewModel.LoginUiState.Error -> {
                        Snackbar.make(
                                binding.root,
                                it.message,
                                Snackbar.LENGTH_LONG
                        ).show()
                        binding.progressBar.isVisible = false
                    }
                    is LoginViewModel.LoginUiState.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}