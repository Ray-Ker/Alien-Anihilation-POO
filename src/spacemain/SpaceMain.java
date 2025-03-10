package spacemain;
import GameObject.Musi;
import graphics.Assets;
import input.KeyBoard;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import states.GameState;

public class SpaceMain extends JFrame implements Runnable{
    
    public static final int WIDTH = 1200, HEIGHT = 850; 
    private Canvas canvas;
    private Thread thread;
    private boolean running = false;
    
    private BufferStrategy bs;
    private Graphics g;
    
    private final int FPS = 60;
    private double TARGETTIME  = 1000000000/FPS;
    private double delta = 0;
    private int AVERAGEFPS = FPS;
    
    private GameState gameState;
    private KeyBoard keyBoard;
    
    public SpaceMain()
            {
                setTitle("Alien Annihilation");
                setSize(Constants.WIDTH, Constants.HEIGHT);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setResizable(false);
                setLocationRelativeTo(null);
                
                
                canvas = new Canvas();
                keyBoard = new KeyBoard();
                
                canvas.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT) );
                canvas.setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT) );
                canvas.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT) );
                canvas.setFocusable(true);
                
                
                add(canvas);
                canvas.addKeyListener(keyBoard);
                setVisible(true);
            }

    public static void main(String[] args) {
        SpaceMain spaceMain = new SpaceMain();
        spaceMain.start();

    }
    
    private void update (){
        keyBoard.update();
        gameState.update();
    }
    
    private void draw(){
        bs= canvas.getBufferStrategy();
        
        if(bs == null)
        {
        canvas.createBufferStrategy(3);
        return;
        }
        
        g = bs.getDrawGraphics();
        
        //--------(Aquí inicia el dibujo)---------
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //g.drawRect(x,0,100,100);
        g.setColor(Color.BLACK);
        
        //g.drawImage(Assets.player, 100, 100, null);
        
        gameState.draw(g);
        
        g.drawString(""+AVERAGEFPS, 10, 20);
        
        //--------(Aquí termina el dibujo)---------
        g.dispose();
        bs.show();
                
    }
    private Musi musi;

    private void init() {
    Assets.init();
    gameState = new GameState();
    musi = new Musi();
    musi.playMusic("/music/musi.wav");
}


    @Override
    public void run() {

        long now = 0;
        long lastTime = System.nanoTime();
        int frames  = 0;
        long time = 0;
        
        init();
        
        while (running)
        {
            
            now = System.nanoTime(); 
            delta +=(now - lastTime)/TARGETTIME;
            time += (now - lastTime);
            lastTime = now;
            
            if(delta >= 1)
            {
                update();
                draw();
                delta --;
                frames ++;
                //System.out.println(frames);
            }
            if(time >= 1000000000)
            {
                AVERAGEFPS = frames;
                frames = 0;
                time = 0;
            }  
        }           
        stop();
    }
     
    private void start (){
        
        thread = new Thread(this);
        thread.start();
        running = true;
        
    }
    private void stop(){
        try {
            thread.join();
            running = false;
        } catch (InterruptedException ex) {
            
        }
    } 
}