package no.kristiania.android.datastorage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cache.*


class InternalFilesActivity : AppCompatActivity() {

    val internalFileName = "my-internal-file"

    // First lets make a function to manipulate files

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)

        val fileContent =
            "Hi, How are you?\nThis is an android file demo \nfor internal private file"

        // button click listener to save the data to cache
        btn_save_internal.setOnClickListener {
            // Get file out stream

            // Write content to the file output stream

            // close the file stream

        }

        // Button click listener to read form the cache
        btn_read_internal.setOnClickListener {
            // get all the files list from the internal folder

            // create an input stream for the file

            // Get the data

            //Toast.makeText(this, string, Toast.LENGTH_LONG).show()
        }

        // Button click listener to delete form the cache
        btn_delete_internal.setOnClickListener {
            // get all the files list from the internal folder and delete our

        }

    }
}
