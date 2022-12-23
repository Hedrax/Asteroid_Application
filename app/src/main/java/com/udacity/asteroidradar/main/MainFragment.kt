package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setHasOptionsMenu(true)
        viewModel.image.observe(viewLifecycleOwner, Observer {
            Glide.with(this).load(it.url)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_connection_error)
            .into(binding.activityMainImageOfTheDay)
            binding.activityMainImageOfTheDay.contentDescription =
                it.description
        })
        val adapter = AsteroidAdabter(ClickListener{viewModel.navigateProp(it)})
        binding.asteroidRecycler.adapter = adapter
        viewModel.asteroidList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        viewModel.navigate.observe(viewLifecycleOwner, Observer {
            this.findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
        })
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.show_today_menu-> viewModel.getDay()
            R.id.show_week_menu -> viewModel.getWeek()
            else -> viewModel.getSaved()
        }
        return super.onOptionsItemSelected(item)
    }
}
