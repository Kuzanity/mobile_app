package com.example.prelim_examapp


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.prelim_examapp.databinding.FragmentLoginBinding
import com.example.prelim_examapp.databse.LoginDatabase
import com.example.prelim_examapp.databse.UserRepository



class Login : Fragment() {
    private lateinit var viewModel: LoginFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater, R.layout.fragment_login,
            container, false
        )
        val application = requireNotNull(this.activity).application

        val dao = LoginDatabase.getDatabase(application).loginDao

        val repository = UserRepository(dao)

        val factory = LoginFragmentFactory(repository, application)

        viewModel = ViewModelProvider(this, factory).get(LoginFragmentViewModel::class.java)

        binding.myLogViewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.toastError.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError == true){
                Toast.makeText(requireContext(),"Please insert Login Credentials",Toast.LENGTH_SHORT).show()
                viewModel.error()
            }
        })
        viewModel.toastCorrect.observe(viewLifecycleOwner, Observer { hasNoError->
            if(hasNoError == true){
                Toast.makeText(requireContext(),"Welcome Master",Toast.LENGTH_SHORT).show()
                moveToHome()
                viewModel.done()
            }
        })
        binding.Register.setOnClickListener{
            requireView().findNavController().navigate(R.id.action_login2_to_registration)
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Login"
//...
    }
    private fun moveToHome() {

        view?.findNavController()?.navigate(R.id.action_login2_to_home2)

    }
    }








