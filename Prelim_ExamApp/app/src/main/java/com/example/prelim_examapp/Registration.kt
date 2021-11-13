package com.example.prelim_examapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.prelim_examapp.RegisterViewModelFactory
import com.example.prelim_examapp.databinding.FragmentRegistrationBinding
import com.example.prelim_examapp.databse.LoginDatabase
import com.example.prelim_examapp.databse.UserRepository


class Registration : Fragment() {

    private lateinit var mViewModel:RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = DataBindingUtil.inflate<FragmentRegistrationBinding>(inflater,
            R.layout.fragment_registration,
            container,false
        )

        val application = requireNotNull(this.activity).application

        val dao = LoginDatabase.getDatabase(application).loginDao

        val repository = UserRepository(dao)

        val factory = RegisterViewModelFactory(repository, application)
        mViewModel = ViewModelProvider(this,factory).get(RegisterViewModel::class.java)

        binding.myViewModel =mViewModel
        binding.lifecycleOwner =this

        mViewModel.toastError.observe(viewLifecycleOwner, Observer{ hasError ->
            if(hasError==true){
                Toast.makeText(requireContext(),"Enter Username and Password",Toast.LENGTH_SHORT).show()
                mViewModel.done()
            }
        })
        mViewModel.toastCorrect.observe(viewLifecycleOwner, Observer {hasNoError ->
            if (hasNoError == true) {
                Toast.makeText(requireContext(), "Registration Successful", Toast.LENGTH_SHORT)
                    .show()
                mViewModel.sucess()
            }
        })
       return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Registration"
        //...
    }
}