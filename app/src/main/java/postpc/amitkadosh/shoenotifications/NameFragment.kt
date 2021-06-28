package postpc.amitkadosh.shoenotifications

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.navigation.findNavController

class NameFragment : Fragment() {

    companion object {
        fun newInstance() = NameFragment()
    }

    private val sharedViewModel: SharedViewModel by lazy {
         ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }
    private val viewModel: NameViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NameViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.name_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.currFragment= FragmentType.NAME
        //finds view
        val firstName : EditText = view.findViewById(R.id.editFirst)
        val lastName : EditText = view.findViewById(R.id.editLast)
        val invalidName: TextView = view.findViewById(R.id.invalidName)
        val continueButton: Button = view.findViewById(R.id.continueButton)

        //set views

        setViews(firstName, lastName, continueButton, invalidName)

        firstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (viewModel.updateFirstName(s.toString())){
                    setViews(firstName, lastName, continueButton, invalidName)
                }
                else{
                    setViewsWhenInvalid(continueButton, invalidName)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        firstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (viewModel.updateLastName(s.toString())){
                    setViews(firstName, lastName, continueButton, invalidName)
                }
                else{
                    setViewsWhenInvalid(continueButton, invalidName)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        continueButton.setOnClickListener {
            //TODO
        }


    }


    private fun setViewsWhenInvalid(
        continueButton: Button,
        invalidName: TextView
    ) {
        continueButton.isEnabled = false
        continueButton.visibility = View.GONE
        invalidName.visibility = View.VISIBLE
    }

    private fun setViews(
        firstName: EditText,
        lastName: EditText,
        continueButton: Button,
        invalidName: TextView
    ) {
        if (viewModel.firstName != null) {
            firstName.setText(viewModel.firstName)
        }
        if (viewModel.lastName != null) {
            lastName.setText(viewModel.lastName)
        }
        if (viewModel.firstName != null && viewModel.lastName != null) {
            continueButton.isEnabled = true
            continueButton.visibility = View.VISIBLE
            invalidName.visibility = View.GONE
        }
        else{
            continueButton.isEnabled = false
            continueButton.visibility = View.GONE
            invalidName.visibility = View.GONE
        }
    }

}