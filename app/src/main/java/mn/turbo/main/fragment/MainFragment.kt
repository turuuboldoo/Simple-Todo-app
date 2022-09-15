package mn.turbo.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import mn.turbo.common.Resource
import mn.turbo.databinding.FragmentMainBinding
import mn.turbo.main.adapter.TodoAdapter
import mn.turbo.main.viewmodel.MainViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val mAdapter = TodoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mRecyclerView.adapter = mAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.todo.collectLatest { resources ->
                    when (resources) {
                        is Resource.Success -> {
                            Log.w("123123", "Resource.Success")
                            binding.mProgressBar.visibility = View.GONE
                            binding.mRecyclerView.visibility = View.VISIBLE
                            mAdapter.submitList(resources.data)
                        }
                        is Resource.Loading -> {
                            Log.w("123123", "Resource.Loading")
                            binding.mRecyclerView.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            Log.w("123123", "Resource.Error ${resources.message}")
                            binding.mRecyclerView.visibility = View.GONE
                            binding.mProgressBar.visibility = View.GONE
                        }
                        null -> {
                            Log.w("123123", "null")
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
