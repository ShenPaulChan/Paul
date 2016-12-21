/**
 * Copyright reserved by IZHUO.NET
 */
package com.paul.demo.common.utils;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;


/**
 * @author Paul
 *
 * 2015-11-30
 */
public class LoadFont{
	
	private static Logger log = Logger.getLogger(LoadFont.class);
	
    public static Font loadFont(String fontFileName, float fontSize)  //第一个参数是外部字体名，第二个是字体大小
    {
        try
        {
            File file = new File(fontFileName);
            FileInputStream aixing = new FileInputStream(file);
            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
            Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
            aixing.close();
            return dynamicFontPt;
        }
        catch(Exception e)//异常处理
        {
            e.printStackTrace();
            log.info("===", e);
            return new java.awt.Font("宋体", Font.PLAIN, 40);
        }
    }
    public static java.awt.Font msyhlFont(int fontSize){
        String root= "/data/www/yodooapi/font";//项目根目录路径
        Font font = LoadFont.loadFont(root+"/msyhl.ttf", fontSize);//调用
        return font;//返回字体
    }
    public static java.awt.Font Font2(){
        String root=System.getProperty("user.dir");//项目根目录路径
        Font font = LoadFont.loadFont(root+"/data/XXXX.ttf", 18f);
        return font;//返回字体
    }
}