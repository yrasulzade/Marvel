package com.example.details

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.children
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.core.base.BaseFragment
import com.example.details.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding, DetailsViewModel>() {
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val safeArgs: DetailsFragmentArgs by navArgs()
        val id = safeArgs.id
        val marvelType = safeArgs.type
        viewModel.fetchCharacterDetails(id, marvelType)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clearMenu()

        viewModel.details.observe(viewLifecycleOwner) {
            binding.name.text = it.title
            binding.image.load(it.thumbnail)
        }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    override fun getLayoutId(): Int = R.layout.fragment_details

    override fun getViewModel(): DetailsViewModel {
        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        return viewModel
    }

    private fun clearMenu() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.children.forEach {
                    it.isVisible = false
                }
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}
