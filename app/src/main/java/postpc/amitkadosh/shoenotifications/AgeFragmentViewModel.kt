package postpc.amitkadosh.shoenotifications

import androidx.lifecycle.ViewModel
import java.util.*

class AgeFragmentViewModel : ViewModel() {

    val progress: Int = 2
    private var calender : Calendar = Calendar.getInstance()
    private var cYear : Int = calender.get(Calendar.YEAR)
    private var cMonth : Int = calender.get(Calendar.MONTH)
    private var cDay : Int = calender.get(Calendar.DAY_OF_MONTH)
    var bYear : Int = calender.get(Calendar.YEAR)
    var bMonth : Int = calender.get(Calendar.MONTH)
    var bDay : Int = calender.get(Calendar.DAY_OF_MONTH)
    var age : Int? = null

    /**
     * function that set the date in the view model
     */
    fun setDate(year: Int, month: Int, day: Int) {
        bYear = year
        bMonth = month
        bDay = day
        calculateAge()
    }

    /**
     * function that calculate the age of the user by the date of birth he insert
     */
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