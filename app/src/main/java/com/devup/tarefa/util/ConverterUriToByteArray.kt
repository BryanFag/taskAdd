import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.exifinterface.media.ExifInterface
import android.net.Uri
import java.io.ByteArrayInputStream

object ConverterUriToByteArray {

    fun uriToByteArray(context: Context, uri: Uri): ByteArray? {
        return context.contentResolver.openInputStream(uri)?.use { it.readBytes() }
    }

    fun decodeByteArrayWithCorrectOrientation(byteArray: ByteArray): Bitmap {
        val exif = ExifInterface(ByteArrayInputStream(byteArray))
        val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )

        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        return rotateBitmapIfRequired(bitmap, orientation)
    }

    private fun rotateBitmapIfRequired(bitmap: Bitmap, orientation: Int): Bitmap {
        val matrix = Matrix()
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
            else -> return bitmap
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
}
