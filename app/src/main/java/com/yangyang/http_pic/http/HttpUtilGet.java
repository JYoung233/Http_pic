package com.yangyang.http_pic.http;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by asus on 2016/2/28.
 * 用get方式获取服务器端的图片
 */
public class HttpUtilGet {
    private static String path="http://localhost:8080/myhttp/8.jpg";
    private static URL url;
    public HttpUtilGet(){
        // TODO Auto-generated constructor stub
    }
    static{
        try {
            url=new URL(path);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void savetodisk(){//将输入流转换成输出流写入到本地磁盘？inputstream不能直接转换成outputstream吗？
        InputStream is=getInputStream();
        byte[] data=new byte[1024];
        int len=0;
        FileOutputStream fos=null;
        if(is!=null){
            try {
                fos=new FileOutputStream("D://1.png");
                try {
                    while((len=is.read(data))!=-1){
                        fos.write(data,0,len);
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                if(is!=null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if(fos!=null){
                    try {
                        fos.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }



    }
    public static InputStream getInputStream(){
        InputStream is=null;
        HttpURLConnection huc=null;
        if(url!=null){
            try {
                huc=(HttpURLConnection)url.openConnection();
                huc.setConnectTimeout(3000);
                huc.setRequestMethod("GET");
                huc.setDoInput(true);
                if(huc.getResponseCode()==200){
                    is=huc.getInputStream();
                }
                else{
                    System.out.print("Wrong!");
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            System.out.print("url");
        }

        return is;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        savetodisk();
    }
}
