package hn.travel.portal.web.captcha;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.GradientBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/*
* 联通体验俱乐部平台
* @author Sean Lai
* @version 1.0,2011/3/17
* Copyright (c) 2011.
*/
public class ImageCaptchaEngine extends ListImageCaptchaEngine {
    private static final Log logger = LogFactory.getLog(ImageCaptchaEngine.class);

    //随机生成的字符集
    private static String keyWord = "0123456789";

    //字体大小 asd
    private static int fontSize = 9;

    //文字显示的个数
    private static int size = 4;

    private static int picWidth = 73;
    private static int picHeight = 25;
    private static int wordWidth = 16;
    private static int wordHeight = 18;

    /////
    private static DefaultManageableImageCaptchaService instance;

    private static boolean isInit = false;

    public static void init(String keyWord, int fontSize, int size,
                            int picWidth, int picHeight, int wordWidth, int wordHeight) {

        if (!isInit) {
            ImageCaptchaEngine.keyWord = keyWord;
            ImageCaptchaEngine.fontSize = fontSize;
            ImageCaptchaEngine.size = size;
            ImageCaptchaEngine.picWidth = picWidth;
            ImageCaptchaEngine.picHeight = picHeight;
            ImageCaptchaEngine.wordWidth = wordWidth;
            ImageCaptchaEngine.wordHeight = wordHeight;

            instance = new DefaultManageableImageCaptchaService
                    (new FastHashMapCaptchaStore(), new ImageCaptchaEngine(), 180, 100000, 75000);
            //instance = new DefaultManageableImageCaptchaService();

            /* DefaultManageableImageCaptchaService 构造说明
            DefaultManageableImageCaptchaService(CaptchaStore captchaStore,
                com.octo.captcha.engine.CaptchaEngine captchaEngine,
            int minGuarantedStorageDelayInSeconds,
            int maxCaptchaStoreSize,
            int captchaStoreLoadBeforeGarbageCollection)
            */

        }
        isInit = true;
    }

    public static void init() {
        if (!isInit) {
            instance = new DefaultManageableImageCaptchaService
                    (new FastHashMapCaptchaStore(), new ImageCaptchaEngine(), 180, 100000, 75000);
            //instance = new DefaultManageableImageCaptchaService();
        }
        isInit = true;
    }

    public static ImageCaptchaService getInstance() {
        if (!isInit)
            init();
        return instance;
    }

    public static void reloadCaptchaEngine(CaptchaEngine ce) {
        if (instance != null && ce != null) {
            instance.setCaptchaEngine(ce);
        }
    }

    public ImageCaptchaEngine() {
    }

    protected void buildInitialFactories() {

        logger.debug("keyWord = " + keyWord);
        logger.debug("fontSize = " + fontSize);
        logger.debug("size = " + size);
        logger.debug("picWidth = " + picWidth);
        logger.debug("picHeight = " + picHeight);
        logger.debug("wordWidth = " + wordWidth);
        logger.debug("wordHeight = " + wordHeight);

        // 随机生成的字符
        // abcdefghijklmnopqrstuvwxyz0123456789
        WordGenerator wgen = new RandomWordGenerator(keyWord);
        RandomRangeColorGenerator cgen =
                new RandomRangeColorGenerator(
                        new int[]{60, 80},
                        new int[]{60, 80},
                        new int[]{60, 80});
        /*
        RandomRangeColorGenerator(int[] redComponentRange,
        int[] greenComponentRange,
        int[] blueComponentRange,
        int[] alphaComponentRange)
        */

        // 文字显示的个数
        TextPaster textPaster = new RandomTextPaster(size, size, cgen, true);
        java.util.List<Color> colorList = new ArrayList<Color>();
        colorList.add(Color.ORANGE);
        //colorList.add(Color.DARK_GRAY);
        colorList.add(Color.WHITE);
        //java.util.Collections.shuffle(colorList);
        // 图片的大小
        BackgroundGenerator backgroundGenerator =
                new GradientBackgroundGenerator(picWidth, picHeight, colorList.get(1), colorList.get(new Random().nextInt(1))); //渐变
        //new UniColorBackgroundGenerator(picWidth, picHeight,Color.GRAY);//单色的
        //new EllipseBackgroundGenerator(picWidth, picHeight); //椭圆的
        //new FunkyBackgroundGenerator(picWidth, picHeight); //彩色的

        // 字体格式
        Font[] fontsList = new Font[]{
                new Font("Arial", 0, fontSize),
                new Font("Tahoma", 0, fontSize),
                new Font("Verdana", 0, fontSize),};

        // 文字的大小
        FontGenerator fontGenerator =
                new RandomFontGenerator(wordWidth, wordHeight, fontsList);

        WordToImage wordToImage = new ComposedWordToImage
                (fontGenerator, backgroundGenerator, textPaster);
        this.addFactory(new GimpyFactory(wgen, wordToImage));
    }
    

    public String toString() {
        return "ImageCaptchaEngine{" +
                "keyWord='" + keyWord + '\'' +
                ", fontSize=" + fontSize +
                ", size=" + size +
                ", picWidth=" + picWidth +
                ", picHeight=" + picHeight +
                ", wordWidth=" + wordWidth +
                ", wordHeight=" + wordHeight +
                '}';
    }
}
