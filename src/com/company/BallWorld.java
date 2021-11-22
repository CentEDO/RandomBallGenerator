package com.company;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
/**
 * The control logic and main display panel for game.
 *
 * @author Hock-Chuan Chua
 * @version October 2010
 * modified by CentEDO
 */
public class BallWorld extends JPanel {
    private static final int UPDATE_RATE = 60;    // Frames per second (fps)
    private static Random generator = new Random();
    private static int ballCount = generator.nextInt(50);
    private Ball balls[] = new Ball[ballCount];
    private ContainerBox box;  // The container rectangular box
    private DrawCanvas canvas; // Custom canvas for drawing the box/ball
    private int canvasWidth;
    private int canvasHeight;
    private int i;

    /**
     * Constructor to create the UI components and init the game objects.
     * Set the drawing canvas to fill the screen (given its width and height).
     *
     * @param width : screen width
     * @param height : screen height
     */
    public BallWorld(int width, int height) {

        canvasWidth = width;
        canvasHeight = height;
       /* Random rand = new Random();
        int radius = 20;
        int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
        int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
        int speed = 5;
        int angleInDegree = rand.nextInt(360);*/

        for(i = 0;i<ballCount;i++){
            Random randomShape = new Random();
            Random randomSpeed = new Random();
            Random rand = new Random();
            int speed = randomSpeed.nextInt(ballCount);
            int shape = randomShape.nextInt(3);
            int radius = 10;
            int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
            int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
            int angleInDegree = rand.nextInt(360);

            switch (shape){
                case 1:
                    Ball ball = new Circle(x,y,radius,speed,angleInDegree,Color.cyan);
                    balls[i]= ball;
                    break;
                case 2:
                    Ball ball1 = new Square(x,y,radius,speed,angleInDegree,Color.MAGENTA);
                    balls[i]= ball1;
                    break;

                default:
                    Ball ball2 = new Ellipse(x,y,radius,speed,angleInDegree,Color.YELLOW);
                    balls[i]= ball2;
                    break;
            }

        }


//
        // Init the Container Box to fill the screen
        box = new ContainerBox(0, 0, canvasWidth, canvasHeight, Color.gray, Color.WHITE);

        // Init the custom drawing panel for drawing the game
        canvas = new DrawCanvas();
        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);

        // Handling window resize.
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component c = (Component)e.getSource();
                Dimension dim = c.getSize();
                canvasWidth = dim.width;
                canvasHeight = dim.height;
                // Adjust the bounds of the container to fill the window
                box.set(0, 0, canvasWidth, canvasHeight);
            }
        });

        // Start the ball bouncing
        gameStart();
    }
    /*
    public void gameStart(){
        gameThread gmthr = new gameThread(this,UPDATE_RATE);
        gmthr.start();

    }
    */

    // Start the ball bouncing.
    public void gameStart() {
        // Run the game logic in its own thread.
        Thread gameThread = new Thread() {
            public void run() {
                while (true) {
                    // Execute one time-step for the game
                    gameUpdate();
                    // Refresh the display
                    repaint();
                    // Delay and give other thread a chance
                    try {
                        Thread.sleep(1000 / UPDATE_RATE);
                    } catch (InterruptedException ex) {}
                }
            }
        };
        gameThread.start();  // Invoke GaemThread.run()
    }


    /**
     * One game time-step.
     * Update the game objects, with proper collision detection and response.
     */
    public void gameUpdate() {
        for ( i = 0; i<ballCount;i++){
            Ball ball = balls[i];
            ball.moveOneStepWithCollisionDetection(box);
        }
    }

    /** The custom drawing panel for the bouncing ball (inner class). */
    class DrawCanvas extends JPanel {
        /** Custom drawing codes */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);    // Paint background
            // Draw the box and the ball
            box.draw(g);
            for (i = 0; i<ballCount;i++) {
                Ball ball = balls[i];
                ball.draw(g);
            }

        }

        /** Called back to get the preferred size of the component. */
        @Override
        public Dimension getPreferredSize() {
            return (new Dimension(canvasWidth, canvasHeight));
        }
    }
}
