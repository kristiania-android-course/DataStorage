package no.kristiania.android.datastorage

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_shared_preference.*

class SharedPreferenceActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        // Create a shared preference instance


        btn_save_preference.setOnClickListener {
            // create editor and add the key value pair to the preference
        }

        btn_read_preference.setOnClickListener {
            // sharePreference.getString()
        }

        btn_delete_preference.setOnClickListener {
            // create editor and remove the key value pair form the preference
        }
    }


}
