package mn.turbo.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import mn.turbo.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return FragmentThirdBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentThirdBinding.bind(view)

        binding.mButtonToMain.setOnClickListener {
            findNavController().navigate(
                ThirdFragmentDirections.actionThirdFragmentToMainFragment()
            )
        }

        binding.mButtonToSecond.setOnClickListener {
            findNavController().navigate(
                ThirdFragmentDirections.actionThirdFragmentToSecondFragment()
            )
        }
    }
}