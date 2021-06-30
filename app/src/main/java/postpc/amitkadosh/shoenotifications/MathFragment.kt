package postpc.amitkadosh.shoenotifications

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pl.droidsonroids.gif.GifImageView

class MathFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }
    private val viewModel: MathViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MathViewModel::class.java)
    }

    companion object {
        fun newInstance() = MathFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.math_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.setProgress(viewModel.progress)
        //finds views
        val equation: TextView = view.findViewById(R.id.equation) as TextView
        val solution: EditText = view.findViewById(R.id.solution)
        val invalidSol: TextView = view.findViewById(R.id.invalidSol)
        val continueButton: Button = view.findViewById(R.id.continueButton)
//        val sendSolButton: FloatingActionButton = view.findViewById(R.id.sendSol)
        val correctSol: TextView = view.findViewById(R.id.correctSolution)
        val successGif: GifImageView = view.findViewById(R.id.successGif)


        //sets views
        setViews(equation, solution, invalidSol, continueButton, correctSol, successGif)

        solution.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setViews(equation, solution,invalidSol, continueButton, correctSol, successGif)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
//
//        sendSolButton.setOnClickListener{
//            setViews(equation, solution, invalidSol, continueButton, sendSolButton, correctSol, successGif)
//        }

        continueButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_mathFragment_to_nameFragment)
        }
    }



    private fun setViews(
        equation: TextView,
        solution: EditText,
        invalidSol: TextView,
        continueButton: Button,
        correctSol: TextView,
        successGif: GifImageView
    ) {
        equation.text = viewModel.equation
        if (viewModel.solution != null){
            solution.visibility = View.GONE
//            sendSolButton.isEnabled = false
//            sendSolButton.visibility = View.GONE
            invalidSol.visibility = View.GONE
            continueButton.isEnabled = true
            continueButton.visibility = View.VISIBLE
            correctSol.visibility = View.VISIBLE
            correctSol.text = viewModel.solution
            successGif.visibility = View.VISIBLE
        }
        else{
            val sol: String = solution.text.toString()
            if(sol.isEmpty()){
                solution.visibility = View.VISIBLE
//            sendSolButton.isEnabled = true
//            sendSolButton.visibility = View.VISIBLE
                invalidSol.visibility = View.GONE
                continueButton.isEnabled = false
                continueButton.visibility = View.GONE
                correctSol.visibility = View.GONE
                successGif.visibility = View.GONE
            }
            else if (!viewModel.updateSolution(sol)) {
                solution.visibility = View.VISIBLE
//            sendSolButton.isEnabled = true
//            sendSolButton.visibility = View.VISIBLE
                invalidSol.visibility = View.VISIBLE
                continueButton.isEnabled = false
                continueButton.visibility = View.GONE
                correctSol.visibility = View.GONE
                successGif.visibility = View.GONE
            } else {
                Log.d("enter correct sol", sol)
                solution.visibility = View.GONE
//            sendSolButton.isEnabled = false
//            sendSolButton.visibility = View.GONE
                invalidSol.visibility = View.GONE
                continueButton.isEnabled = true
                continueButton.visibility = View.VISIBLE
                correctSol.visibility = View.VISIBLE
                correctSol.text = sol
                successGif.visibility = View.VISIBLE
            }
        }
    }
}