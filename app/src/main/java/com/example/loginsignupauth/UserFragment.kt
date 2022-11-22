package com.example.loginsignupauth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.loginsignupauth.databinding.FragmentUserBinding
import com.example.loginsignupauth.model.local.UserResponse
import com.example.loginsignupauth.utils.*
import com.example.loginsignupauth.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class UserFragment : Fragment(R.layout.fragment_user) {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UserViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserBinding.bind(view)

        viewModel.state.onEach {
            handleUserResponse(it.userRes)
        }.launchIn(viewLifecycleScope)
    }

    private fun handleUserResponse(res: Resource<UserResponse>) {
        when(res.status){
            Resource.Status.ERROR -> {
                val err = res.exception?.message
                hideAppLoader()
                showSnackBar(err.toString())
            }
            Resource.Status.LOADING -> {
                showAppLoader()
            }
            Resource.Status.SUCCESS ->{
                hideAppLoader()
            }
            else -> {}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}