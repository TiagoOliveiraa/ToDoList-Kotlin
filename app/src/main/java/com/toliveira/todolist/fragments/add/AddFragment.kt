package com.toliveira.todolist.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.toliveira.todolist.R
import com.toliveira.todolist.model.Task
import com.toliveira.todolist.viewmodel.TaskViewModel
import com.toliveira.todolist.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        binding.addBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return binding.root
    }

    private fun insertDataToDatabase() {

        val title = binding.editTextTitle.text.toString()
        val description = binding.editTextDescription.text.toString()
        if(inputCheck(title,description)){
            // Create a task object
            val task = Task(0,title,description,false)
            // Add Data to database
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(title:String, description:String) : Boolean{

        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }

}