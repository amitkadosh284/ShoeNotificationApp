package postpc.amitkadosh.shoenotifications

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.navigation.findNavController

class ConditionFragment : Fragment() {

    companion object {
        fun newInstance() = ConditionFragment()
    }

    private val sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }
    private val viewModel: ConditionViewModel by lazy {
        ViewModelProvider(requireActivity()).get(ConditionViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.condition_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //sets the progress bar in the main activity
        sharedViewModel.setProgress(viewModel.progress)
        //find views
        val agreeButton : RadioButton = view.findViewById(R.id.agreeButton)
        val continueButton : Button = view.findViewById(R.id.continueButton)

        //set views
        setViews(agreeButton, continueButton)

        agreeButton.setOnClickListener{
            viewModel.acceptTerms = agreeButton.isChecked
            setViews(agreeButton, continueButton)
        }

        continueButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_conditionFragment_to_mathFragment)
        }
    }

    /**
     * function sets all the views
     */
    private fun setViews(
        agreeButton: RadioButton,
        continueButton: Button
    ) {
        agreeButton.isChecked = viewModel.acceptTerms
        if (agreeButton.isChecked) {
            continueButton.isEnabled = true
            continueButton.visibility = View.VISIBLE
        } else {
            continueButton.isEnabled = false
            continueButton.visibility = View.GONE
        }
    }

}