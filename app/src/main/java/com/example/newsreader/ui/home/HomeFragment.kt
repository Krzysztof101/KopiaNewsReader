package com.example.newsreader.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsreader.R
import com.example.newsreader.Repository

class HomeFragment : Fragment() {
    val TAG = "HomeFragment"
    private lateinit var recyclerView: RecyclerView
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val view: View = inflater.inflate(R.layout.fragment_home,container,false)
        recyclerView = view.findViewById(R.id.mainArticlesList)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = MainArticlesAdapter(homeViewModel)
        val llm:RecyclerView.LayoutManager = LinearLayoutManager(view.context)
        //llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm
        Repository.getArticlesForHomeView()
        Repository.articlesArray.observe( viewLifecycleOwner,
            { Log.i(TAG,"Observer update")
                (recyclerView.adapter as MainArticlesAdapter).data  = Repository.getArticlesArray()})
//        val binding:HomeFragmentBinding = DataBindingUtil.setContentView(
//            requireActivity().parent,R.layout.fragment_home
//        )

        //val root = inflater.inflate(R.layout.fragment_home, container, false)
        //val adapter = MainArticlesAdapter()
        return view
    }
    fun onRecyclerItemClick(view: View){
        Toast.makeText(context,"$view clicked", Toast.LENGTH_SHORT).show()
    }
}