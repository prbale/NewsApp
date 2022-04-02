package app.bale.newsapplication.data.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "article")
data class Article (

  @PrimaryKey(autoGenerate = true)
  val articleId: Int,

  @Embedded var source      : Source? = Source(),
  var author      : String? = null,
  var title       : String? = null,
  var description : String? = null,
  var url         : String? = null,
  var urlToImage  : String? = null,
  var publishedAt : String? = null,
  var content     : String? = null

): Parcelable
