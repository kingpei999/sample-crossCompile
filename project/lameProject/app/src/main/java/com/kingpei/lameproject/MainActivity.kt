package com.kingpei.lameproject

import android.Manifest
import android.content.pm.PackageManager
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import java.io.File

class MainActivity : AppCompatActivity() {

    companion object {
        private class MyHandler : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
            }
        }

        private var handler = MyHandler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), 111)
        };
    }

    override fun onDestroy() {
        super.onDestroy()
        Mp3Encoder.destroy()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (i in 0..(permissions.size -1)){
            if(Manifest.permission.WRITE_EXTERNAL_STORAGE == permissions[i] && grantResults[i] == PackageManager.PERMISSION_GRANTED){

               var file = Environment.getExternalStorageDirectory()
                var fileList = file.listFiles()
                for (i in 0..(fileList.size - 1)){
                    Log.d("file", "file:" + fileList[i].absolutePath )
                }

                Thread{
                        var pcmPath = Environment.getExternalStorageDirectory().absolutePath + File.separator + "16k.pcm"
                        var mp3Path = Environment.getExternalStorageDirectory().absolutePath + File.separator + "16k.mp3"

                        var mp3File = File(mp3Path)
                        if(!mp3File.exists()){
                            mp3File.createNewFile()
                        }

                        var ret = Mp3Encoder.init(pcmPath, 1,256000, 16000, mp3Path)
                        handler.post{ Toast.makeText(this@MainActivity, "init:" + (ret != -1), Toast.LENGTH_LONG).show() }
                        Mp3Encoder.encode()
                        handler.post{ Toast.makeText(this@MainActivity, "finish", Toast.LENGTH_LONG).show() }
                }.run()
            }
        }
    }

}
