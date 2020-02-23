package no.kristiania.android.datastorage

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_save_external_cache.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class SaveExternalCacheActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_external_cache)
        val fileContent =
            "Hi, How are you?\nThis is an android file demo \nfor external private file"


        btn_save_external.setOnClickListener {
            val folder = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            val file = File(folder, "my-external-file")
            write(fileContent, file)
        }

        btn_read_external.setOnClickListener {
            val folder = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            val file = File(folder, "my-external-file")
            Log.d("tag", file.absolutePath)
            readFrom(file)
        }

        btn_delete_external.setOnClickListener {
            val folder = this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            val file = File(folder, "my-external-file")
            file.delete()
        }
    }

    private fun readFrom(file: File) {
        val fIn = FileInputStream(file)
        val string = fIn.bufferedReader()
            .useLines { lines ->
                lines.fold("") { appnd, line ->
                    "$appnd\n$line"
                }
            }
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }

    // Lets have a function to read and write to files
    private fun write(fileContents: String, file: File) {

        val fOut = FileOutputStream(file, true)
        fOut.write(fileContents.toByteArray())
        fOut.close()
    }
}
