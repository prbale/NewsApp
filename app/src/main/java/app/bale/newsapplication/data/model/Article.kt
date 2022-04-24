package app.bale.newsapplication.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

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

): Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readInt(),
    parcel.readParcelable(Source::class.java.classLoader),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString()
  )

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeInt(articleId)
    parcel.writeParcelable(source, flags)
    parcel.writeString(author)
    parcel.writeString(title)
    parcel.writeString(description)
    parcel.writeString(url)
    parcel.writeString(urlToImage)
    parcel.writeString(publishedAt)
    parcel.writeString(content)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<Article> {
    override fun createFromParcel(parcel: Parcel): Article {
      return Article(parcel)
    }

    override fun newArray(size: Int): Array<Article?> {
      return arrayOfNulls(size)
    }
  }

}
