package cn.com.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;


	  /**  
     * ���ɸ���Ķ��󣬲�����execute����֮��  
     * ����ִ�е���onProExecute����  
     * ���ִ��doInBackgroup����  
     */ 
    public class LoadAsyncTask extends AsyncTask<Integer, Integer, String> {

        private ProgressDialog mProgressDialog;  
        protected void onPreExecute() {
            Log.v("TestActivity", "��ʼִ���첽�߳�");
            super.onPreExecute();  
           
            
        }
        
        /**  
         * �����Integer������ӦAsyncTask�еĵ�һ������   
         * �����String����ֵ��ӦAsyncTask�ĵ���������  
         * �÷�������������UI�̵߳��У���Ҫ�����첽�����������ڸ÷����в��ܶ�UI���еĿռ�������ú��޸�  
         */ 
        
        protected String doInBackground(Integer... params) {
			return null;
            // ������������
        }
        
        
        protected void onPostExecute(String result) {
            Log.v("TestActivity", "����ִ���첽�߳�");
            // ����UI
        }

    }

