package postpc.amitkadosh.shoenotifications

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.navigation.findNavController

class AgeFragment : Fragment() {

    companion object {
        fun newInstance() = AgeFragment()
    }
    private val sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }
    private val viewModel: AgeFragmentViewModel by lazy {
        ViewModelProvider(requireActivity()).get(AgeFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.age_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set the progress bar in the main activity
        sharedViewModel.setProgress(viewModel.progress)

        //finds view
        val date : DatePicker = view.findViewById(R.id.datePicker)
        val invalidAge : TextView = view.findViewById(R.id.invalidAge)
        val continueButton : Button = view.findViewById(R.id.continueButton)

        //sets view
        setViews(invalidAge, continueButton)

        //set the date picker
        date.init(viewModel.bYear,viewModel.bMonth, viewModel.bDay, DatePicker.OnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            viewModel.setDate(year, monthOfYear, dayOfMonth)
            setViews(invalidAge, continueButton)
        })

        continueButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_age_fragment_to_conditionFragment)
        }

    }


    /**
     * function that sets all the views of the fragment
     */
    private fun setViews(invalidAge: TextView, continueButton: Button) {
        if (viewModel.age == null) { // if it the first time in this fragment
            invalidAge.visibility = View.GONE
            continueButton.isEnabled = false
            continueButton.visibility = View.GONE
        } else if (viewModel.age!! >= 18) { // if the age is valid
            invalidAge.visibility = View.GONE
            continueButton.isEnabled = true
            continueButton.visibility = View.VISIBLE
        } else { // if the age is under 18
            invalidAge.visibility = View.VISIBLE
            continueButton.isEnabled = false
            continueButton.visibility = View.GONE
        }
    }

}