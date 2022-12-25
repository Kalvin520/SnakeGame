import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.*;
import java.util.TimerTask;

public class Main extends JPanel {
    public static final int CELL_SIZE = 20;
    public static int width = 400;//視窗高度
    public static int height = 400;
    public static int row = height / CELL_SIZE;//每格寬
    public static int column = width / CELL_SIZE;//每格高
    private Snake snake;//蛇
    private Fruit fruit;//果實
    private Timer t;
    private int speed = 100;
    public Main(){
        snake = new Snake();//創建蛇
        fruit = new Fruit();//創建果實
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        },0, speed);
    }

    @Override
    public void paintComponent(Graphics g){
        //draw a black background
        g.fillRect(0, 0, width, height);//(0,0)開始畫
        snake.drawSnake(g);
        fruit.drawFruit(g);
    }

    // 設定視窗大小
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(width, height);
    }

    public static void main(String[] args) throws Exception{
        JFrame window = new JFrame("Snake Game");
        window.setContentPane(new Main());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false); //無法調整視窗大小
    }
}
