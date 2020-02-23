package no.kristiania.android.datastorage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_save_external_cache.*

class SaveExternalCacheActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_external_cache)
        val fileContent =
            "Hi, How are you?\nThis is an android file demo \nfor external private file"


        btn_save_external.setOnClickListener {
            // getExternalFilesDir

            // Lets have a function to write to files
        }

        btn_read_external.setOnClickListener {
            // getExternalFilesDir

            // Lets have a function to write to files

        }

        btn_delete_external.setOnClickListener {
            // getExternalFilesDir

            // Delete
        }
    }


}
