package no.kristiania.android.datastorage

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_public_external.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class PublicExternalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_external)
        val fileContent =
            "Hi, How are you?\nThis is an android file demo \nfor External public file"

        //Check and ask for permission
        executePermission()

        btn_save_public.setOnClickListener {
            // Get the public folder
            val folder = Environment.getExternalStorageDirectory()
            val file = File(folder, "my-public-file")
            write(fileContent, file)
        }

        btn_read_public.setOnClickListener {
            val folder = Environment.getExternalStorageDirectory()
            val file = File(folder, "my-public-file")
            readFrom(file)
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // make sure we have  valid permission
        if (requestCode == 1234
            && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, "All need permissions are granted.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Need permission to work", Toast.LENGTH_LONG).show()
        }

    }


    // Permission handling
    private fun executePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat
                .requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1234
                )
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
