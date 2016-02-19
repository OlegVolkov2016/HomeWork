package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;

/**
 * Created by Олег Волков on 26.01.2016.
 */
public class ImageReaderFactory
{
    public static ImageReader getReader(ImageTypes type) {
        if (ImageTypes.BMP.equals(type)) {
            return new BmpReader();
        } else if (ImageTypes.PNG.equals(type)) {
            return new PngReader();
        } else if (ImageTypes.JPG.equals(type)) {
            return new JpgReader();
        } else {
            throw new IllegalArgumentException("Неизвестный тип картинки");
        }
    }
}
