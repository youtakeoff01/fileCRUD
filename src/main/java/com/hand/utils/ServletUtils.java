package com.hand.utils;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletUtils {
    
    public static HttpSession buildAppObject()
    {
        HttpSession session = getSession();
        if (session == null) return null;
        return session;
    }

    public static HttpSession getSession() 
    { 
        HttpSession session = null; 
        try { 
            session = getRequest().getSession(); 
        } catch (Exception e) {} 
        return session; 
    } 
    
    public static HttpServletRequest getRequest() 
    { 
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null)return null;
          
        return attrs.getRequest(); 
    }
    
    public static String getTempFilesPath() {
        HttpSession session = getSession();
        if (session == null)
            return "";
        ServletContext sc = session.getServletContext();
        String path = sc.getInitParameter("TEMPFILEPATH");
        //Means the user defined an absolute path
        if (isAbsolutePath(path)) {
            checkDir(path);
            return path;
        } else {
        //Means the user defined a relative path
            path = getResourceFullPath(path);
            checkDir(path);
            return path;
        }        
    } 
    
    private static boolean isAbsolutePath(String path) {
        return (path.startsWith("/") || path.indexOf(":") != -1);
    }
    
    private static void checkDir(String filename){
        boolean exists = (new File(filename)).exists();
        if (!exists) {
            try {
                (new File(filename)).mkdirs();
            } catch (Exception ex) {}
        }
    }
    
    public static String getResourceFullPath(String resourceRelativePathy) {
        HttpSession session = getSession();
        ServletContext sc = session.getServletContext();
        return sc.getRealPath(resourceRelativePathy);
    }
    
}
