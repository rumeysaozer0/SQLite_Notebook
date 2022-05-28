package com.rumeysaozer.sqlite.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rumeysaozer.sqlite.adapter.NotAdapter
import com.rumeysaozer.sqlite.databinding.FragmentNotListBinding
import com.rumeysaozer.sqlite.model.Not


class NotListFragment : Fragment() {
    private var _binding: FragmentNotListBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteList : ArrayList<Not>
    private lateinit var notAdapter: NotAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteList = ArrayList<Not>()
        notAdapter = NotAdapter(noteList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = notAdapter
        try{
            val database = this.requireActivity().openOrCreateDatabase("Notes",Context.MODE_PRIVATE,null)
            val cursor = database.rawQuery("SELECT * FROM notes", null)
            val idIx = cursor.getColumnIndex("id")
            val titleIx = cursor.getColumnIndex("title")
            val textIx = cursor.getColumnIndex("text")
            while(cursor.moveToNext()){
                val id = cursor.getInt(idIx)
                val title = cursor.getString(titleIx)
                val text = cursor.getString(textIx)
                val not = Not(id,title,text)
                noteList.add(not)
            }
            notAdapter.notifyDataSetChanged()
            cursor.close()


        }catch(e: Exception){
            e.printStackTrace()
        }
        binding.floatingActionButton.setOnClickListener {
            val action = NotListFragmentDirections.actionNotListFragmentToNotAddFragment()
            findNavController().navigate(action)
        }
    }


}