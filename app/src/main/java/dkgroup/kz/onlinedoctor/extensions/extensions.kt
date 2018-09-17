package dkgroup.kz.onlinedoctor.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import dkgroup.kz.onlinedoctor.R
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Cancellable


fun ImageView.loadRoundedImage(
        url: String?,
        ctx: Context? = null
) {
    var options = RequestOptions()
            .placeholder(R.drawable.profile2)
            .error(R.drawable.profile2)
            .transforms(CenterCrop(),CircleCrop())

    Glide.with(ctx ?: context)
            .load(url)
            .apply(options)
            .into(this)
}



fun DocumentReference.rxSingleValue(): Single<DocumentSnapshot> = Single.create {
    /*val listener = */object : SingleEmitter<DocumentSnapshot> {
    override fun isDisposed(): Boolean {
        return it.isDisposed
    }

    override fun setCancellable(c: Cancellable?) {
        it.setCancellable(c)
    }

    override fun setDisposable(s: Disposable?) {
        it.setDisposable(s)
    }

    override fun onSuccess(t: DocumentSnapshot) {
        it.onSuccess(t)
    }

    override fun onError(t: Throwable) {
        it.onError(t)
    }
}
//    it.setCancellable { removeEventListener(listener) }
//
//    addListenerForSingleValueEvent(listener)
}