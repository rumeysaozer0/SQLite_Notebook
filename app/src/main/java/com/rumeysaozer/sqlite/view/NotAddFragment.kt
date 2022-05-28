package com.rumeysaozer.sqlite.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rumeysaozer.sqlite.databinding.FragmentNotAddBinding
import java.lang.Exception


class NotAddFragment : Fragment() {
    private var _binding: FragmentNotAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotAddBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageButton.setOnClickListener {
            val title = binding.baslik.text.toString()
            val text = binding.not.text.toString()
            try{
               val database = this.requireActivity().openOrCreateDatabase("Notes",Context.MODE_PRIVATE, null)
                database.execSQL("CREATE TABLE IF NOT EXISTS notes(id INTEGER PRIMARY KEY, title VARCHAR, text VARCHAR)")
                val statement = database.compileStatement("INSERT INTO notes(title, text) VALUES (?,?)")
                statement.bindString(1,title)
                statement.bindString(2,text)
                statement.execute()
                val action = NotAddFragmentDirections.actionNotAddFragmentToNotListFragment()
                findNavController().navigate(action)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }


}