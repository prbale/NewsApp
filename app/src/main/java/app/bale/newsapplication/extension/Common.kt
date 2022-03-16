package app.bale.newsapplication.extension

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Paint
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.github.marlonlom.utilities.timeago.TimeAgo
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit


fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun TextView.strikeThrough() {
    this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

fun Context.callANumber(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null))
    this.startActivity(intent)
}

fun Context.launchWebsite(webUrl: String) {
    var intent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
    val packageManager = this.packageManager
    val list = packageManager?.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
    if (list?.isEmpty() == true) {
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
    }
    startActivity(intent)
}

fun ImageView.loadImage(imageUrl: String?) = Glide.with(this.context).load(imageUrl).into(this)

fun String.covertTimeToText(): String? {

    var convTime: String? = null

    var prefix: String = ""
    val suffix: String = "Ago"

    try {
        val dateFormat: SimpleDateFormat  = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val pasTime: Date = dateFormat.parse(this) as Date
        val nowTime: Date = Date()
        val dateDiff: Long  = nowTime.time - pasTime.time;

        val second: Long = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
        val minute: Long = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
        val hour: Long   = TimeUnit.MILLISECONDS.toHours(dateDiff);
        val day: Long  = TimeUnit.MILLISECONDS.toDays(dateDiff);

        if (second < 60) {
            convTime = "$second Seconds $suffix"
        } else if (minute < 60) {
            convTime = "$minute Minutes $suffix"
        } else if (hour < 24) {
            convTime = "$hour Hours $suffix"
        } else if (day >= 7) {
            if (day > 360) {
                convTime = (day / 360).toString() + " Years " + suffix
            } else if (day > 30) {
                convTime = (day / 30).toString() + " Months " + suffix
            } else {
                convTime = (day / 7).toString() + " Week " + suffix
            }
        } else if (day < 7) {
            convTime = "$day Days $suffix"
        }

    } catch (e: ParseException) {

    }

    return convTime
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.dateTimeAgo(): String {
    val instant = Instant.parse(this)
    val ms = instant.toEpochMilli()
    return TimeAgo.using(ms).replaceFirstChar { it.uppercaseChar() }
}
