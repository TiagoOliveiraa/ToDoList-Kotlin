package com.toliveira.todolist.fragments.update

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.toliveira.todolist.R
import com.toliveira.todolist.databinding.FragmentUpdateBinding
import com.toliveira.todolist.model.Task
import com.toliveira.todolist.viewmodel.TaskViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var mTaskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentUpdateBinding.inflate(layoutInflater, container, false)

        mTaskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)


        binding.updateTitle.editText?.setText(args.currentUser.title)
        binding.updateDescription.editText?.setText(args.currentUser.description)
        if (args.currentUser.resolved){
            binding.checkboxDone.isChecked = true
        }

        binding.updateBtn.setOnClickListener {
            updateTask()

        }

        //Add menu
        setHasOptionsMenu(true)

        return binding.root


    }

    private fun updateTask() {
        val title = binding.updateTitle.editText?.text.toString()
        val description = binding.updateDescription.editText?.text.toString()
        val resolved = binding.checkboxDone.isChecked

        if (inputCheck(title, description)) {
            val updatedTask = Task(args.currentUser.id, title, description, resolved)

            mTaskViewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(), "Task Updated Successfully", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Fields can't be empty", Toast.LENGTH_LONG).show()
        }
    }

    fun inputCheck(title: String, description:String) : Boolean{
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_,_,->
            mTaskViewModel.deleteTask(args.currentUser)
            Toast.makeText(requireContext(), "Successfully removed: ${args.currentUser.title}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNeutralButton("No"){_,_,->}
        builder.setTitle("Delete ${args.currentUser.title}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.title}?")
        builder.create().show()
    }

}