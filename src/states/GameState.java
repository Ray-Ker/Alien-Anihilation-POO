package states;
import GameObject.Meteor;
import GameObject.MovingObject;
import GameObject.Player;
import graphics.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import math.Vector2D;
import spacemain.Constants;

public class GameState {
	
	private Player player;
	private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
        
        public int meteors;
	
	public GameState()
	{
		player = new Player(new Vector2D(Constants.WIDTH/2 - Assets.player.getWidth()/2,
				Constants.HEIGHT/2 - Assets.player.getHeight()/2), new Vector2D(), Constants.PLAYER_MAX_VEL, Assets.player, this);
		movingObjects.add(player);
                
                meteors = 1;
                
                startWave();
		
	}
        
        private void startWave()
        {
            double x,y;
            BufferedImage texture = Assets.aste1;

            x = Math.random()*Constants.WIDTH;
            y = Math.random()*Constants.HEIGHT;
                movingObjects.add(new Meteor(new Vector2D(x,y),
                new Vector2D(0,1).setDirection(Math.random()*Math.PI*2),
                 Constants.METEOR_VEL*Math.random()+1,
                 texture,
                this));
    
            meteors++;
    
            if(meteors < Constants.NUMBER_OF_METEORS_ON_WAVE){
                startWave();
                }
        }   

        
        public void update() {
                for(int i = 0; i < movingObjects.size(); i++) {
                        movingObjects.get(i).update();
                }

                boolean hasMeteors = false;
                for(int i = 0; i < movingObjects.size(); i++) {
                    if(movingObjects.get(i) instanceof Meteor) {
                        hasMeteors = true;
                        break;
                    }
                }           

                if(!hasMeteors) {
                 startWave();
            }
        }
	
	public void draw(Graphics g)
	{	
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		for(int i = 0; i < movingObjects.size(); i++)
			movingObjects.get(i).draw(g);
	}

	public ArrayList<MovingObject> getMovingObjects() {
		return movingObjects;
	}
}