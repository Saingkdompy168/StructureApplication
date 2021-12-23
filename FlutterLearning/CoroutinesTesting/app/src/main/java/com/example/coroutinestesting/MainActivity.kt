package com.example.coroutinestesting

import android.app.ProgressDialog
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.coroutinestesting.activities.GroupDataActivity
import com.example.coroutinestesting.databinding.ActivityUploadBinding
import com.example.coroutinestesting.model.JoinCoroutines
import com.example.coroutinestesting.viewmodel.FlowViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_upload.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var job: Job
    var imageUri: Uri? = null
    var storageReference: StorageReference? = null
    var progressDialog: ProgressDialog? = null
    var storage = Firebase.storage
    private var resultLauncher: ActivityResultLauncher<String>? = null

    private var myConstraintLayout: ConstraintLayout? = null
    private var animationDrawable: AnimationDrawable? = null

    var binding: ActivityUploadBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        myConstraintLayout = binding?.constraintLayoutMain as ConstraintLayout
        animationDrawable = myConstraintLayout?.background as AnimationDrawable
        animationDrawable?.setEnterFadeDuration(10)
        animationDrawable?.setExitFadeDuration(5000)
        animationDrawable?.start()

        binding?.selectImagebtn?.setOnClickListener {
            selectImage()
        }

        binding?.uploadimagebtn?.setOnClickListener {
            uploadImage()
        }

        val viewModel = viewModels<FlowViewModel>()


        CoroutineScope(Dispatchers.Main).launch {
            Log.d("MyTag", "Coroutines from  ${Thread.currentThread().name}")
            JoinCoroutines().testing()
            Log.d("MyTag", "Came back to main  ${Thread.currentThread().name}")
        }

        binding?.groupData?.setOnClickListener {
            GroupDataActivity.launch(this)
        }

//        CoroutineScope(Dispatchers.IO).launch {
//            Log.d("mytage", "Hello current =====>${Thread.currentThread().name}")
//        }
//        CoroutineScope(Dispatchers.Main).launch {
//            Log.d("mytage", "Hello current =====> ${Thread.currentThread().name}")
//        }


//        job = CoroutineScope(Dispatchers.Main).launch {
//            downloadData()
//        }
//
//        button_status.setOnClickListener {
//            when {
//                job.isActive -> {
//                    text_view_status.text = "isActive"
//                }
//
//                job.isCancelled -> {
//                    text_view_status.text = "isCancelled"
//                }
//
//                job.isCompleted -> {
//                    text_view_status.text = "isCompleted"
//                }
//                else -> {
//                    text_view_status.text = "any"
//                }
//            }
//        }
//
//        button_cancle.setOnClickListener {
//            job.cancel()
//        }

        resultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
            imageUri = result
            binding?.firebaseimage?.setImageURI(imageUri)
        }

    }

    override fun onPause() {
        super.onPause()
        if (animationDrawable != null) {
            super.onPause();
            if (animationDrawable?.isRunning == true) {
                animationDrawable?.stop()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        if (animationDrawable != null) {
            if (!animationDrawable?.isRunning!!) {
                animationDrawable?.start()
            }
        }
    }

    private fun uploadImage() {
        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Uploading File....")
        progressDialog!!.show()
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName: String = formatter.format(now)
        storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")
        storageReference?.putFile(imageUri!!)
            ?.addOnSuccessListener {
                binding?.firebaseimage?.setImageURI(null)
                Toast.makeText(this@MainActivity, "Successfully Uploaded", Toast.LENGTH_SHORT)
                    .show()
                if (progressDialog!!.isShowing) progressDialog!!.dismiss()
                storageReference?.downloadUrl
                    ?.addOnCompleteListener { task -> //getdownloadUrl
                        val url = task.result
                        Log.d("imageRespone", "$url")
                    }


            }?.addOnFailureListener {
                if (progressDialog!!.isShowing) progressDialog!!.dismiss()
                Toast.makeText(this@MainActivity, "Failed to Upload", Toast.LENGTH_SHORT).show()
            }
    }


    private fun selectImage() {
//        val intent = Intent()
//        intent.type = "image/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        startActivityForResult(intent, 100)
        resultLauncher?.launch("image/*")
    }


    private suspend fun downloadData() {
        withContext(Dispatchers.IO) {
            repeat(30) {
                delay(1000)
                Log.d("Mytage", "round ======> $it")
            }

        }
    }
}