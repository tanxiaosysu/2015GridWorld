import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

import imagereader.IImageProcessor;

/*
 * AP(r) Computer Science GridWorld Case Study: Copyright(c) 2005-2006 Cay S.
 * Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * @author Amin
 */

/**
 * 提取图片的色彩通道
 * 
 * 此处继承抽象类RGBImageFilter并分别进行R G B Gray的实现
 */
public class ImageProcessorTanX implements IImageProcessor {
    // R G B分别需要与运算的值(alpha默认255)
    private static int RED = 0xffff0000;
    private static int GREEN = 0xff00ff00;
    private static int BLUE = 0xff0000ff;

    class RFilter extends RGBImageFilter {
        public RFilter() {
            canFilterIndexColorModel = true;
        }

        @Override
        public int filterRGB(int x, int y, int rgb) {
            return (rgb & RED);
        }
    }

    class GFilter extends RGBImageFilter {
        public GFilter() {
            canFilterIndexColorModel = true;
        }

        @Override
        public int filterRGB(int x, int y, int rgb) {
            return (rgb & GREEN);
        }
    }

    class BFilter extends RGBImageFilter {
        public BFilter() {
            canFilterIndexColorModel = true;
        }

        @Override
        public int filterRGB(int x, int y, int rgb) {
            return (rgb & BLUE);
        }
    }

    class GrayFilter extends RGBImageFilter {
        public GrayFilter() {
            canFilterIndexColorModel = true;
        }

        @Override
        public int filterRGB(int x, int y, int rgb) {
            // 先去掉alpha
            int temp = rgb & 0x00ffffff;
            // 因为运算同时涉及R G B,且有小数,所以强制转化
            int r = (temp & RED) >> 16;
            int g = (temp & GREEN) >> 8;
            int b = temp & BLUE;
            // 0.299 * R + 0.587 * G + 0.114 * B
            temp = (int) (r * 0.299 + g * 0.587 + b * 0.114);
            int ret = (0xff << 24 | temp << 16 | temp << 8 | temp);
            return ret;
        }
    }

    @Override
    public Image showChanelR(Image arg0) {
        // TODO Auto-generated method stub
        RGBImageFilter filter = new RFilter();
        // 调用API
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(
                arg0.getSource(), filter));
    }

    @Override
    public Image showChanelG(Image arg0) {
        // TODO Auto-generated method stub
        RGBImageFilter filter = new GFilter();
        // 调用API
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(
                arg0.getSource(), filter));
    }

    @Override
    public Image showChanelB(Image arg0) {
        // TODO Auto-generated method stub
        RGBImageFilter filter = new BFilter();
        // 调用API
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(
                arg0.getSource(), filter));
    }

    @Override
    public Image showGray(Image arg0) {
        // TODO Auto-generated method stub
        RGBImageFilter filter = new GrayFilter();
        // 调用API
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(
                arg0.getSource(), filter));
    }
}
