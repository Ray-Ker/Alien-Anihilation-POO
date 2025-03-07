package graphics;
import java.awt.image.BufferedImage;

public class Assets {
    
    public static BufferedImage player;
    
    //efectos
    
    public static BufferedImage speed;
    
    //Laser
    
    public static BufferedImage blueLaser;
    
    //Meteoritos
    
    public static BufferedImage aste1;
    
    public static BufferedImage aste2;
    
    public static BufferedImage aste3;
    
    public static void init()
    {
        player = Loader.ImageLoader("/ships/player.png");
        
        //speed = Loader.ImageLoader("/effects/blue fire.png");
        
        blueLaser = Loader.ImageLoader("/lasers/lasers.png");
        
        aste1 = Loader.ImageLoader("/meteors/aste1.png");
        
        aste2 = Loader.ImageLoader("/meteors/aste2.png");
        
        aste3 = Loader.ImageLoader("/meteors/aste3.png");
    }
}