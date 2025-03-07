package GameObject;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import math.Vector2D;
import spacemain.Constants;
import states.GameState;

public class Laser extends MovingObject{

    public Laser(Vector2D position, Vector2D velocity, double maxVel, double angle, BufferedImage texture, GameState gameState) {
        super(position, velocity, maxVel, texture, gameState);
        this.angle = angle;
        this.velocity = velocity.scale(maxVel);
    }

    @Override
    public void update() {
        position = position.add(velocity);
        if(position.getX() < 0 || position.getX() > Constants.WIDTH || 
                     position.getY()< 0 || position.getY() > Constants.HEIGHT){
            Destroy();
        }
        collidesWith();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        
        at = AffineTransform.getTranslateInstance(position.getX()- width/1.65,position.getY());
        //Estas 2 posicionan el laser en el centro de la nave (la linea de arriba y abajo)
        at.rotate(angle, width/1.65,0);
        
        g2d.drawImage(texture, at, null);
    }
    
    @Override
    public Vector2D getCenter(){
		return new Vector2D(position.getX() + width/2, position.getY() + width/2);
	}
}

    
