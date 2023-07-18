package com.example.newsapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.newsapplication.databinding.FragmentMainBinding
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asFlow
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.newsapplication.adapters.MainFragmentAdapter
import com.example.newsapplication.models.Article
import com.example.newsapplication.utlis.NetworkResult
import com.example.newsapplication.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import kotlin.concurrent.thread

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding:FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var currlst :MutableList<Article>
    var list:MutableList<Article> = mutableListOf()
    private lateinit var mAdapter: MainFragmentAdapter
    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mAdapter= MainFragmentAdapter(this,::onBookMarkClicked,::onBookMarkDelete)
        _binding=FragmentMainBinding.inflate(inflater,container,false)
        bindObservers()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getNews()
        binding.recyclerView.adapter= mAdapter
        binding.recyclerView.layoutManager= StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding.BtnFavourite.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_bookMarkFragment)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                mAdapter.submitList(currlst)
                list= mutableListOf()
                for(i in mAdapter.currentList){
                    if (query != null) {
                        if(i.title.contains(query)){
                            list.add(i)
                            mAdapter.submitList(list)
                            Log.d("zz", currlst.toString())

                        }

                    }
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

    }




    private fun bindObservers() {
        mainViewModel.dbLiveData.observe(viewLifecycleOwner, Observer {
            Log.d("pp",it.toString())
            mAdapter.submitList(it)
        })
        mainViewModel.NewsLiveData.observe(viewLifecycleOwner, Observer {
            when (it){
                is NetworkResult.Success ->{
                    mAdapter.submitList(it.data?.articles)
                    currlst = it.data?.articles as MutableList<Article>
                    Log.d("pp",it.data.toString())
                }
                is NetworkResult.Error -> {
                    Log.d("pp",it.data.toString())
                }
                is NetworkResult.Loading ->{
                    Log.d("pp",it.data.toString())
                }
            }
        })

    }
    private fun onBookMarkClicked(article: Article){
        mainViewModel.addBookMark(article)
    }
    private fun onBookMarkDelete(article: Article){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
        context?.startActivity(intent)
    }
}