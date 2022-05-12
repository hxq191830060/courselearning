package cn.seecoder.courselearning.util;

import java.util.HashMap;
import java.util.Map;

public class FileConstant {
    public static final String PPT="ppt";
    public static final String WORD="doc";
    public static final String TXT="txt";
    public static final String PDF="pdf";

    public static final Map<String,String>map=new HashMap<>();
    static {
        map.put(PPT,"application/vnd.ms-powerpoint");
        map.put(WORD,"application/msword");
        map.put(TXT,"text/plain");
        map.put(PDF,"application/pdf");
    }

    //判断文件类型是否支持
    public static boolean FileTypeIsSupported(String type){
        return type.equals(PPT)||type.equals(WORD)||type.equals(TXT)||type.equals(PDF);
    }

    //返回文件类型对应的ContentType
    public static String getContentType(String type){
        return map.get(type);
    }
}
