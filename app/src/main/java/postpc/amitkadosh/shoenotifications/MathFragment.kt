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
import androidx.core.widget.doOnTextChanged
import androidx.navigation.findNavController

class MathFragment : Fragment() {

    companion object {
        fun newInstance() = MathFragment()
    }

    private val sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }
    private val viewModel: MathViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MathViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.math_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.currFragment = FragmentType.MATH
        //finds views
        val equation: TextView = view.findViewById(R.id.equation) as TextView
        val solution: EditText = view.findViewById(R.id.solution)
        val invalidSol: TextView = view.findViewById(R.id.invalidSol)
        val continueButton: Button = view.findViewById(R.id.continueButton)

        //sets views
        setViews(equation, solution, invalidSol, continueButton)

        solution.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                solution.text = s
                setViews(equation, solution,invalidSol, continueButton)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        continueButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_mathFragment_to_nameFragment)
        }
    }



    private fun setViews(
        equation: TextView,
        solution: EditText,
        invalidSol: TextView,
        continueButton: Button,
    ) {
        equation.text = viewModel.equation
        if (!viewModel.updateSolution(solution.text.toString())) {
            invalidSol.visibility = View.VISIBLE
            continueButton.isEnabled = false
            continueButton.visibility = View.GONE
        } else {
            invalidSol.visibility = View.GONE
            continueButton.isEnabled = true
            continueButton.visibility = View.VISIBLE
        }
    }

}