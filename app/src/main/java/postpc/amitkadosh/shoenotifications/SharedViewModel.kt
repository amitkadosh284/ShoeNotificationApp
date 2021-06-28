package postpc.amitkadosh.shoenotifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class FragmentType {
    START, AGE, TERMS, MATH, NAME, DONE
}

class SharedViewModel : ViewModel() {
    var currFragment: FragmentType? = null

}
