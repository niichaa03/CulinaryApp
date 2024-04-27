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
import com.niichaa.core.domain.model.Culinary
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

        val layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvCulinary.layoutManager = layoutManager
        binding.rvCulinary.setHasFixedSize(true)

        favoriteViewModel.favoriteCulinary.observe(viewLifecycleOwner){
                setCulinary(it)
                binding.tvEmptyFavorite.visibility =
                    if (it.isNotEmpty()) View.GONE else View.VISIBLE
            }

    }

    private fun setCulinary(culinary: List<Culinary>) {
        val adapter = CulinaryAdapter(culinary)
        adapter.setOnItemClickCallback(object : CulinaryAdapter.OnItemClickCallback{
            override fun onItemClicked(culinary: Culinary) {
                val intent = Intent(activity, DetailCulinaryActivity::class.java)
                intent.putExtra(DetailCulinaryActivity.EXTRA_DATA, culinary)
                startActivity(intent)
            }
        })
        binding.rvCulinary.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}