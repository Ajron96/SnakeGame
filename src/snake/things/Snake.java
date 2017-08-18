package snake.things;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Snake {

    private final static int PART_SIZE = 10;

    private int parts;  //count of snake's parts
    private final int x[] = new int[900]; //x positions of snake's parts | x[0]y[0] is snake's head
    private final int y[] = new int[900]; //y positions of snake's parts |

    private boolean upDirection = true;
    private boolean downDirection = false;
    private boolean leftDirection = false;
    private boolean rightDirection = false;

    private Image headImg;
    private Image bodyImg;

    public Snake(){

        parts = 3;
        loadImages();
        createSnake();
    }

    public int getX(int x_pos){
        return x[x_pos];
    }

    public int getY(int y_pos){
        return y[y_pos];
    }

    public int getParts(){
        return parts;
    }

    public boolean getUpDirection() {
        return upDirection;
    }

    public boolean getDownDirection() {
        return downDirection;
    }

    public boolean getLeftDirection() {
        return leftDirection;
    }

    public boolean getRightDirection() {
        return rightDirection;
    }

    public void setRightDirection(boolean rightDirection) {
        this.rightDirection = rightDirection;
    }

    public void setUpDirection(boolean upDirection) {
        this.upDirection = upDirection;
    }

    public void setDownDirection(boolean downDirection) {
        this.downDirection = downDirection;
    }

    public void setLeftDirection(boolean leftDirection) {
        this.leftDirection = leftDirection;
    }

    public void addPart(){
        parts++;
    }

    private void loadImages(){

        ImageIcon h = new ImageIcon("imgs/head.png");
        headImg = h.getImage();

        ImageIcon b = new ImageIcon("imgs/body.png");
        bodyImg = b.getImage();
    }

    private void createSnake(){
        for(int i = 0; i < parts; i++){
            x[i] = 50 - i * 10;
            y[i] = 50;
        }
    }

    public void drawSnake(Graphics2D g2d, ImageObserver imgObs){

        g2d.drawImage(headImg, x[0], y[0], imgObs);

        for(int i = 1; i < parts; i++){
           g2d.drawImage(bodyImg, x[i], y[i], imgObs);
        }
    }

    public void move(){

        for(int i = parts; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if(upDirection)     y[0] -= PART_SIZE;
        if(downDirection)   y[0] += PART_SIZE;
        if(leftDirection)   x[0] -= PART_SIZE;
        if(rightDirection)  x[0] += PART_SIZE;
    }
}
