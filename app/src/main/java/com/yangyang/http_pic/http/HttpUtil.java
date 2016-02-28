package com.yangyang.http_pic.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 2016/2/28.
 *
 */
public class HttpUtil {
    private static String path="";
    private static URL url;

    public HttpUtil() {
    }
    static{
        try {
            url=new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    public static String SendMessage(Map<String,String> params,String encode){
        StringBuffer stringBuffer=new StringBuffer();
        if(params!=null||!params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                try {
                    stringBuffer.append(entry.getKey())
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), encode));
                    stringBuffer.append("&");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        try {
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setConnectTimeout(3000);
            huc.setRequestMethod("POST");
            huc.setDoInput(true);
            huc.setDoOutput(true);
            //设置连接参数
            byte[] mydata = stringBuffer.toString().getBytes();

            huc.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            huc.setRequestProperty("Content-Length",
                    String.valueOf(mydata.length));

            OutputStream os=huc.getOutputStream();
            os.write(mydata);

            int responsecode=huc.getResponseCode();
            if(responsecode==200){
                return changeInputStream(huc.getInputStream(),encode);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";


    }

    private static String changeInputStream(InputStream inputStream,
                                            String encode) {
        // TODO Auto-generated method stub
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        byte[] data=new byte[1024];
        int len=0;
        String result="";
        if(inputStream!=null){
            try {
                while((len=inputStream.read(data))!=-1){
                    os.write(data, 0, len);
                }
                result=new String(os.toByteArray(),encode);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return result;
    }
    public static void main(String[] args) {//这里是测试函数
        // TODO Auto-generated method stub
        Map<String, String> params = new HashMap<String,String>();
        params.put("username", "admin");
        params.put("psw", "123");
        String result=SendMessage(params, "utf-8");
        System.out.print(result);
    }

}
