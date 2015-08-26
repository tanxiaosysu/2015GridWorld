import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import imagereader.IImageIO;

/**
 * IImageIO接口的实现
 */
public class ImageIOTanX implements IImageIO {
    // 文件头部分为14字节
    private static int HEAD = 14;
    // 文件信息部分40字节
    private static int INFO = 40;
    // 与运算用到的数值
    private static int AND = 0xff;

    /**
     * 二进制字节流读入图片,并显示在屏幕上
     * 
     * @param fileName
     *            图片的文件名
     */
    @Override
    public Image myRead(String fileName) throws IOException {
        Image img = null;
        // 使用 try-catch 接收异常
        try {
            // 字节流
            InputStream file = new FileInputStream(fileName);
            byte headPart[] = new byte[HEAD];
            // 头部分读入headPart
            file.read(headPart, 0, HEAD);
            byte infoPart[] = new byte[INFO];
            /*
             * 信息部分读入infoPart ps:这里的数据都是从低位到高位保存的,例如图片宽度的最高位是
             * infoPart[7],最低位是infoPart[4],所以之后的位运算是由高位开始逐个左移一定位置.....
             * infoPart[4 ]-infoPart[7 ]是图片宽度. infoPart[8 ]-infoPart[11]是图片高度.
             * infoPart[14]-infoPart[15]是图片色深. infoPart[20]-infoPart[23]是图片大小.
             */
            file.read(infoPart, 0, INFO);
            // 读入图片宽度,使用或运算来合并这些位
            int imageWidth = 0;
            imageWidth = ((infoPart[7] & AND) << 24)
                    | ((infoPart[6] & AND) << 16)
                    | ((infoPart[5] & AND) << 8)
                    | (infoPart[4] & AND);
            // 读入图片高度
            int imageHeight = 0;
            imageHeight = ((infoPart[11] & AND) << 24)
                    | ((infoPart[10] & AND) << 16)
                    | ((infoPart[9] & AND) << 8)
                    | (infoPart[8] & AND);
            // 读入色深
            int imageBitCount = 0;
            imageBitCount = ((infoPart[15] & AND) << 8) | (infoPart[14] & AND);
            // 读入图片大小 ps:图片大小应当=宽度×高度×色深÷8(单位byte)
            int imageSize;
            imageSize = ((infoPart[23] & AND) << 24)
                    | ((infoPart[22] & AND) << 16)
                    | ((infoPart[21] & AND) << 8)
                    | (infoPart[20] & AND);
            // 行补位计算公式->每行的字节数
            int widthBytes = (imageWidth * imageBitCount + 31) / 32 * 4;
            // 每行应该补位的字节数
            int fillBlankBytes = widthBytes - imageWidth * imageBitCount / 8;
            // 24位位图的处理(给定图片默认为24位,偷个懒不写其他了..)
            // rgb色彩的字节
            byte rgbDataBytes[] = new byte[imageSize];
            // rgb色彩的整型数据
            int rgbData[] = new int[imageHeight * imageWidth];
            // 使用一个索引index来保证每次访问的都是正确的存储,而不是补位的空字节
            int index = 0;
            if (imageBitCount == 24) {
                // 读入rgb的字节数据
                file.read(rgbDataBytes, 0, imageSize);
                for (int j = 0; j < imageHeight; j++) {
                    for (int i = 0; i < imageWidth; i++) {
                        // 由于文件的像素是从下往上排列,而数组要求是从上往下,所以要用loc定位数组
                        int loc = imageWidth * (imageHeight - j - 1) + i;
                        // 此处255代表透明度
                        rgbData[loc] = ((255 & AND) << 24)
                                | ((rgbDataBytes[index + 2] & AND) << 16)
                                | ((rgbDataBytes[index + 1] & AND) << 8)
                                | (rgbDataBytes[index] & AND);
                        index += 3;
                    }
                    index += fillBlankBytes;
                }
            } else if (imageBitCount == 8) {
                // 8位图像有256个颜色表项,每一项由4个字节组成,所以计算得出colorCount
                int colorCount = (int) Math.pow(2, imageBitCount) * 4;
                // 声明并读取颜色表
                byte colorTable[] = new byte[colorCount];
                file.read(colorTable, 0, colorCount);

                file.read(rgbDataBytes, 0, imageSize);
                for (int j = 0; j < imageHeight; j++) {
                    for (int i = 0; i < imageWidth; i++) {
                        // 由于文件的像素是从下往上排列,而数组要求是从上往下,所以要用loc定位数组
                        int loc = imageWidth * (imageHeight - j - 1) + i;
                        int colorR = colorTable[(rgbDataBytes[index] & AND) * 4
                                + 2] & AND;
                        int colorG = colorTable[(rgbDataBytes[index] & AND) * 4
                                + 1] & AND;
                        int colorB = colorTable[(rgbDataBytes[index] & AND) * 4]
                                & AND;
                        // 此处255代表透明度
                        // 灰度可以考虑把RGB统一为那个数字
                        rgbData[loc] = ((255 & AND) << 24)
                                | (colorR << 16)
                                | (colorG << 8)
                                | (colorB);
                        index += 1;
                    }
                    index += fillBlankBytes;
                }
            }
            // 关闭文件
            file.close();
            // 使用API创建图片
            img = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(
                    imageWidth, imageHeight, rgbData, 0, imageWidth));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * 调用API保存图片
     * 
     * @param srcImage
     *            待处理的图片
     * @param tarImage
     *            目标图片
     */
    @Override
    public Image myWrite(Image srcImage, String tarImage) throws IOException {
        try {
            // 新建目标文件
            File imageFile = new File(tarImage);
            // 获取原图片宽高
            int width = srcImage.getWidth(null);
            int height = srcImage.getHeight(null);
            // 调用API
            BufferedImage bufImage = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_BGR);
            bufImage.getGraphics().drawImage(srcImage, 0, 0, width, height,
                    null);
            ImageIO.write(bufImage, "bmp", imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return srcImage;
    }
}
