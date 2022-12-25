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
    private static String direction;
    private int speed = 100;

    public Main(){
        snake = new Snake();//創建蛇
        fruit = new Fruit();//創建果實
        t = new Timer();    //刷新時間
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        },0, speed);
        direction = "Right";
    }

    @Override
    public void paintComponent(Graphics g){
        //System.out.println("We are calling paint component..."); 確認timer有在運作
        //draw a black background
        g.fillRect(0, 0, width, height);//(0,0)開始畫
        snake.drawSnake(g);
        fruit.drawFruit(g);

        //remove snake tail ant put in  head
        int snakeX = snake.getSnakeBody().get(0).x;
        int snakeY = snake.getSnakeBody().get(0).y;
        // right, x += CELL_SIZE
        // left, x -= CELL_SIZE
        // down, y += CELL_SIZE
        // up, y -= CELL_SIZE
        if (direction.equals("Left")){
            snakeX -= CELL_SIZE;
        } else if (direction.equals("Up")) {
            snakeY -= CELL_SIZE;
        }else if (direction.equals("Right")){
            snakeX += CELL_SIZE;
        }else if (direction.equals("Down")){
            snakeY += CELL_SIZE;
        }
        Node newHead = new Node(snakeX,snakeY); // 新的頭
        snake.getSnakeBody().remove(snake.getSnakeBody().size()-1);//刪除尾巴
        snake.getSnakeBody().add(0,newHead);
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
