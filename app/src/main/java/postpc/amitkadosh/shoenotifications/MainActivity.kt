package postpc.amitkadosh.shoenotifications

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {
    ;

    private lateinit var sp: SharedPreferences
    private val shareViewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sp = this.getSharedPreferences("on_board_progress", Context.MODE_PRIVATE)
        if (sp.getBoolean("done", false)){
            startAfterOnBoardActivity()
        }

        //finds view
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        progressBar.max = shareViewModel.max


        shareViewModel.progressLiveData.observe(this, Observer { it->
            if (it == 0){
                startAfterOnBoardActivity()
            }
            else{
                progressBar.progress = shareViewModel.progressLiveData.value!!
            }
        })

    }

    private fun startAfterOnBoardActivity() {
        val intent = Intent(this, AfterOnBoard::class.java)
        startActivity(intent)
    }
}