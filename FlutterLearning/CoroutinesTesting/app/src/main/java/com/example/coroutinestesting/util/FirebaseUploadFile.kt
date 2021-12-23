package com.example.coroutinestesting.util

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.*

object FirebaseUploadFile {
    var responseUrl: ((String) -> Unit)? = null
    var storageReference: StorageReference? = null
    fun uploadImage(context: Context, imageUri: Uri) {
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName: String = formatter.format(now)
        storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")
        storageReference?.putFile(imageUri)
            ?.addOnSuccessListener {
                Toast.makeText(context, "Successfully Uploaded", Toast.LENGTH_SHORT).show()
                storageReference?.downloadUrl
                    ?.addOnCompleteListener { task -> //getdownloadUrl
                        val url = task.result
                        responseUrl?.invoke("$url")
                        Log.d("imageResponse", "$url")
                    }


            }?.addOnFailureListener {

                Toast.makeText(context, "Failed to Upload", Toast.LENGTH_SHORT).show()
            }
    }
}