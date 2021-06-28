package postpc.amitkadosh.shoenotifications

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {
    ;

    private lateinit var model: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProvider(this).get(SharedViewModel::class.java)

//        if (sp.get)
//        val intent = Intent(this, AfterOnBoard::class.java)
//        startActivity(intent)

    }

//    private fun openScreenByType(type: FragmentType){
//        when(type){
//            FragmentType.START -> loadFragment(StartFragment())
//            FragmentType.AGE -> loadFragment(AgeFragment())
//            FragmentType.TERMS -> loadFragment(ConditionFragment())
//            FragmentType.MATH -> loadFragment(MathFragment())
//            FragmentType.NAME -> loadFragment(NameFragment())
//            FragmentType.DONE -> openAfterOnBoardActivity()
//        }
//
//    }

//    private fun loadFragment(fragment:Fragment){
////        val transaction = supportFragmentManager.beginTransaction()
////        transaction.replace(R.id.nav_host_fragment, fragment)
////        transaction.disallowAddToBackStack()
////        transaction.commit()
//        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHost.findNavController()
//        navController.navigate(action)
//    }
//
//    private fun openAfterOnBoardActivity(){
//        val intent = Intent(this, AfterOnBoard::class.java)
//        startActivity(intent)
//    }
}