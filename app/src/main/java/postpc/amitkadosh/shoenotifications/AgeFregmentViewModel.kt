package postpc.amitkadosh.shoenotifications

import androidx.lifecycle.ViewModel
import java.util.*

class AgeFregmentViewModel : ViewModel() {

    var calender : Calendar = Calendar.getInstance()
    var cYear : Int = calender.get(Calendar.YEAR)
    var cMonth : Int = calender.get(Calendar.MONTH)
    var cDay : Int = calender.get(Calendar.DAY_OF_MONTH)
    var bYear : Int = calender.get(Calendar.YEAR)
    var bMonth : Int = calender.get(Calendar.MONTH)
    var bDay : Int = calender.get(Calendar.DAY_OF_MONTH)
    var isDone : Boolean = false
    var age : Int? = null

    fun setDate(year: Int, month: Int, day: Int) {
        bYear = year
        bMonth = month
        bDay = day
        calculateAge()
    }

    private fun calculateAge() {
        if (cYear - bYear > 0){
            age = if (cMonth - bMonth > 0){
                cYear - bYear
            } else if (cMonth - bMonth == 0){
                if (cDay - bDay >= 0){
                    cYear - bYear
                } else{
                    cYear - bYear - 1
                }
            } else{
                cYear - bYear - 1
            }
        }
    }
}