package mish.mish.assefa.com.hisab.framework.util

import android.util.Log

private const val TAG="HISAB-TAG-DEBUG"
fun logD(source:Any,message:String){
    //val name=source::class.simpleName
    //val output="$name:$message"
    log(TAG,message,false)
}
fun logE(source:Any,message:String){
    //val name=source::class.simpleName
    //val output="$name:$message"
    log(TAG,message,true)
}
fun log(source:Any,message:String,error:Boolean){
    val name=source::class.simpleName

    val emoji=if (error) "⚠️" else "✅️"
    val output="$emoji - $name:$message"
    if (error){
        Log.e(TAG,message)
    }
    else{
    Log.d(TAG,output)}
}
fun play(){

}