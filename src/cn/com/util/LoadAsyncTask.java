package cn.com.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;


	  /**  
     * 生成该类的对象，并调用execute方法之后  
     * 首先执行的是onProExecute方法  
     * 其次执行doInBackgroup方法  
     */ 
    public class LoadAsyncTask extends AsyncTask<Integer, Integer, String> {

        private ProgressDialog mProgressDialog;  
        protected void onPreExecute() {
            Log.v("TestActivity", "开始执行异步线程");
            super.onPreExecute();  
           
            
        }
        
        /**  
         * 这里的Integer参数对应AsyncTask中的第一个参数   
         * 这里的String返回值对应AsyncTask的第三个参数  
         * 该方法并不运行在UI线程当中，主要用于异步操作，所有在该方法中不能对UI当中的空间进行设置和修改  
         */ 
        
        protected String doInBackground(Integer... params) {
			return null;
            // 加载网络数据
        }
        
        
        protected void onPostExecute(String result) {
            Log.v("TestActivity", "结束执行异步线程");
            // 更新UI
        }

    }

