package postpc.amitkadosh.shoenotifications

import androidx.lifecycle.ViewModel

class NameViewModel : ViewModel() {

    var firstName : String? = null
    var lastName : String? = null

    fun updateFirstName(name: String) : Boolean{
        if (checkValidity(name)){
            firstName = name
            return true
        }
        return false
    }

    fun updateLastName(name: String) : Boolean{
        if (checkValidity(name)){
            lastName = name
            return true
        }
        return false
    }

    private fun checkValidity(name: String): Boolean {
        for (c in name)
        {
            if (c !in 'A'..'Z' && c !in 'a'..'z') {
                return false
            }
        }
        return true
    }
}