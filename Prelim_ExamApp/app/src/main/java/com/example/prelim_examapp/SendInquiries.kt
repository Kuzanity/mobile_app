package com.example.prelim_examapp

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.prelim_examapp.Inquires.Inquiries
import com.example.prelim_examapp.Inquires.InquiryViewModel
import com.example.prelim_examapp.databinding.FragmentSendInquiriesBinding


class SendInquiries : Fragment() {

    private lateinit var mInquiryViewModel: InquiryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding= DataBindingUtil.inflate<FragmentSendInquiriesBinding>(inflater,
        R.layout.fragment_send_inquiries,container,false)

        mInquiryViewModel= ViewModelProvider(this).get(InquiryViewModel::class.java)

        binding.button.setOnClickListener{
            val inquiry = binding.inqText.text.toString()
            if (checker(inquiry)){
                val insert =Inquiries(0,inquiry)
                mInquiryViewModel.addInq(insert)
                Toast.makeText(requireContext(),"Inquiry Accepted",Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
    }
    private fun checker(inquiry:String):Boolean{
        return  !(TextUtils.isEmpty(inquiry))
    }
