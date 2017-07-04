package com.open.androidtvwidget.utils;






import android.text.TextUtils;
import android.util.Log;

/**
 * Log工具，类似android.util.Log。
 * tag自动产生，格式: customTagPrefix:className.methodName(L:lineNumber),
 * customTagPrefix为空时只输出：className.methodName(L:lineNumber)。
 * zhangzhonglei 20160329
 */
public class LogUtils {

    public static String customTagPrefix = "";

    private LogUtils() {/* cannot be instantiated */
    }

    public static boolean allowD = true;
    public static boolean allowE = true;
    public static boolean allowI = false;
    public static boolean allowV = true;
    public static boolean allowW = true;
    public static boolean allowWtf = true;

    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L行:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    
    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }

    
    public static void d(String tag,String content) {
        if (!allowD) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tagFun = generateTag(caller);

        Log.d(tag, tagFun+content); 
    }

   

    public static void e(String tag,String content) {
        if (!allowE) return;
        StackTraceElement caller =getCallerStackTraceElement();
        

        String tagFun = generateTag(caller);

        Log.e(tag, tagFun+content); 
    }

    
    public static void i(String tag,String content) {
        if (!allowI) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tagFun = generateTag(caller);
        Log.i(tag, tagFun+content); 
    }

   

    public static void v(String tag,String content) {
        if (!allowV) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tagFun = generateTag(caller);
        Log.i(tag, tagFun+content); 
    }

   

    public static void w(String tag,String content) {
        if (!allowW) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tagFun = generateTag(caller);
        Log.i(tag, tagFun+content); 
    }

    


    public static void wtf(String tag,String content) {
        if (!allowWtf) return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tagFun = generateTag(caller);
        Log.i(tag, tagFun+content); 
    }

   
   
}