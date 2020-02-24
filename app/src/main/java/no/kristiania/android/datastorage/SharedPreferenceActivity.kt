package no.kristiania.android.datastorage

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_shared_preference.*

class SharedPreferenceActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        // Create a shared preference instance
        sharedPreferences =
            getSharedPreferences("no.kristainia.android.preference", Context.MODE_PRIVATE)

        btn_save_preference.setOnClickListener {
            // create editor and add the key value pair to the preference
            val editor = sharedPreferences.edit()
            editor.putString("KEY_STR", "Well it may work!")
            editor.apply()
        }

        btn_read_preference.setOnClickListener {
            val str = sharedPreferences.getString("KEY_STR", null)
            Toast.makeText(this, str, Toast.LENGTH_LONG).show()
        }

        btn_delete_preference.setOnClickListener {
            // create editor and remove the key value pair form the preference
            val editor = sharedPreferences.edit()
            editor.remove("KEY_STR")
            editor.apply()
        }
    }


}
