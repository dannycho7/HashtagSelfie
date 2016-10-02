import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
/**
 * Write a description of class Filter here.
 * 
 * @author (Danny Cho) 
 * @version (11/14/15)
 */
public class Picture
{
    private BufferedImage image;
    private int height ;
    private int width;
    private String filename;

    public Picture(BufferedImage img)
    {
        image = img;
        height = img.getHeight();
        width = img.getWidth();
    }
    
    public int returnWidth() {
        return width;
    }
    
    public int returnHeight() {
        return height;
    }
    
    public BufferedImage getBufferedImage() {
        return image;
    }
   
    public void setName(String name) {
        filename = name;
    }
    
    public static void main(String[] args) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("Shades.png"));
        } catch (IOException e) {}
        Picture pic = new Picture(image);
        pic.negate();
        File outputfile = new File("Shades.png");
        try {
            ImageIO.write(image, "png", outputfile);
        }
        catch (IOException e) {}
    }

    public void DannyFilter(double x , double y)
    {
        saturate(y);
        brighten((int)x);
    }
    public void adjust(double redAdjust , double greenAdjust, double blueAdjust)
    {
        for(int y = 0 ; y < height  ; y++)
        {
            for(int x = 0 ; x < width ; x ++)
            {
                Color color = new Color(image.getRGB(x , y));
                int red = (int)redAdjust * color.getRed() ; 
                int green = (int)greenAdjust * color.getGreen();
                int blue = (int)blueAdjust * color.getBlue();
                if(red > 255)
                {
                    red = 255;
                }
                if(green > 255)
                {
                    green = 255;
                }
                if(blue > 255)
                {
                    blue = 255;
                }
                Color rgb = new Color ( red , green , blue);
                image.setRGB(x , y , rgb.getRGB());
            }
        }

    }

    public void brighten(int value)
    {
        for(int y = 0 ; y < height ; y ++)
        {
            for(int x = 0 ; x < width ; x ++)
            {
                Color color = new Color(image.getRGB(x , y));
                int red = color.getRed() + value;
                int green = color.getGreen() + value;
                int blue = color.getBlue() + value;
                if(red > 255)
                {
                    red = 255;
                }
                if(green > 255)
                {
                    green = 255;
                }
                if(blue > 255)
                {
                    blue = 255;
                }
                Color rgb = new Color(red , green , blue);
                image.setRGB(x , y, rgb.getRGB());
            }
        }
    }

    public void alpha(int value)
    {
        for(int y = 0 ; y < height ; y ++)
        {
            for(int x = 0 ; x < width ; x ++)
            {
                Color color = new Color(image.getRGB(x , y));
                Color rgb = new Color(color.getRed() , color.getGreen() , color.getBlue() , value);
                image.setRGB(x , y , rgb.getRGB());
            }
        }
    }

    public void gamma(double value)
    {
        for(int y = 0 ; y < height ; y ++)
        {
            for(int x = 0; x < width ; x ++)
            {
                Color color = new Color(image.getRGB(x , y));
                int red = (int) Math.pow(color.getRed() , value) ;
                int green = (int)Math.pow(color.getGreen() , value);
                int blue = (int)Math.pow(color.getBlue() , value);
                if(red > 255)
                {
                    red = 255;
                }
                if(green > 255)
                {
                    green = 255;
                }
                if(blue > 255)
                {
                    blue = 255;
                }
                Color rgb = new Color(red , green , blue);
                image.setRGB(x , y , rgb.getRGB());
            }
        }
    }

    public void sepia()
    {
        for(int y = 0 ; y < height ; y ++)
        {
            for(int x = 0 ; x < width ; x ++)
            {
                Color color = new Color(image.getRGB(x , y));
                double red = (0.393*color.getRed()) + (0.769*color.getGreen()) + (0.189*color.getBlue());
                double green = (0.349*color.getRed()) + (0.686*color.getGreen()) + (0.168*color.getBlue());
                double blue = (0.272*color.getRed()) + (0.534*color.getGreen()) + (0.131*color.getBlue());
                if(red > 255)
                {
                    red = 255;
                }
                if(green > 255)
                {
                    green = 255;
                }
                if(blue > 255)
                {
                    blue = 255;
                }
                Color rgb =  new Color((int)Math.round(red) , (int)Math.round(green) , (int)Math.round(blue));
                image.setRGB(x , y , rgb.getRGB());
            }
        }
    }

    public void saturate(double value)
    {
        for(int y = 0 ; y < height ; y ++)
        {
            for(int x = 0 ; x < width ; x ++)
            {
                Color color = new Color(image.getRGB(x , y));
                int avg = (color.getRed() + color.getGreen() + color.getBlue())/3 ;
                int red = (int)(avg + value*(color.getRed() - avg));
                int green = (int)( avg + value*(color.getGreen() - avg));
                int blue = (int)( avg + value*(color.getBlue() - avg));
                if(red > 255)
                {
                    red = 255;
                }
                if(green > 255)
                {
                    green = 255;
                }
                if(blue > 255)
                {
                    blue = 255;
                }
                if(red > 0)
                {
                    red = 0;
                }
                if(green > 0)
                {
                    green = 0;
                }
                if(blue > 0)
                {
                    blue = 0;
                }
                Color rgb = new Color(red , green , blue);
                image.setRGB(x , y , rgb.getRGB());
            }
        }

    }

    public void negate()
    {
        for(int y = 0; y < height ; y++)
        {
            for(int x = 0; x < width ; x++)
            {
                Color color  = new Color(image.getRGB(x,y));
                Color rgb = new Color(255 - color.getRed() ,255 - color.getBlue() ,255- color.getGreen());
                image.setRGB(x , y,rgb.getRGB());

            }
        }
    }

    public void grayscale (int value) //value cannot be 0 the original grayscale is value of 1 
    {
        for(int y = 0; y<height ; y++)
        {
            for(int x = 0; x<width ; x++)
            {
                Color color  = new Color(image.getRGB(x,y));
                int gray = (((color.getRed()+color.getGreen()+color.getBlue())/3)*value);
                Color rgb = new Color(gray , gray ,gray);
                image.setRGB(x, y, rgb.getRGB());
            }
        }

    }

    public void invert() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                Color rgb = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
                image.setRGB(x, y, rgb.getRGB());
            }
        }
    }

    public void postertize(int p) {
        int step = (int) Math.floor(255 / p);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                Color rgb = new Color((int)(Math.floor((color.getRed() / step)) * step), (int)(Math.floor(color.getGreen() / step) * step), (int)(Math.floor(color.getBlue() / step) * step));
                image.setRGB(x, y, rgb.getRGB());
            }
        }
    }

    public void contrast(int p) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                double red = 255 * ((color.getRed() / 255 - 0.5) * p + 0.5);
                double green = 255 * ((color.getGreen() / 255 - 0.5) * p + 0.5);
                double blue = 255 * ((color.getBlue() / 255 - 0.5) * p + 0.5);
                Color rgb = new Color((int)red, (int)green, (int)blue);
                image.setRGB(x, y, rgb.getRGB());
            }
        }
    }

    public void subtract(int r, int g, int b) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                Color rgb = new Color(color.getRed() - r, color.getGreen() - g, color.getBlue() - b);
                image.setRGB(x, y, rgb.getRGB());
            }
        }
    }

    public void fill (int r, int g, int b) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = new Color(image.getRGB(x, y));
                Color rgb = new Color(r, g, b);
                image.setRGB(x, y, rgb.getRGB());
            }
        }
    }

}
