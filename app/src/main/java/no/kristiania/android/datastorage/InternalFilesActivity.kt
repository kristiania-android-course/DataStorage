package no.kristiania.android.datastorage

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cache.*
import java.io.FileNotFoundException


class InternalFilesActivity : AppCompatActivity() {

    val internalFileName = "my-internal-file"


    // First lets make a function to manipulate files

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)

        val fileContent = "Hi, How are you?\nThis is an android file demo"

        // button click listener to save the data to cache
        btn_save_internal.setOnClickListener {
            // Get file out stream
            val fileOutPutStream = this.openFileOutput("my-internal-file", Context.MODE_PRIVATE)
            // Write content to the file output stream
            fileOutPutStream.write(fileContent.toByteArray())
            // close the file stream
            fileOutPutStream.close()
        }

        // Button click listener to read form the cache
        btn_read_internal.setOnClickListener {
            // get all the files list from the internal folder
            val fileList = this.fileList()
            val ourFile = fileList[0]
            // create an input stream for the file
            val fileInputStream = this.openFileInput(ourFile)
            // Get the data
            val string = fileInputStream.bufferedReader().useLines { lines ->
                // fold extension function allows to fold the sequence to a flat string
                lines.fold("") { appended, line ->
                    "$appended\n$line"
                }
            }
            fileInputStream.close()
            Toast.makeText(this, string, Toast.LENGTH_LONG).show()
        }

        // Button click listener to delete form the cache
        btn_delete_internal.setOnClickListener {

            val fileList = this.fileList()
            try {
                val ourFile = fileList[0]
                this.deleteFile(ourFile)

            } catch (e: ArrayIndexOutOfBoundsException) {
                e.printStackTrace()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        }

    }


}
