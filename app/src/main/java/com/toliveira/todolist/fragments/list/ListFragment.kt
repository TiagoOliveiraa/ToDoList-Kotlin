package com.toliveira.todolist.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.toliveira.todolist.R
import com.toliveira.todolist.viewmodel.TaskViewModel
import com.toliveira.todolist.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(inflater, container, false)

        val adapter = ListAdapter()
        val recyclerView : RecyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())



        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        mTaskViewModel.readAllData.observe(viewLifecycleOwner, Observer{task ->
            adapter.setData(task)
            if(task.isEmpty()){
                binding.floatingDeleteButton.visibility = View.GONE
            }else{
                binding.floatingDeleteButton.visibility = View.VISIBLE
            }
        })


        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        binding.floatingDeleteButton.setOnClickListener {
            deleteAllTasks()
        }



        return binding.root
    }

    private fun deleteAllTasks() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mTaskViewModel.deleteAll()
            Toast.makeText(
                requireContext(),
                "Successfully removed every task!",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNeutralButton("No") { _, _ -> }
        builder.setTitle("Delete all tasks?")
        builder.setMessage("Are you sure you want to delete all your tasks?")
        builder.create().show()
    }

}