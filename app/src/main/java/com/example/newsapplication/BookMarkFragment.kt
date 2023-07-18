package com.example.newsapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.newsapplication.adapters.BookMarkFragmentAdapter
import com.example.newsapplication.adapters.MainFragmentAdapter
import com.example.newsapplication.databinding.FragmentBookMarkBinding
import com.example.newsapplication.databinding.FragmentMainBinding
import com.example.newsapplication.models.Article
import com.example.newsapplication.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookMarkFragment : Fragment() {
    private var _binding: FragmentBookMarkBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAdapter: BookMarkFragmentAdapter
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mAdapter= BookMarkFragmentAdapter(this,::onBookMarkDelete)
        _binding= FragmentBookMarkBinding.inflate(inflater,container,false)
        mainViewModel.getBookMarks()
        //bindObservers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter= mAdapter
        binding.recyclerView.layoutManager= StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        bindObservers()
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }



    }
    private fun bindObservers() {
        mainViewModel.dbLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("pp",it.toString())
            mAdapter.submitList(it)
            if(mAdapter.itemCount==0){
                Toast.makeText(context,"Empty",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onBookMarkDelete(article: Article){
        mainViewModel.deleteBookMark(article)
    }
}