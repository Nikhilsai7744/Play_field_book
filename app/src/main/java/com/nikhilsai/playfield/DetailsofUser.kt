
package com.nikhilsai.playfield

import android.Manifest
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nikhilsai.playfield.ui.theme.EventAppTheme
import com.nikhilsai.playfield.ui.theme.Primary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.min

class UserProfileActivity : ComponentActivity() {

    enum class CameraPermissionStatus { NoPermission, PermissionGranted, PermissionDenied }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cameraPermissionStatusState = mutableStateOf(CameraPermissionStatus.NoPermission)
        val photoUriState: MutableState<Uri?> = mutableStateOf(null)
        val hasPhotoState = mutableStateOf(false)
        val resolver = applicationContext.contentResolver

        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                cameraPermissionStatusState.value = if (isGranted) {
                    CameraPermissionStatus.PermissionGranted
                } else {
                    CameraPermissionStatus.PermissionDenied
                }
            }

        val takePhotoLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { isSaved ->
                hasPhotoState.value = isSaved
            }

        val takePhoto: () -> Unit = {
            hasPhotoState.value = false

            val values = ContentValues().apply {
                val title = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
                put(MediaStore.Images.Media.TITLE, "Compose Camera Example Image - $title")
                put(MediaStore.Images.Media.DISPLAY_NAME, title)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
            }

            val uri = resolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
            )
            takePhotoLauncher.launch(uri)
            photoUriState.value = uri
        }

        val getThumbnail: (Uri?) -> ImageBitmap? = { uri ->
            val targetSize = 256f
            uri?.let {
                resolver.openInputStream(it)?.let { inputStream ->
                    BitmapFactory.decodeStream(inputStream)?.let { bitmap ->
                        val height = bitmap.height.toFloat()
                        val width = bitmap.width.toFloat()
                        val scaleFactor = min(targetSize / height, targetSize / width)
                        Bitmap.createScaledBitmap(bitmap, (scaleFactor * width).toInt(), (scaleFactor * height).toInt(), true)
                            .let { scaledBitmap ->
                                val rotation = getImageRotation(resolver, uri)
                                Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.width, scaledBitmap.height, Matrix().apply { postRotate(rotation.toFloat()) }, true)
                            }.asImageBitmap()
                    }
                }
            }
        }

        val getFullImage: (Uri?) -> ImageBitmap? = { uri ->
            uri?.let {
                resolver.openInputStream(it)?.let { inputStream ->
                    BitmapFactory.decodeStream(inputStream)?.let { bitmap ->
                        val rotation = getImageRotation(resolver, uri)
                        Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, Matrix().apply { postRotate(rotation.toFloat()) }, true)
                            .asImageBitmap()
                    }
                }
            }
        }

        setContent {
            val cameraPermissionStatus by remember { cameraPermissionStatusState }
            val hasPhoto by remember { hasPhotoState }
            var shouldShowFullImage by remember { mutableStateOf(false) }

            // State variables for input fields
            var name by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var location by remember { mutableStateOf("") }

            EventAppTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TakePhotoButton(
                            cameraPermissionStatus = cameraPermissionStatus,
                            requestPermissionLauncher = requestPermissionLauncher,
                            takePhoto = takePhoto
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (hasPhoto) {
                            val thumbnail = getThumbnail(photoUriState.value)
                            thumbnail?.let { bitmap ->
                                Image(
                                    bitmap = bitmap,
                                    contentDescription = "Thumbnail of Saved Photo",
                                    modifier = Modifier
                                        .clickable { shouldShowFullImage = true }
                                        .size(256.dp)
                                )
                            }
                        }

                        // Input fields for user details










                        //heretextfields
                        Spacer(modifier = Modifier.height(16.dp))

                        // Save button






                        //save here
                    }

                    if (shouldShowFullImage && hasPhoto) {
                        val fullImage = getFullImage(photoUriState.value)
                        fullImage?.let { bitmap ->
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable { shouldShowFullImage = false }
                            ) {
                                Image(
                                    bitmap = bitmap,
                                    contentDescription = "Full image of Saved Photo",
                                    modifier = Modifier.align(Alignment.Center)
                                )
                                Surface(
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.background)
                                        .align(Alignment.Center)
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = "Click to Close",
                                        style = MaterialTheme.typography.headlineLarge.copy(
                                            fontWeight = FontWeight.ExtraBold
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getImageRotation(resolver: ContentResolver, uri: Uri): Int {
        val cursor = resolver.query(uri, arrayOf(MediaStore.Images.Media.ORIENTATION), null, null, null)
        var result = 0

        cursor?.use {
            if (it.moveToFirst()) {
                val index = it.getColumnIndex(MediaStore.Images.Media.ORIENTATION)
                result = it.getInt(index)
            }
        }
        return result
    }
}

@Composable
fun TakePhotoButton(
    cameraPermissionStatus: UserProfileActivity.CameraPermissionStatus,
    requestPermissionLauncher: ActivityResultLauncher<String>,
    takePhoto: () -> Unit
) {
    OutlinedButton(
        onClick = {
            when (cameraPermissionStatus) {
                UserProfileActivity.CameraPermissionStatus.NoPermission ->
                    requestPermissionLauncher.launch(Manifest.permission.CAMERA)

                UserProfileActivity.CameraPermissionStatus.PermissionGranted ->
                    takePhoto()

                UserProfileActivity.CameraPermissionStatus.PermissionDenied -> {}
            }
        }
    ) {
        Text(
            text = when (cameraPermissionStatus) {
                UserProfileActivity.CameraPermissionStatus.NoPermission ->
                    "Request Camera Permissions"

                UserProfileActivity.CameraPermissionStatus.PermissionDenied ->
                    "Camera Permissions Have Been Denied"

                UserProfileActivity.CameraPermissionStatus.PermissionGranted ->
                    "Take Photo"
            }
        )
    }
}
