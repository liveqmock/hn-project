package hn.travel.persist.utils;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import magick.CompositeOperator;
import magick.CompressionType;
import magick.DrawInfo;
import magick.ImageInfo;
import magick.MagickApiException;
import magick.MagickException;
import magick.MagickImage;
import magick.PixelPacket;
import magick.PreviewType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageTools {
	
	private static Logger logger = LoggerFactory.getLogger(ImageTools.class);

	static {
		// 不能漏掉这个，不然jmagick.jar的路径找不到
		System.setProperty("jmagick.systemclassloader", "no");
	}

	/**
	 * 压缩图片
	 * 
	 * @param filePath
	 *            源文件路径
	 * @param toPath
	 *            缩略图路径
	 */
	public static void createThumbnail(String filePath, String toPath)
			throws MagickException {
		ImageInfo info = null;
		MagickImage image = null;
		Dimension imageDim = null;
		MagickImage scaled = null;
		try {
			info = new ImageInfo(filePath);
			image = new MagickImage(info);
			imageDim = image.getDimension();
			int wideth = imageDim.width;
			int height = imageDim.height;
			if (wideth > height) {
				height = 660 * height / wideth;
				wideth = 660;
			}
			scaled = image.scaleImage(wideth, height);// 小图片文件的大小.
			scaled.setFileName(toPath);
			scaled.writeImage(info);
		} finally {
			if (scaled != null) {
				scaled.destroyImages();
			}
		}
	}

	/**
	 * 水印(图片logo)
	 * 
	 * @param filePath
	 *            源文件路径
	 * @param toImg
	 *            修改图路径
	 * @param logoPath
	 *            logo图路径
	 * @throws MagickException
	 */
	public static void initLogoImg(String filePath, String toImg,
			String logoPath) throws MagickException {
		ImageInfo info = new ImageInfo();
		MagickImage fImage = null;
		MagickImage sImage = null;
		MagickImage fLogo = null;
		MagickImage sLogo = null;
		Dimension imageDim = null;
		Dimension logoDim = null;
		try {
			fImage = new MagickImage(new ImageInfo(filePath));
			imageDim = fImage.getDimension();
			int width = imageDim.width;
			int height = imageDim.height;
			if (width > 660) {
				height = 660 * height / width;
				width = 660;
			}
			sImage = fImage.scaleImage(width, height);

			fLogo = new MagickImage(new ImageInfo(logoPath));
			logoDim = fLogo.getDimension();
			int lw = width / 8;
			int lh = logoDim.height * lw / logoDim.width;
			sLogo = fLogo.scaleImage(lw, lh);

			sImage.compositeImage(CompositeOperator.AtopCompositeOp, sLogo,
					width - (lw + lh / 10), height - (lh + lh / 10));
			sImage.setFileName(toImg);
			sImage.writeImage(info);
		} finally {
			if (sImage != null) {
				sImage.destroyImages();
			}
		}
	}
	

	/**
	 * 水印(文字)
	 * 
	 * @param filePath
	 *            源文件路径
	 * @param toImg
	 *            修改图路径
	 * @param text
	 *            名字(文字内容自己随意)
	 * @throws MagickException
	 */
	public static void initTextToImg(String filePath, String toImg, String text)
			throws MagickException {
		ImageInfo info = new ImageInfo(filePath);
		if (filePath.toUpperCase().endsWith("JPG")
				|| filePath.toUpperCase().endsWith("JPEG")) {
			info.setCompression(CompressionType.JPEGCompression); // 压缩类别为JPEG格式
			info.setPreviewType(PreviewType.JPEGPreview); // 预览格式为JPEG格式
			info.setQuality(95);
		}
		MagickImage aImage = new MagickImage(info);
		Dimension imageDim = aImage.getDimension();
		int wideth = imageDim.width;
		int height = imageDim.height;
		if (wideth > 660) {
			height = 660 * height / wideth;
			wideth = 660;
		}
		int a = 0;
		int b = 0;
		String[] as = text.split("");
		for (String string : as) {
			if (string.matches("[\u4E00-\u9FA5]")) {
				a++;
			}
			if (string.matches("[a-zA-Z0-9]")) {
				b++;
			}
		}
		int tl = a * 12 + b * 6 + 300;
		MagickImage scaled = aImage.scaleImage(wideth, height);
		if (wideth > tl && height > 5) {
			DrawInfo aInfo = new DrawInfo(info);
			aInfo.setFill(PixelPacket.queryColorDatabase("white"));
			aInfo.setUnderColor(new PixelPacket(0, 0, 0, 100));
			aInfo.setPointsize(12);
			// 解决中文乱码问题,自己可以去随意定义个自己喜欢字体，我在这用的微软雅黑
			String fontPath = "C:/WINDOWS/Fonts/MSYH.TTF";
			// String fontPath = "/usr/maindata/MSYH.TTF";
			aInfo.setFont(fontPath);
			aInfo.setTextAntialias(true);
			aInfo.setOpacity(0);
			aInfo.setText("　" + text + "于　"
					+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())
					+ "　上传于XXXX网，版权归作者所有！");
			aInfo.setGeometry("+" + (wideth - tl) + "+" + (height - 5));
			scaled.annotateImage(aInfo);
		}
		scaled.setFileName(toImg);
		scaled.writeImage(info);
		scaled.destroyImages();
	}

	/**
	 * 切图
	 * 
	 * @param imgPath
	 *            源图路径
	 * @param toPath
	 *            修改图路径
	 * @param w
	 * @param h
	 * @param x
	 * @param y
	 * @throws MagickException
	 */
	public static void cutImg(String imgPath, String toPath, int w, int h,
			int x, int y) {
		ImageInfo infoS = null;
		MagickImage image = null;
		MagickImage cropped = null;
		Rectangle rect = null;
		try {
			infoS = new ImageInfo(imgPath);
			image = new MagickImage(infoS);
			rect = new Rectangle(x, y, w, h);
			cropped = image.cropImage(rect);
			cropped.setFileName(toPath);
			cropped.writeImage(infoS);

		} catch (MagickException e) {
			logger.error("图片剪切时出现{}异常:{}", "MagickApiException", e);
		} finally {
			if (cropped != null) {
				cropped.destroyImages();
			}
		}
	}
	
	/**
	 * 默认从中间剪裁图片
	 * @param imgPath
	 * @param toPath
	 * @param w
	 * @param h
	 */
	public static void cutImg(String imgPath, String toPath, int w, int h) {
		ImageInfo infoS = null;
		MagickImage image = null;
		MagickImage cropped = null;
		Rectangle rect = null;
		int x, y;
		try {
			infoS = new ImageInfo(imgPath);
			image = new MagickImage(infoS);
			Dimension d = image.getDimension();
			if(d.width <= w)
				x = 0;
			else
				x = (d.width/2)-(w/2);
			if(d.height <= h)
				y = 0;
			else 
				y = (d.height/2)-(h/2);
			rect = new Rectangle(x, y, w, h);
			cropped = image.cropImage(rect);
			cropped.setFileName(toPath);
			cropped.writeImage(infoS);
		} catch (MagickException e) {
			logger.error("图片剪切时出现{}异常:{}", "MagickApiException", e);
		} finally {
			if (cropped != null) {
				cropped.destroyImages();
			}
		}
	}
	
	public static void _cutImg(String imgPath, String toPath, String size) {
		String[] wh = size.split("x");
		int w = Integer.parseInt(wh[0]);
		int h = Integer.parseInt(wh[1]);
		cutImg(imgPath, toPath, w, h);
	}
	
	public static void main(String[] args) {
		
		resizeAndCutImg("C:\\Users\\Administrator\\Desktop\\120x170.jpg", "C:\\Users\\Administrator\\Desktop\\process3.jpg", "100x140");
//		double d = 2.5d;
//		int i = Integer.parseInt(String.valueOf(d));
//		System.out.println(i);
	}

	public static void resize(String imgPath, String toPath, int w, int h) {
		MagickImage scaled = null;
		try {
			ImageInfo info = new ImageInfo(imgPath);
			MagickImage image = new MagickImage(new ImageInfo(imgPath));
			scaled = image.scaleImage(w, h);// 小图片文件的大小.
			scaled.setFileName(toPath);
			scaled.writeImage(info);
		} catch (MagickApiException ex) {
			logger.error("图片缩放时出现{}异常{}", "MagickApiException", ex);
		} catch (MagickException ex) {
			logger.error("图片缩放时出现{}异常{}", "MagickException", ex);
		} finally {
			if (scaled != null) {
				scaled.destroyImages();
			}
		}
	}
	
	/**
	 * 先根据宽度缩放，再cut高度
	 * @param imgPath
	 * @param toPath
	 * @param size
	 */
	public static void resizeAndCutImg(String imgPath, String toPath, String size) {
		String[] wh = size.split("x");
		int w = Integer.parseInt(wh[0]);
		int h = Integer.parseInt(wh[1]);
		
		ImageInfo infoS = null;
		MagickImage image = null;
		MagickImage cropped = null;
		Rectangle rect = null;
		int x, y, sw, sh;
		try {
			infoS = new ImageInfo(imgPath);
			image = new MagickImage(infoS);
			Dimension d = image.getDimension();
			if(d.getWidth() > d.getHeight()) {
				sw = new BigDecimal(String.valueOf(d.getWidth())).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				sh = new BigDecimal(String.valueOf(d.getHeight())).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				cropped = image;
			} else {
				double _h = (d.getHeight()*w)/d.getWidth();
				cropped = image.scaleImage(w, new BigDecimal(String.valueOf(_h)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
				Dimension _d = cropped.getDimension();
				sw = new BigDecimal(String.valueOf(_d.getWidth())).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				sh = new BigDecimal(String.valueOf(_d.getHeight())).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
			}
			if(sw <= w)
				x = 0;
			else
				x = (sw/2)-(w/2);
			if(sh <= h)
				y = 0;
			else 
				y = (sh/2)-(h/2);
			rect = new Rectangle(x, y, w, h);
			cropped = cropped.cropImage(rect);
			cropped.setFileName(toPath);
			cropped.writeImage(infoS);
		} catch (MagickException e) {
			logger.error("图片剪切时出现{}异常:{}", "MagickApiException", e);
		} finally {
			if (cropped != null) {
				cropped.destroyImages();
			}
		}
	}
	
	/**
	 * 
	 * @param imgPath
	 * @param toPath
	 * @param size eg:100x200
	 */
	public static void resize(String imgPath, String toPath, String size) {
		String[] xy = size.split("x");
		int x = Integer.parseInt(xy[0]);
		int y = Integer.parseInt(xy[1]);
		resize(imgPath, toPath, x, y);
	}
	
	public static void resizeWithScale(String imgPath, String toPath, String size) {
		String[] wh = size.split("x");
		int w = Integer.parseInt(wh[0]);
		int h = Integer.parseInt(wh[1]);
		
		ImageInfo infoS = null;
		MagickImage image = null;
		MagickImage cropped = null;
		Rectangle rect = null;
		int x, y, sw, sh;
		try {
			infoS = new ImageInfo(imgPath);
			image = new MagickImage(infoS);
			Dimension d = image.getDimension();
			
			double _h = (d.getHeight()*w)/d.getWidth();
			cropped = image.scaleImage(w, new BigDecimal(String.valueOf(_h)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
			Dimension _d = cropped.getDimension();
			sw = new BigDecimal(String.valueOf(_d.getWidth())).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
			sh = new BigDecimal(String.valueOf(_d.getHeight())).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
			
			if(sw <= w)
				x = 0;
			else
				x = (sw/2)-(w/2);
			if(sh <= h)
				y = 0;
			else 
				y = (sh/2)-(h/2);
			rect = new Rectangle(x, y, w, h);
			cropped = cropped.cropImage(rect);
			cropped.setFileName(toPath);
			cropped.writeImage(infoS);
		} catch (MagickException e) {
			logger.error("图片剪切时出现{}异常:{}", "MagickApiException", e);
		} finally {
			if (cropped != null) {
				cropped.destroyImages();
			}
		}
	}
	
}
