package no.kristiania.android.datastorage

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
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
            val file = File(folder, "my_external_public_file")

            // create file
            writeToFile(fileContent, file)
            // Write in the file

            // create an image
            val imageFile = File(folder, "my_image.jpeg")
            storeImage(imageFile)

        }

        btn_read_public.setOnClickListener {
            // Get the public folder
            val folder = Environment.getExternalStorageDirectory()
            val file = File(folder, "my_external_public_file")
            // create file
            readFromFile(file)
            // read from the file
        }

    }

    private fun readFromFile(file: File) {
        val fIn = FileInputStream(file)
        val string = fIn.bufferedReader().useLines { lines ->
            lines.fold("") { append, line ->
                "$append\n$line"
            }
        }

        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }

    private fun writeToFile(fileContent: String, file: File) {
        val fOut = FileOutputStream(file, true)
        fOut.write(fileContent.toByteArray())
        fOut.close()
    }

    // How to store an image in a file
    private fun storeImage(file: File) {
        // Get the drawable from the drawable folder
        // convert it to bitmap
        val bitmap = ContextCompat
            .getDrawable(this, R.drawable.android)?.toBitmap(2001, 1789)

        FileOutputStream(file, true)
            .use { fileOutStream ->
                // Compress and save to file output stream
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 85, fileOutStream)
                // close the stream
                fileOutStream.close()
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
                    arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1234
                )
        }
    }


    // Lets have a function to read and write to files


}
