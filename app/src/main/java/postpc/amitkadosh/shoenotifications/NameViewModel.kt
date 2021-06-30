package postpc.amitkadosh.shoenotifications

import androidx.lifecycle.ViewModel

class NameViewModel : ViewModel() {

    val progress: Int = 5
    var firstName : String? = null
    var lastName : String? = null


    /**
     * function check if the input string is only english letters
     */
    fun checkValidity(name: String): Boolean {
        for (c in name)
        {
            if (c !in 'A'..'Z' && c !in 'a'..'z') {
                return false
            }
        }
        return true
    }
}