import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;

public class pixel {
    public static void main(String[] args) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("shades.png"));
        } catch (IOException e) {
            
        }
        loadLevel(img);
    }
    
    
    public static void loadLevel(BufferedImage levelImage){
        int[][] tiles = new int[levelImage.getWidth()][levelImage.getHeight()];
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                Color c = new Color(levelImage.getRGB(x, y));
                //String h = String.format("%02x%02x%02x", c.getRed(),c.getGreen(),c.getBlue());
                String red = "" + c.getRed() + ", " + c.getGreen() + ", " + c.getBlue();
                System.out.println(red);
            }
        }
    }
}
