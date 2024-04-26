package com.niichaa.culinaryapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.niichaa.core.data.Resource
import com.niichaa.core.ui.CulinaryAdapter
import com.niichaa.culinaryapp.R
import com.niichaa.culinaryapp.databinding.FragmentHomeBinding
import com.niichaa.culinaryapp.ui.detail.DetailCulinaryActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val culinaryAdapter =  CulinaryAdapter()
            culinaryAdapter.onItemClick = {
                val intent = Intent(activity, DetailCulinaryActivity::class.java)
                intent.putExtra(DetailCulinaryActivity.EXTRA_DATA, it)
                startActivity(intent)
            }

            homeViewModel.culinary.observe(viewLifecycleOwner) {
                if (it != null) {
                    when(it) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            culinaryAdapter.setCulinary(it.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility= View.GONE
                            binding.viewError.root.visibility= View.VISIBLE
                            binding.viewError.tvErrorMessage.text = it.message ?: getString(R.string.errorMessage)
                        }
                    }
                }
            }

            with(binding.rvCulinary){
                layoutManager = GridLayoutManager(requireActivity(), 2)
                setHasFixedSize(true)
                adapter = culinaryAdapter
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}