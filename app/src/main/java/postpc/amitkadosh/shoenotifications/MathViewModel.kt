package postpc.amitkadosh.shoenotifications

import androidx.lifecycle.ViewModel
import java.lang.Exception
import kotlin.random.Random

class MathViewModel : ViewModel() {
    var isDone: Boolean = false
    var equation: String = ""
    lateinit var solution: String
    private var correctSol: Int = 0

    fun generateEquation(range1: Int, range2: Int): String{
        val num1: Int = Random.nextInt(range1)
        val num2: Int = Random.nextInt(range1)
        val num3: Int = Random.nextInt(range1)
        val operator1: Int = Random.nextInt(range2)
        val operator2: Int = Random.nextInt(range2)
        var equation: String = ""

        when(operator1){
            1 -> {
                equation = "($num1 + $num2)"
                correctSol = num1 + num2
            }
            2 -> {
                equation = "($num1 X $num2)"
                correctSol = num1 + num2
            }
            3 -> {
                equation = "($num1 / $num2)"
                correctSol = num1 + num2
            }
            4 -> {
                equation = "($num1 - $num2)"
                correctSol = num1 + num2
            }
        }

        when(operator2){
            1 -> {
                equation += " + $num3"
                correctSol += num3
            }
            2 -> {
                equation += " X $num3"
                correctSol *= num3
            }
            3 -> {
                equation += " / $num3"
                correctSol /= num3
            }
            4 -> {
                equation += " - $num3"
                correctSol -= num3
            }
        }
        return equation
    }

    fun updateSolution(sol: String) : Boolean{
        try{
            if (sol.toInt() == correctSol){
                solution = sol
                return true
            }
            return false
        }catch (e : Exception){
            return false
        }
    }
}