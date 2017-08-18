package snake;

import snake.things.Ball;
import snake.things.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private final int DELAY = 150;

    private boolean inGame = true;

    private Timer timer;

    private Snake snake;
    private Ball ball;

    public Board(){

        addKeyListener(new TAdapter());

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);

        initGame();
    }

    public void initGame(){

        snake = new Snake();
        ball  = new Ball();

        ball.spawnBall();

        timer = new Timer(DELAY, this);
        timer.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if(inGame){

            snake.drawSnake(g2d, this);
            ball.drawBall(g2d, this);
            Toolkit.getDefaultToolkit().sync();
        }else   gameOver(g2d);
    }

    private void gameOver(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;

        String message = "Game Over";
        Font helveticaFont = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metric = getFontMetrics(helveticaFont);

        g2d.setColor(Color.white);
        g2d.setFont(helveticaFont);
        g2d.drawString(message, (WIDTH - metric.stringWidth(message)) / 2, HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(inGame){

            checkBall();
            checkCollision();
            snake.move();
        }

        validate();
        repaint();
    }

    private void checkBall(){

        if( (snake.getX(0) == ball.getX()) && (snake.getY(0) == ball.getY()) ){
            snake.addPart();
            ball.spawnBall();
        }
    }

    private void checkCollision(){

        if(snake.getParts() > 4){
            for(int i = snake.getParts(); i > 0; i--) {
                if((snake.getX(0) == snake.getX(i)) && (snake.getY(0) == snake.getY(i)))
                    inGame = false;
            }
        }

        if((snake.getX(0) < 0) || (snake.getX(0) > WIDTH))
            inGame = false;
        if((snake.getY(0) < 0) || (snake.getY(0) > HEIGHT))
            inGame = false;
        if(!inGame) timer.stop();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            boolean upDirection = snake.getUpDirection();
            boolean downDirection = snake.getDownDirection();
            boolean leftDirection = snake.getLeftDirection();
            boolean rightDirection = snake.getRightDirection();

            if( (key == KeyEvent.VK_UP) && (!downDirection) ){
                snake.setUpDirection(true);
                snake.setLeftDirection(false);
                snake.setRightDirection(false);
            }
            if( (key == KeyEvent.VK_DOWN) && (!upDirection) ){
                snake.setDownDirection(true);
                snake.setLeftDirection(false);
                snake.setRightDirection(false);
            }
            if( (key == KeyEvent.VK_LEFT) && (!rightDirection) ){
                snake.setLeftDirection(true);
                snake.setUpDirection(false);
                snake.setDownDirection(false);
            }
            if( (key == KeyEvent.VK_RIGHT) && (!leftDirection) ){
                snake.setRightDirection(true);
                snake.setUpDirection(false);
                snake.setDownDirection(false);
            }

        }
    }

}
