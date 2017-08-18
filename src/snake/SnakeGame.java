package snake;

import javax.swing.*;

public class SnakeGame extends JFrame {

    public SnakeGame(){

        add(new Board());

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);
    }
}
