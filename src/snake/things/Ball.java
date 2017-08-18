package snake.things;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Ball {

    private final static int RAND_POS = 15;

    private int x;
    private int y;

    private Image ballImg;

    public Ball(){

        loadImage();

    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    private void loadImage(){
        ImageIcon b = new ImageIcon("imgs/ball.png");
        ballImg = b.getImage();
    }

    public void spawnBall(){

        int r = (int) (Math.random() * RAND_POS);

        x = r * 10;
        y = r * 10;
    }

    public void drawBall(Graphics2D g2d, ImageObserver imgObs){
        g2d.drawImage(ballImg, x, y, imgObs);
    }

}
