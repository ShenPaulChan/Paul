/**
 */
package com.paul.demo.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * @author Paul
 * 
 *         2015-5-13
 */
public class FileUtils {

	public static final String POINT = ".";
	private static Logger log = Logger.getLogger(FileUtils.class);

	/**
	 * 获取文件的文件名（"h:/test.jpg" ====>test）
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		String fileName = newNameByDate();
		try {
			File file = new File(filePath);
			String fileAllName = file.getName();
			fileName = fileAllName.substring(0, fileAllName.lastIndexOf(POINT));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public static boolean exists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	/**
	 * 获取文件的文件全名（"h:/test.jpg" ====>test.jpg）
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileAllName(String filePath) {
		File file = new File(filePath);
		String fileAllName = file.getName();
		return fileAllName;
	}

	/**
	 * 获取文件的扩展名（"h:/testimage.jpg" ====>jpg）
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileExtensionName(String filePath) {
		String extensionName = filePath
				.substring(filePath.lastIndexOf(POINT) + 1);
		return extensionName;
	}

	/**
	 * 获取文件所在的文件夹
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileFolder(String filePath) {
		File file = new File(filePath);
		return file.getParent();
	}

	/**
	 * 获取文件全路径
	 * 
	 * @param fileFolder
	 * @param fileName
	 * @param fileExName
	 * @return
	 */
	public static String getFilePath(String fileFolder, String fileName,
			String fileExName) {
		return fileFolder + File.separator + fileName + POINT + fileExName;
	}

	/**
	 * 获取文件全名
	 * 
	 * @param fileName
	 * @param fileExName
	 * @return
	 */
	public static String getFileAllName(String fileName, String fileExName) {
		return fileName + POINT + fileExName;
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 */
	public static boolean deleteFile(String filePath) {
		boolean success = false;
		try {
			File file = new File(filePath);
			if (file.exists()) {
				success = file.delete();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return success;
	}

	public static void main(String[] args) {
		System.out.println(download("http://7xp2os.com1.z0.glb.clouddn.com/1449813306399.jpg", "123456", "H:/", true));
	}

	/**
	 * 保存文件
	 * 
	 * @param fileInputStream
	 * @param file
	 * @throws Exception
	 */
	public static void copyInputStreamToFile(InputStream fileInputStream,
			File file) throws Exception {
		OutputStream outpuStream = new FileOutputStream(file);
		int read = 0;
		byte[] bytes = new byte[1024];
		while ((read = fileInputStream.read(bytes)) != -1) {
			outpuStream.write(bytes, 0, read);
		}
		outpuStream.flush();
		outpuStream.close();
	}

	public static String download(String urlString, String filename,
			String savePath, boolean isExName) {
		try {
			if(StringUtils.isEmpty(urlString)){
				return "";
			}
			String exName = "";
			if(isExName){
				exName = getFileExtensionName(urlString);
				if(exName != null && !exName.isEmpty()){
					filename = filename + POINT + exName;
				}
			}else{
				filename = newNameByDate();
			}
			// 构造URL
			URL url = new URL(urlString);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			InputStream is = con.getInputStream();

			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf = new File(savePath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			OutputStream os = new FileOutputStream(sf.getPath() + "/" + filename);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
			return sf.getPath() + "/" + filename;
		} catch (Exception e) {
			log.info(null, e);
		}
		return null;
	}

	public static String newNameByDate() {
		return DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSSS)
				+ new Random().nextInt(10000);
	}

	
}
