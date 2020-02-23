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
            getSharedPreferences(
                "no.kristiania.android.data-storage",
                Context.MODE_PRIVATE
            )

        btn_save_preference.setOnClickListener {
            saveToPreference()
        }

        btn_read_preference.setOnClickListener {
            readFromPreference()
        }

        btn_delete_preference.setOnClickListener {
            deleteFromPreference()
        }
    }

    private fun deleteFromPreference() {
        // create editor and remove the key value pair form the preference
        sharedPreferences.edit().remove("key_data").commit()
    }

    private fun readFromPreference() {
        val keyValueData = sharedPreferences
            .getString("key_data", null)
        Toast.makeText(this, keyValueData, Toast.LENGTH_SHORT).show()
    }

    private fun saveToPreference() {
        val editor = sharedPreferences.edit()
        editor.putString("key_data", "Well everything is fine..!!")
        editor.putBoolean("KEY_TRUE_FALSE_CHECK", true)
        editor.commit()
    }
}
