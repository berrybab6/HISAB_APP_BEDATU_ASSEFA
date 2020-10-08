package mish.mish.assefa.com.hisab.framework.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import mish.mish.assefa.com.hisab.R

import mish.mish.assefa.com.hisab.framework.util.logD
import mish.mish.assefa.com.hisab.framework.util.logE

open class BaseActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        d("uI created")
        logE(this,"Error Message")
        //setContentView(R.layout.activity_main)
    }
    override fun onStart() {
        super.onStart()
        //setContentView(R.layout.activity_main)
        d("onStart")
        //Log.d("ActivityCallback","OnStart()")
    }

    override fun onResume() {
        super.onResume()
        d("onResume")
        //Log.d("ActivityCallback","OnResume()")
    }

    override fun onPause() {
        super.onPause()
        d("onPause")
        //Log.d("ActivityCallback","OnPause()")
    }

    override fun onStop() {
        super.onStop()
        d("onStop()")
        //Log.d("ActivityCallback","OnStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        d("onDestroy")
      //  Log.d("ActivityCallback","OnDestroy()")
    }
    protected fun d(message:String){
        logD(this,message)
       // val tag=this::class.simpleName
        //Log.d(tag,message)
    }


}