package com.seongbindb.recipe.utils;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.MultiStepRescaleOp;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * <pre>
 * 이미지 용량 관리 및 로딩속도를 위해서 특정 이미지를 썸네일화 하여 관리한다.
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:35:24
 * @Version : 1.0
 */
public class ThumbUtils {

    /**
     * <pre>
     * 클라이언트의 썸네일 등록을 위한 메소드
     * </pre>
     *
     * @param tfile
     * @param thumbWidth
     * @param thumbHeight
     * @return thumbImage
     * @throws Exception
     */
    public static BufferedImage createThumbnail(MultipartFile tfile, int thumbWidth, int thumbHeight) throws Exception {
        try {

            InputStream in = tfile.getInputStream();


            BufferedImage orginalImage = ImageIO.read(in);
            MultiStepRescaleOp rescale = new MultiStepRescaleOp(thumbWidth, thumbHeight);
            rescale.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Soft);
            BufferedImage thumbImage = rescale.filter(orginalImage, null);
            in.close();
            return thumbImage;
        } catch (Exception e) {
            throw new Exception("전달받은 MultipartFile tfile : " + tfile + " / e :" + e);
        }
    }

    /**
     * <pre>
     * 크롤링을 한 대표사진에 대해 썸네일을 생성한다.
     * </pre>
     *
     * @param URL
     * @param FileName
     * @param thumbwidth
     * @param thumbHeight
     * @throws Exception
     */
    public static void createCrawlerThumbnail(String URL, String FileName, int thumbwidth, int thumbHeight) throws Exception {

        if (URL.isEmpty() || FileName.isEmpty()) {
            throw new Exception();
        }
        try {
            BufferedImage buffImage = ImageIO.read(new File(URL + FileName));
            MultiStepRescaleOp rescale = new MultiStepRescaleOp(thumbwidth, thumbHeight);
            rescale.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Soft);
            BufferedImage thumbImage = rescale.filter(buffImage, null);

            String destPath = URL + "thumbnail/" + FileName;
            File destFile = new File(destPath);

            ImageIO.write(thumbImage, "png", destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
