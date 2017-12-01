package sources;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import static java.lang.Math.abs;

public class ImagesComparator {
    public static double compare(Image leftImage, Image rightImage){

        int RGBleft[][] = new int[3][256];
        int RGBright[][] = new int[3][256];

        histogram(leftImage, RGBleft);
        histogram(rightImage, RGBright);

        double result = 0;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 256 ; j++){
                int tmp = abs(RGBleft[i][j] - RGBright[i][j]);
                int max = RGBleft[i][j] > RGBright[i][j] ? RGBleft[i][j] : RGBright[i][j];
                max = max == 0 ? 1 : max;
                double diff = (double) tmp/max;
                double res = 1 - diff;
                if( res > 0.5 ){
                    res *= 1.1;
                } else {
                    res *= 0.9;
                }
                if( res > 1 ){
                    res = 1;
                }
                result += res;
            }
        }
        result /= 7.680;
        System.out.println("Score: " + String.format("%.2f", result) );
        return result;
    }

    static void histogram(Image image, int[][] RGB){
        PixelReader pixelReader = image.getPixelReader();
        for(int i = 0 ; i < image.getWidth() ; i++){
            for(int j = 0 ; j < image.getHeight() ; j++){
                Color color = pixelReader.getColor(i,j);
                RGB[0][(int)(color.getRed()*255)]++;
                RGB[1][(int)(color.getGreen()*255)]++;
                RGB[2][(int)(color.getBlue()*255)]++;
            }
        }
    }
}
