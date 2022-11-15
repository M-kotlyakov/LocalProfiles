package com.example.localprofiles.presentation.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.localprofiles.R
import com.example.localprofiles.data.ProfileListMapper
import com.example.localprofiles.databinding.FragmentHomeBinding
import com.example.localprofiles.presentation.activities.HomeAdapter
import com.example.localprofiles.presentation.factory.ViewModelFactory
import com.example.localprofiles.presentation.viewModels.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var homeAdapter: HomeAdapter

    private val mapper = ProfileListMapper()

    private val viewModelFactory by lazy {
        ViewModelFactory(requireActivity().application)
    }

    private val vm by lazy {
        ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        setupRecyclerView()
        observeLiveData()
        setupSwipeListener(recyclerView)
        itemClick()
        /*binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addProfileFragment)
        }*/
    }

    private fun setupSwipeListener(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = homeAdapter.getCurrentItem(position)
                homeAdapter.notifyItemRemoved(position)
                vm.deleteProfile(mapper.mapDbModelToEntity(item))
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun observeLiveData() {
        vm.getList.observe(viewLifecycleOwner) {
            homeAdapter.setData(mapper.mapListEntityToListDbModel(it))
        }
    }

    private fun itemClick() {
        homeAdapter.onProfileItemClickListener = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToPersonalAccountFragment(it)
            )
        }
    }

    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter()
        with(recyclerView) {
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
            adapter = homeAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}