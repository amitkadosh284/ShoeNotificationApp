package postpc.amitkadosh.shoenotifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class SharedViewModel : ViewModel() {
    val max: Int = 5
    private val _progressLivaData: MutableLiveData<Int> = MutableLiveData()
    val progressLiveData: LiveData<Int>
        get() = _progressLivaData

    fun setProgress(num: Int){
        _progressLivaData.value = num
    }
}
