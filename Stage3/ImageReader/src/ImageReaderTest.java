import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

public class ImageReaderTest {

    @Before
    public void setUp() throws Exception {
    }

    /**
     * 测试读取的图片与API是否相同
     */
    @Test
    public void testImageRead() {
        try {
            // 目标文件
            InputStream goalFile = new FileInputStream("res/1.bmp");
            // 调用API
            BufferedImage goal = ImageIO.read(goalFile);
            int goalWidth = goal.getWidth(null);
            int goalHeight = goal.getHeight(null);
            // 测试文件
            ImageIOTanX testIO = new ImageIOTanX();
            Image testImage = testIO.myRead("res/1.bmp");
            int testWidth = testImage.getWidth(null);
            int testHeight = testImage.getHeight(null);
            // 调用API (类似调用API的写入部分)
            BufferedImage test = new BufferedImage(testWidth, testHeight,
                    BufferedImage.TYPE_INT_BGR);
            test.getGraphics().drawImage(testImage, 0, 0, testWidth, testHeight,
                    null);
            // 至此, goal与test准备完毕,可以开始测试
            // 首先测试宽高
            assertEquals(goal.getHeight(), test.getHeight());
            assertEquals(goal.getWidth(), test.getWidth());
            // 然后逐个像素测定RGB
            int goalRGB[] = new int[goalWidth * goalHeight];
            int testRGB[] = new int[goalWidth * goalHeight];
            goal.getRGB(0, 0, goalWidth, goalHeight, goalRGB, 0, goalWidth);
            test.getRGB(0, 0, testWidth, testHeight, testRGB, 0, goalWidth);
            assertArrayEquals(goalRGB, testRGB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试R保存的图片与API是否相同 测试分两组,一组1.bmp,一组2.bmp
     */
    @Test
    public void testChanelRed() {
        try {
            // 目标文件
            InputStream goalRedFile1 = new FileInputStream(
                    "res/goal/1_red_goal.bmp");
            // 调用API
            BufferedImage goalRed1 = ImageIO.read(goalRedFile1);
            int goalWidth1 = goalRed1.getWidth(null);
            int goalHeight1 = goalRed1.getHeight(null);
            // 测试文件
            ImageIOTanX testIORed1 = new ImageIOTanX();
            ImageProcessorTanX procTescIORed1 = new ImageProcessorTanX();
            Image testImageRed1 = procTescIORed1.showChanelR(testIORed1.myRead(
                    "res/1.bmp"));
            int testWidth1 = testImageRed1.getWidth(null);
            int testHeight1 = testImageRed1.getHeight(null);
            // 调用API (类似调用API的写入部分)
            BufferedImage testRed1 = new BufferedImage(testWidth1, testHeight1,
                    BufferedImage.TYPE_INT_BGR);
            testRed1.getGraphics().drawImage(testImageRed1, 0, 0, testWidth1,
                    testHeight1, null);
            // 至此, goal与test准备完毕,可以开始测试
            // 逐个像素测定RGB
            int goalRGBRed1[] = new int[goalWidth1 * goalHeight1];
            int testRGBRed1[] = new int[goalWidth1 * goalHeight1];
            goalRed1.getRGB(0, 0, goalWidth1, goalHeight1, goalRGBRed1, 0,
                    goalWidth1);
            testRed1.getRGB(0, 0, testWidth1, testHeight1, testRGBRed1, 0,
                    testWidth1);
            assertArrayEquals(goalRGBRed1, testRGBRed1);

            // 第二组测试
            // 目标文件
            InputStream goalFileRed2 = new FileInputStream(
                    "res/goal/2_red_goal.bmp");
            // 调用API
            BufferedImage goalRed2 = ImageIO.read(goalFileRed2);
            int goalWidth2 = goalRed2.getWidth(null);
            int goalHeight2 = goalRed2.getHeight(null);
            // 测试文件
            ImageIOTanX testIORed2 = new ImageIOTanX();
            ImageProcessorTanX procTescIO2 = new ImageProcessorTanX();
            Image testImageRed2 = procTescIO2.showChanelR(testIORed2.myRead(
                    "res/2.bmp"));
            int testWidth2 = testImageRed2.getWidth(null);
            int testHeight2 = testImageRed2.getHeight(null);
            // 调用API (类似调用API的写入部分)
            BufferedImage testRed2 = new BufferedImage(testWidth2, testHeight2,
                    BufferedImage.TYPE_INT_BGR);
            testRed2.getGraphics().drawImage(testImageRed2, 0, 0, testWidth2,
                    testHeight2, null);
            // 至此, goal与test准备完毕,可以开始测试
            // 逐个像素测定RGB
            int goalRGBRed2[] = new int[goalWidth2 * goalHeight2];
            int testRGBRed2[] = new int[goalWidth2 * goalHeight2];
            goalRed2.getRGB(0, 0, goalWidth2, goalHeight2, goalRGBRed2, 0,
                    goalWidth2);
            testRed2.getRGB(0, 0, testWidth2, testHeight2, testRGBRed2, 0,
                    testWidth2);
            assertArrayEquals(goalRGBRed2, testRGBRed2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试G保存的图片与API是否相同 测试分两组,一组1.bmp,一组2.bmp
     */
    @Test
    public void testChanelGreen() {
        try {
            // 目标文件
            InputStream goalGreenFile1 = new FileInputStream(
                    "res/goal/1_green_goal.bmp");
            // 调用API
            BufferedImage goalGreen1 = ImageIO.read(goalGreenFile1);
            int goalWidth1 = goalGreen1.getWidth(null);
            int goalHeight1 = goalGreen1.getHeight(null);
            // 测试文件
            ImageIOTanX testIOGreen1 = new ImageIOTanX();
            ImageProcessorTanX procTescIOGreen1 = new ImageProcessorTanX();
            Image testImageGreen1 = procTescIOGreen1.showChanelG(testIOGreen1
                    .myRead("res/1.bmp"));
            int testWidth1 = testImageGreen1.getWidth(null);
            int testHeight1 = testImageGreen1.getHeight(null);
            // 调用API (类似调用API的写入部分)
            BufferedImage testGreen1 = new BufferedImage(testWidth1,
                    testHeight1, BufferedImage.TYPE_INT_BGR);
            testGreen1.getGraphics().drawImage(testImageGreen1, 0, 0,
                    testWidth1, testHeight1, null);
            // 至此, goal与test准备完毕,可以开始测试
            // 逐个像素测定RGB
            int goalRGBGreen1[] = new int[goalWidth1 * goalHeight1];
            int testRGBGreen1[] = new int[goalWidth1 * goalHeight1];
            goalGreen1.getRGB(0, 0, goalWidth1, goalHeight1, goalRGBGreen1, 0,
                    goalWidth1);
            testGreen1.getRGB(0, 0, testWidth1, testHeight1, testRGBGreen1, 0,
                    testWidth1);
            assertArrayEquals(goalRGBGreen1, testRGBGreen1);

            // 第二组测试
            // 目标文件
            InputStream goalFileGreen2 = new FileInputStream(
                    "res/goal/2_green_goal.bmp");
            // 调用API
            BufferedImage goalGreen2 = ImageIO.read(goalFileGreen2);
            int goalWidth2 = goalGreen2.getWidth(null);
            int goalHeight2 = goalGreen2.getHeight(null);
            // 测试文件
            ImageIOTanX testIOGreen2 = new ImageIOTanX();
            ImageProcessorTanX procTescIO2 = new ImageProcessorTanX();
            Image testImageGreen2 = procTescIO2.showChanelG(testIOGreen2.myRead(
                    "res/2.bmp"));
            int testWidth2 = testImageGreen2.getWidth(null);
            int testHeight2 = testImageGreen2.getHeight(null);
            // 调用API (类似调用API的写入部分)
            BufferedImage testGreen2 = new BufferedImage(testWidth2,
                    testHeight2, BufferedImage.TYPE_INT_BGR);
            testGreen2.getGraphics().drawImage(testImageGreen2, 0, 0,
                    testWidth2, testHeight2, null);
            // 至此, goal与test准备完毕,可以开始测试
            // 逐个像素测定RGB
            int goalRGBGreen2[] = new int[goalWidth2 * goalHeight2];
            int testRGBGreen2[] = new int[goalWidth2 * goalHeight2];
            goalGreen2.getRGB(0, 0, goalWidth2, goalHeight2, goalRGBGreen2, 0,
                    goalWidth2);
            testGreen2.getRGB(0, 0, testWidth2, testHeight2, testRGBGreen2, 0,
                    testWidth2);
            assertArrayEquals(goalRGBGreen2, testRGBGreen2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试B保存的图片与API是否相同 测试分两组,一组1.bmp,一组2.bmp
     */
    @Test
    public void testChanelBlue() {
        try {
            // 目标文件
            InputStream goalBlueFile1 = new FileInputStream(
                    "res/goal/1_blue_goal.bmp");
            // 调用API
            BufferedImage goalBlue1 = ImageIO.read(goalBlueFile1);
            int goalWidth1 = goalBlue1.getWidth(null);
            int goalHeight1 = goalBlue1.getHeight(null);
            // 测试文件
            ImageIOTanX testIOBlue1 = new ImageIOTanX();
            ImageProcessorTanX procTescIOBlue1 = new ImageProcessorTanX();
            Image testImageBlue1 = procTescIOBlue1.showChanelB(testIOBlue1
                    .myRead("res/1.bmp"));
            int testWidth1 = testImageBlue1.getWidth(null);
            int testHeight1 = testImageBlue1.getHeight(null);
            // 调用API (类似调用API的写入部分)
            BufferedImage testBlue1 = new BufferedImage(testWidth1, testHeight1,
                    BufferedImage.TYPE_INT_BGR);
            testBlue1.getGraphics().drawImage(testImageBlue1, 0, 0, testWidth1,
                    testHeight1, null);
            // 至此, goal与test准备完毕,可以开始测试
            // 逐个像素测定RGB
            int goalRGBBlue1[] = new int[goalWidth1 * goalHeight1];
            int testRGBBlue1[] = new int[goalWidth1 * goalHeight1];
            goalBlue1.getRGB(0, 0, goalWidth1, goalHeight1, goalRGBBlue1, 0,
                    goalWidth1);
            testBlue1.getRGB(0, 0, testWidth1, testHeight1, testRGBBlue1, 0,
                    testWidth1);
            assertArrayEquals(goalRGBBlue1, testRGBBlue1);

            // 第二组测试
            // 目标文件
            InputStream goalFileBlue2 = new FileInputStream(
                    "res/goal/2_blue_goal.bmp");
            // 调用API
            BufferedImage goalBlue2 = ImageIO.read(goalFileBlue2);
            int goalWidth2 = goalBlue2.getWidth(null);
            int goalHeight2 = goalBlue2.getHeight(null);
            // 测试文件
            ImageIOTanX testIOBlue2 = new ImageIOTanX();
            ImageProcessorTanX procTescIO2 = new ImageProcessorTanX();
            Image testImageBlue2 = procTescIO2.showChanelB(testIOBlue2.myRead(
                    "res/2.bmp"));
            int testWidth2 = testImageBlue2.getWidth(null);
            int testHeight2 = testImageBlue2.getHeight(null);
            // 调用API (类似调用API的写入部分)
            BufferedImage testBlue2 = new BufferedImage(testWidth2, testHeight2,
                    BufferedImage.TYPE_INT_BGR);
            testBlue2.getGraphics().drawImage(testImageBlue2, 0, 0, testWidth2,
                    testHeight2, null);
            // 至此, goal与test准备完毕,可以开始测试
            // 逐个像素测定RGB
            int goalRGBBlue2[] = new int[goalWidth2 * goalHeight2];
            int testRGBBlue2[] = new int[goalWidth2 * goalHeight2];
            goalBlue2.getRGB(0, 0, goalWidth2, goalHeight2, goalRGBBlue2, 0,
                    goalWidth2);
            testBlue2.getRGB(0, 0, testWidth2, testHeight2, testRGBBlue2, 0,
                    testWidth2);
            assertArrayEquals(goalRGBBlue2, testRGBBlue2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试Gray保存的图片与API是否相同 测试分两组,一组1.bmp,一组2.bmp
     */
    @Test
    public void testChanelGray() {
        try {
            // 目标文件
            InputStream goalFile1 = new FileInputStream(
                    "res/goal/1_gray_goal.bmp");
            // 调用API
            BufferedImage goal1 = ImageIO.read(goalFile1);
            int goalWidth1 = goal1.getWidth(null);
            int goalHeight1 = goal1.getHeight(null);
            // 测试文件
            ImageIOTanX testIO1 = new ImageIOTanX();
            ImageProcessorTanX procTescIO1 = new ImageProcessorTanX();
            Image testImage1 = procTescIO1.showGray(testIO1.myRead(
                    "res/1.bmp"));
            int testWidth1 = testImage1.getWidth(null);
            int testHeight1 = testImage1.getHeight(null);
            // 调用API (类似调用API的写入部分)
            BufferedImage test1 = new BufferedImage(testWidth1, testHeight1,
                    BufferedImage.TYPE_INT_BGR);
            test1.getGraphics().drawImage(testImage1, 0, 0, testWidth1,
                    testHeight1, null);
            // 至此, goal与test准备完毕,可以开始测试
            // 逐个像素测定RGB
            int goalRGB1[] = new int[goalWidth1 * goalHeight1];
            int testRGB1[] = new int[goalWidth1 * goalHeight1];
            goal1.getRGB(0, 0, goalWidth1, goalHeight1, goalRGB1, 0,
                    goalWidth1);
            test1.getRGB(0, 0, testWidth1, testHeight1, testRGB1, 0,
                    testWidth1);
            assertArrayEquals(goalRGB1, testRGB1);

            // 第二组测试
            // 目标文件
            InputStream goalFile2 = new FileInputStream(
                    "res/goal/2_gray_goal.bmp");
            // 调用API
            BufferedImage goal2 = ImageIO.read(goalFile2);
            int goalWidth2 = goal2.getWidth(null);
            int goalHeight2 = goal2.getHeight(null);
            // 测试文件
            ImageIOTanX testIO2 = new ImageIOTanX();
            ImageProcessorTanX procTescIO2 = new ImageProcessorTanX();
            Image testImage2 = procTescIO2.showGray(testIO2.myRead(
                    "res/2.bmp"));
            int testWidth2 = testImage2.getWidth(null);
            int testHeight2 = testImage2.getHeight(null);
            // 调用API (类似调用API的写入部分)
            BufferedImage test2 = new BufferedImage(testWidth2, testHeight2,
                    BufferedImage.TYPE_INT_BGR);
            test2.getGraphics().drawImage(testImage2, 0, 0, testWidth2,
                    testHeight2, null);
            // 至此, goal与test准备完毕,可以开始测试
            // 逐个像素测定RGB
            int goalRGB2[] = new int[goalWidth2 * goalHeight2];
            int testRGB2[] = new int[goalWidth2 * goalHeight2];
            goal2.getRGB(0, 0, goalWidth2, goalHeight2, goalRGB2, 0,
                    goalWidth2);
            test2.getRGB(0, 0, testWidth2, testHeight2, testRGB2, 0,
                    testWidth2);
            assertArrayEquals(goalRGB2, testRGB2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
