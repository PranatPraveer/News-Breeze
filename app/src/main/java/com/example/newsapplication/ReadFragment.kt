package com.example.newsapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.newsapplication.adapters.MainFragmentAdapter
import com.example.newsapplication.databinding.FragmentMainBinding
import com.example.newsapplication.databinding.FragmentReadBinding
import com.example.newsapplication.models.Article
import com.example.newsapplication.models.Source
import com.example.newsapplication.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReadFragment : Fragment() {
    private var _binding: FragmentReadBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentReadBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image= arguments?.getString("Image")
        val title= arguments?.getString("Title")
        val sourceName= arguments?.getString("SourceName")
        val sourceId= arguments?.getString("SourceId")
        val ArticleId= arguments?.getInt("Id")
        val Author= arguments?.getString("Author")
        val Description= arguments?.getString("Description")
        val PublishedAt= arguments?.getString("PublishedAt")
        val Content= arguments?.getString("Content")
        val news= arguments?.getString("News")
        Glide.with(binding.imageView).load(image).into(binding.imageView)
        if (news != null) {
            binding.webView.loadUrl(news)
        }
        binding.BtnSave.setOnClickListener {
            mainViewModel.addBookMark(Article(ArticleId!!,Author!!,Content!!, Description!!,PublishedAt!!.toString(),
                Source(sourceId!!,sourceName!!),title!!,news!!,image!!))
        }
        binding.imageView2.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}