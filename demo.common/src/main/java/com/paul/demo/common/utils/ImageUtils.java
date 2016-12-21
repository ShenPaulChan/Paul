/**
 * 
 */
package com.paul.demo.common.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.log4j.Logger;

/**
 * @author Paul
 * 
 *         2015-5-13
 */
public class ImageUtils {

	public static Logger log = Logger.getLogger(ImageUtils.class);

	public static final String POINT = ".";

	/**
	 * 创建新的图像
	 * 
	 * @param filePath
	 * @param toFilePath
	 * @param width
	 * @param height
	 * @return
	 */
	public static String resize(String filePath, String toFilePath,
			String toFileName, int width, int height) {
		try {
			BufferedImage srcImg = ImageIO.read(new File(filePath));
			String extensionName = getFileExtensionName(filePath);
			BufferedImage img = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			img.getGraphics().drawImage(srcImg, 0, 0, width, height, null);
			File file = new File(toFilePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			String mToFile = toFilePath + File.separator + toFileName + POINT
					+ extensionName;
			log.info("new image path:" + mToFile);
			ImageIO.write(img, extensionName, new File(mToFile));
			return mToFile;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("creatImage error:" + e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 创建新的图像
	 * 
	 * @param filePath
	 * @param toFilePath
	 * @param toFileName
	 * @param width
	 * @return
	 */
	public static String resize(String filePath, String toFilePath,
			String toFileName, int width) {

		try {
			int imageWidth = 0;
			int imageHeight = 0;
			BufferedImage source = ImageIO.read(new FileInputStream(filePath));
			imageWidth = source.getWidth();
			imageHeight = source.getHeight();
			double proportion = Double.valueOf(width)
					/ Double.valueOf(imageWidth);
			int height = (int) (imageHeight * proportion);
			return resize(filePath, toFilePath, toFileName, width, height);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("creatImage auto error:" + e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 获取文件的扩展名
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileExtensionName(String file) {
		String extensionName = file.substring(file.lastIndexOf(".") + 1);
		return extensionName;
	}

	/**
	 * 获取图片的高宽 返回结果：[width, hight]
	 * 
	 * @param filePath
	 * @return
	 */
	public static int[] getImageWidthAndHight(String filePath) {
		int width = 1;
		int hight = 1;
		try {
			BufferedImage source = ImageIO.read(new FileInputStream(filePath));
			width = source.getWidth();
			hight = source.getHeight();
		} catch (Exception e) {
			// TODO: handle exception
			log.info("getImageWidthAndHight>>>>>>>>", e);
		}
		return new int[] { width, hight };
	}

	/**
	 * 
	 * 图片裁剪通用接口
	 */

	@SuppressWarnings("rawtypes")
	public static void cutImage(String src, String dest, int x, int y, int w,
			int h) throws IOException {
		Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, "jpg", new File(dest));
	}

	/**
	 * 
	 * 图片裁剪通用接口
	 * 
	 * @throws IOException
	 */

	public static void cutImage(String src, String dest, int x, int y, int w,
			int h, int sW) throws IOException {
		int[] wh = getImageWidthAndHight(src);
		double ratio = Double.valueOf(wh[0]) / Double.valueOf(sW);
		x = (int) (ratio * x);
		y = (int) (ratio * y);
		w = (int) (ratio * w);
		h = (int) (ratio * h);
		cutImage(src, dest, x, y, w, h);
	}

	/**
	 * 图片裁剪 根据比列，居中裁剪图片
	 * 
	 * @param src
	 *            图片地址
	 * @param dest
	 *            剪切图片地址
	 * @param ratio
	 *            剪切后的比例（w/h）
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static void cutImage(String src, String dest, double ratio)
			throws IOException {
		int[] wh = getImageWidthAndHight(src);
		CutOption option = getCutCenterOption(wh[0], wh[1], ratio);
		Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(option.x, option.y, option.w, option.h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, "jpg", new File(dest));
	}

	/**
	 * 获取居中比例裁剪的参数
	 * 
	 * @param imageW
	 * @param imageH
	 * @return
	 */
	private static CutOption getCutCenterOption(int imageW, int imageH,
			double ratio) {
		CutOption option = new CutOption();
		double mRatio = (double) imageW / (double) imageH;
		if (ratio > mRatio) {
			option.w = imageW;
			option.h = (int) (imageW / ratio);
			option.x = 0;
			option.y = (imageH - option.h) / 2;
		} else {
			option.h = imageH;
			option.w = (int) (imageH * ratio);
			option.y = 0;
			option.x = (imageW - option.w) / 2;
		}
		System.out.println(option.x + ":" + option.y + ":" + option.w + ":"
				+ option.h);
		return option;
	}

	public static class CutOption {
		int x;
		int y;
		int w;
		int h;
	}

	/**
	 * 彩色转为黑白
	 * 
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 */
	public final static void gray(String srcImageFile, String destImageFile) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile));
			ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			ColorConvertOp op = new ColorConvertOp(cs, null);
			src = op.filter(src, null);
			ImageIO.write(src, "JPEG", new File(destImageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给图片添加文字水印
	 * 
	 * @param pressText
	 *            水印文字
	 * @param srcImageFile
	 *            源图像地址
	 * @param destImageFile
	 *            目标图像地址
	 * @param fontName
	 *            水印的字体名称
	 * @param fontStyle
	 *            水印的字体样式
	 * @param color
	 *            水印的字体颜色
	 * @param fontSize
	 *            水印的字体大小
	 * @param x
	 *            修正值
	 * @param y
	 *            修正值
	 * @param alpha
	 *            透明度：alpha 必须是范围 [0.0, 1.0] 之内（包含边界值）的一个浮点数字
	 */
	public final static void pressText(String pressText, String srcImageFile,
			String destImageFile, String fontName, int fontStyle, Color color,
			int fontSize, int x, int y, float alpha) {
		try {
			File img = new File(srcImageFile);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			// 在指定坐标绘制水印文字
			g.drawString(pressText, (width - (getLength(pressText) * fontSize))
					/ 2 + x, (height - fontSize) / 2 + y);
			g.dispose();
			ImageIO.write((BufferedImage) image, "JPEG",
					new File(destImageFile));// 输出到文件流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算text的长度（一个中文算两个字符）
	 * 
	 * @param text
	 * @return
	 */
	public final static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}

	/**
	 * 生成一个文件的文件名
	 * 
	 * @return
	 */
	public static String createFileName() {
		Random ran = new Random();
		return DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSSS)
				+ ran.nextInt(1000);
	}

	/**
	 * 
	 * @param image
	 * @param cornerRadius
	 * @return
	 */
	public static BufferedImage makeRoundedCorner(BufferedImage image,
			int cornerRadius) {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage output = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = output.createGraphics();
		// This is what we want, but it only does hard-clipping, i.e. aliasing
		// g2.setClip(new RoundRectangle2D ...)

		// so instead fake soft-clipping by first drawing the desired clip shape
		// in fully opaque white with antialiasing enabled...
		g2.setComposite(AlphaComposite.Src);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius,
				cornerRadius));

		// ... then compositing the image on top,
		// using the white shape from above as alpha source
		g2.setComposite(AlphaComposite.SrcAtop);
		g2.drawImage(image, 0, 0, null);

		g2.dispose();

		return output;
	}

	public static void main(String[] args) {
		String path = "H:/test.jpg";
		String path1 = "H:/testr.png";
		try {
			BufferedImage bufferedImage = ImageIO.read(new File(path));
			System.out.println(bufferedImage.getWidth()/2);
			bufferedImage = makeRoundedCorner(bufferedImage, bufferedImage.getWidth());
			ImageIO.write(bufferedImage, "png", new File(path1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// String toPath = "D:/test/testImage";
		// String toName = "test1";
		// String newPath = createImage(path, toPath, toName, 100);
		// int[] wh = getImageWidthAndHight(path);
		// System.out.println("" + wh[0] + wh[1]);
		/*
		 * try { cutImage(path, path1, 0, 0, 100, 1944); } catch (IOException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		// gray(path, path1);
		// pressText("我是水印文字", path, path1, "宋体", Font.BOLD, Color.white, 80,
		// 0,0, 0.5f);// 测试OK
		/*
		 * try { cutImage(path, path1, 1D/1D); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		/*try {
			cutImage(path, path1, 250, 50, 200, 150, 500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
