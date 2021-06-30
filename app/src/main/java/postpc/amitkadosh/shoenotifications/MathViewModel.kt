package postpc.amitkadosh.shoenotifications

import android.util.Log
import androidx.lifecycle.ViewModel
import java.lang.Exception
import kotlin.random.Random

class MathViewModel : ViewModel() {
    val progress: Int = 4
    var equation: String = generateEquation()
    var solution: String? = null
    private var correctSol: Int = 0

    fun generateEquation(): String{
        val num1: Int = Random.nextInt(1,10)
        val num2: Int = Random.nextInt(1, 10)
        val num3: Int = Random.nextInt(1, 10)
        val operator1: Int = Random.nextInt(1, 3)
        val operator2: Int = Random.nextInt(1, 3)
        var equation: String = ""

        when(operator1){
            1 -> {
                equation = "($num1 + $num2)"
                correctSol = num1 + num2
            }
            2 -> {
                equation = "($num1 X $num2)"
                correctSol = num1 * num2
            }
            3 -> {
                equation = "($num1 - $num2)"
                correctSol = num1 - num2
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