package hn.travel.portal.web.captcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.service.CaptchaServiceException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/*
 * 联通体验俱乐部平台
 * @author
 * @version 1.0,2011/3/18
 * Copyright (c) 2011.
 */

public class ImageCaptchaServlet extends HttpServlet {
    //随机生成的字符集
    private String keyWord;

    //字体大小
    private int fontSize;

    //文字显示的个数
    private int size;

    private int picWidth;
    private int picHeight;
    private int wordWidth;
    private int wordHeight;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        String s = servletConfig.getInitParameter("keyWord");
        if (s != null && s.length() > 0) {
            keyWord = s;
        }
        String fontSize = servletConfig.getInitParameter("fontSize");
        if (fontSize != null && fontSize.matches("\\d+")) {
            this.fontSize = Integer.valueOf(fontSize);
        }
        String size = servletConfig.getInitParameter("size");
        if (size != null && size.matches("\\d+")) {
            this.size = Integer.valueOf(size);
        }
        String picWidth = servletConfig.getInitParameter("picWidth");
        if (picWidth != null && picWidth.matches("\\d+")) {
            this.picWidth = Integer.valueOf(picWidth);
        }
        String picHeight = servletConfig.getInitParameter("picHeight");
        if (picHeight != null && picHeight.matches("\\d+")) {
            this.picHeight = Integer.valueOf(picHeight);
        }
        String wordWidth = servletConfig.getInitParameter("wordWidth");
        if (wordWidth != null && wordWidth.matches("\\d+")) {
            this.wordWidth = Integer.valueOf(wordWidth);
        }
        String wordHeight = servletConfig.getInitParameter("wordHeight");
        if (wordHeight != null && wordHeight.matches("\\d+")) {
            this.wordHeight = Integer.valueOf(wordHeight);
        }

        ImageCaptchaEngine.init(keyWord, this.fontSize, this.size,
                this.picWidth, this.picHeight, this.wordWidth, this.wordHeight);
    }

    protected void service(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse) throws ServletException, IOException {

        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            String captchaId = httpServletRequest.getSession().getId();
            BufferedImage challenge = ImageCaptchaEngine.getInstance().
                    getImageChallengeForID(captchaId, httpServletRequest.getLocale());
            JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
            jpegEncoder.encode(challenge);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(
                    HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (CaptchaServiceException e) {
            httpServletResponse.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();

    }
}
