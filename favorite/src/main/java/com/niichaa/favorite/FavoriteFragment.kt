package com.niichaa.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.niichaa.core.ui.CulinaryAdapter
import com.niichaa.culinaryapp.di.FavoriteDependencies
import com.niichaa.culinaryapp.ui.detail.DetailCulinaryActivity
import com.niichaa.favorite.databinding.FragmentFavoriteBinding
import com.niichaa.favorite.di.DaggerFavoriteComponent
import com.niichaa.favorite.viewmodel.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var culinaryAdapter: CulinaryAdapter

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onAttach(context: Context) {
        DaggerFavoriteComponent.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    FavoriteDependencies::class.java
                )
            ).build().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            culinaryAdapter = CulinaryAdapter()
            culinaryAdapter.onItemClick = {
                val intent = Intent(activity, DetailCulinaryActivity::class.java)
                intent.putExtra(DetailCulinaryActivity.EXTRA_DATA, it)
                startActivity(intent)
            }

            favoriteViewModel.favoriteCulinary.observe(viewLifecycleOwner){
                culinaryAdapter.setCulinary(it)
                binding.tvEmptyFavorite.visibility =
                    if (it.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvCulinary){
                layoutManager = GridLayoutManager(requireContext(), 2)
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