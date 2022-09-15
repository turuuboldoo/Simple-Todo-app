package mn.turbo.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import mn.turbo.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val stringFromMainFragment = arguments?.getString("customString") ?: "empty argument"
        println("${this.javaClass.name} - $stringFromMainFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return FragmentSecondBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSecondBinding.bind(view)
        binding.mButton.setOnClickListener {
            findNavController().navigate(
                SecondFragmentDirections.actionSecondFragmentToThirdFragment()
            )
        }
    }
}