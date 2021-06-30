package postpc.amitkadosh.shoenotifications

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class StartFragment : Fragment() {

    companion object {
        fun newInstance() = StartFragment()
    }

    private val shareViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    private val model: StartViewModel by lazy {
        ViewModelProvider(requireActivity()).get(StartViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.start_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shareViewModel.setProgress(model.progress)
        // find view
        val starButton: Button = view.findViewById(R.id.startButton)

        starButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_start_to_age)
        }
    }


}