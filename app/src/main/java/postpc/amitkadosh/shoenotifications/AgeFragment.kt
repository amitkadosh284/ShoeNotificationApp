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

    private lateinit var viewModel: AgeFregmentViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.age_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(AgeFregmentViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        sharedViewModel.currFragment = FragmentType.AGE
        //finds view
        val date : DatePicker = view.findViewById(R.id.datePicker)
        val invalidAge : TextView = view.findViewById(R.id.invalidAge)
        val continueButton : Button = view.findViewById(R.id.continueButton)

        //sets view
        setViews(invalidAge, continueButton)

        date.init(viewModel.bYear,viewModel.bMonth, viewModel.bDay, DatePicker.OnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            viewModel.setDate(year, monthOfYear, dayOfMonth)
            setViews(invalidAge, continueButton)
        })

        continueButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_age_fragment_to_conditionFragment)
        }

    }


    private fun setViews(invalidAge: TextView, continueButton: Button) {
        if (viewModel.age == null) {
            invalidAge.visibility = View.GONE
            continueButton.isEnabled = false
            continueButton.visibility = View.GONE
        } else if (viewModel.age!! >= 18) {
            invalidAge.visibility = View.GONE
            continueButton.isEnabled = true
            continueButton.visibility = View.VISIBLE
        } else {
            invalidAge.visibility = View.VISIBLE
            continueButton.isEnabled = false
            continueButton.visibility = View.GONE
        }
    }

}