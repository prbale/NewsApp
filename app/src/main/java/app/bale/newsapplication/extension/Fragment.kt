package app.bale.newsapplication.extension

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, addToBackstack: Boolean = true) {
    supportFragmentManager.inTransaction {
        add(frameId, fragment)
        if(addToBackstack) { addToBackStack(fragment.javaClass.simpleName) }
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, addToBackstack: Boolean = true) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment)
        if(addToBackstack) { addToBackStack(fragment.javaClass.simpleName) }
    }
}

fun Fragment.addFragment(fragment: Fragment, frameId: Int, addToBackstack: Boolean = true) {
    val currentFragment = this
    activity?.supportFragmentManager?.inTransaction {
        add(frameId, fragment)
        hide(currentFragment)
        if(addToBackstack) { addToBackStack(fragment.javaClass.simpleName) }
    }
}

@Suppress("unused")
fun Fragment.replaceFragment(fragment: Fragment, frameId: Int, addToBackstack: Boolean = true) {
    val currentFragment = this
    activity?.supportFragmentManager?.inTransaction {
        replace(frameId, fragment)
        hide(currentFragment)
        if(addToBackstack) { addToBackStack(fragment.javaClass.simpleName) }
    }
}

fun Fragment.showMessage(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(context, message, duration).show()
}
