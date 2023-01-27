package mn.turbo.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mn.turbo.common.Resource
import mn.turbo.common.collectLatestLifecycleFlow
import mn.turbo.databinding.FragmentMainBinding
import mn.turbo.main.adapter.TodoAdapter
import mn.turbo.main.viewmodel.MainViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val mAdapter = TodoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mRecyclerView.adapter = mAdapter

        collectLatestLifecycleFlow(viewModel.todo) { resources ->
            when (resources) {
                is Resource.Success -> {
                    binding.mProgressBar.visibility = View.GONE
                    binding.mRecyclerView.visibility = View.VISIBLE
                    mAdapter.submitList(resources.data)
                }
                is Resource.Loading -> {
                    binding.mRecyclerView.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.mRecyclerView.visibility = View.GONE
                    binding.mProgressBar.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
