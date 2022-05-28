package com.rumeysaozer.sqlite.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rumeysaozer.sqlite.R
import com.rumeysaozer.sqlite.databinding.FragmentNotUpdateBinding
import java.lang.Exception

class NotUpdateFragment : Fragment() {
    private var _binding: FragmentNotUpdateBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<NotUpdateFragmentArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.baslik.setText(args.not.baslik)
        binding.not.setText(args.not.not)
        binding.imageButton.setOnClickListener {
            try{
                val title = binding.baslik.text.toString()
                val text = binding.not.text.toString()
                val id = args.not.id
                val database = this.requireActivity().openOrCreateDatabase("Notes",
                    Context.MODE_PRIVATE, null)
                database.execSQL("UPDATE notes SET title = '${title}' WHERE id = ${id} ")
                database.execSQL("UPDATE notes SET text = '${text}' WHERE id = ${id} ")
            }catch (e:Exception){
                e.printStackTrace()
            }
            val action = NotUpdateFragmentDirections.actionNotUpdateFragmentToNotListFragment()
            findNavController().navigate(action)
        }
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_item,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId== R.id.delete){
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("${args.not.baslik} silinecek ")
            builder.setMessage("Silmek istediğinize emin misiniz?")
            builder.setPositiveButton("yes"){_, _->

                try {
                    val id = args.not.id
                    val database = this.requireActivity().openOrCreateDatabase("Notes",
                        Context.MODE_PRIVATE, null)
                    database.execSQL("DELETE FROM notes WHERE id =  ${id}")
                    val action = NotUpdateFragmentDirections.actionNotUpdateFragmentToNotListFragment()
                    findNavController().navigate(action)
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
            builder.setNegativeButton("no"){_,_->}
            builder.create().show()
        }
        return super.onOptionsItemSelected(item)
    }


}