package app.bale.newsapplication.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.bale.newsapplication.R

fun AppCompatActivity?.sendAnEmail(email: String) {

    val intent = Intent(Intent.ACTION_SENDTO).apply {
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        putExtra(Intent.EXTRA_SUBJECT, "")
        putExtra(Intent.EXTRA_TEXT, "")
        data = Uri.parse("mailto:")
    }

    if (this?.packageManager?.let { it1 -> intent.resolveActivity(it1) } != null) {
        startActivity(intent)
    } else {
        this?.showMessage(this.resources.getString(R.string.email_support_error))
    }
}

fun AppCompatActivity.showMessage(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun Context.shareContent(data: String) {
    val shareBody = "$data\nI would like to share this with you."
    val sharingIntent = Intent(Intent.ACTION_SEND)
    sharingIntent.type = "text/plain"
    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
    startActivity(
        Intent.createChooser(
            sharingIntent, getString(R.string.share_using_title)
        )
    )
}
