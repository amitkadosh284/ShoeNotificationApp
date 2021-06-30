package postpc.amitkadosh.shoenotifications

import android.content.Intent
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

    private var firstValid: Boolean = false
    private var lastValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.name_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.setProgress(viewModel.progress)
        //finds view
        val firstName : EditText = view.findViewById(R.id.editFirst)
        val lastName : EditText = view.findViewById(R.id.editLast)
        val invalidName: TextView = view.findViewById(R.id.invalidName)
        val continueButton: Button = view.findViewById(R.id.continueButton)

        //set views

        setViews(firstName, lastName, continueButton, invalidName)

        lastName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (viewModel.checkValidity(s.toString())){
                    lastValid = true
                    setViews(firstName, lastName, continueButton, invalidName)
                }
                else{
                    lastValid = false
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
                if (viewModel.checkValidity(s.toString())){
                    firstValid = true
                    setViews(firstName, lastName, continueButton, invalidName)
                }
                else{
                    firstValid = false
                    setViewsWhenInvalid(continueButton, invalidName)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        continueButton.setOnClickListener {
            viewModel.firstName = firstName.text.toString()
            viewModel.lastName = lastName.text.toString()
            sharedViewModel.setProgress(0)
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
            firstValid = true
            firstName.setText(viewModel.firstName)
        }
        if (viewModel.lastName != null) {
            lastValid = true
            lastName.setText(viewModel.lastName)
        }
        if (firstValid && lastValid) {
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