import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.*;

public class falling_squares extends JPanel {
    static JPanel mainPanel;
    static JPanel lowerPanel;
    static JLabel score;

    static int windowWidth = 400;
    static int windowHeight = 400;

    static int missed = 0;
    static int squareWidth = 25;
    static int squareHeight = 25;
    static int[] squareYLocationA = {-squareHeight, -squareHeight, -squareHeight, -squareHeight};
    static double counter = 0;
    static double squaresRatio;
    static double squareCounter = 0;
    static boolean[] isClicked = new boolean[4];
    static boolean[] numberCreated = new boolean[4];
    static boolean gameRunning = false;
    static int[] tab = new int[4];

    public void generateRandomNumber() {
        Random rand = new Random();
        if (!numberCreated[0]) {
            tab[0] = rand.nextInt(windowWidth - squareWidth);
            isClicked[0] = false;
            numberCreated[0] = true;
            squareCounter++;
        }
        if (!numberCreated[1]) {
            tab[1] = rand.nextInt(windowWidth - squareWidth);
            isClicked[1] = false;
            numberCreated[1] = true;
            squareCounter++;
        }
        if (!numberCreated[2]) {
            tab[2] = rand.nextInt(windowWidth - squareWidth);
            isClicked[2] = false;
            numberCreated[2] = true;
            squareCounter++;
        }
        if (!numberCreated[3]) {
            tab[3] = rand.nextInt(windowWidth - squareWidth);
            isClicked[3] = false;
            numberCreated[3] = true;
            squareCounter++;
        }
    }


    public void paint(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(0, 0, windowWidth, windowHeight);
        g.setColor(Color.blue);
        
        for (int i = 0; i < tab.length; i++) {
            g.fillRect(tab[i], squareYLocationA[i], squareWidth, squareHeight);
        }

    }

    public void update() {
        for (int i = 0; i < squareYLocationA.length; i++) {
            if (!numberCreated[i]) {
                generateRandomNumber();
            }
            if (squareYLocationA[i] <= windowHeight) {
                squareYLocationA[i]++;
            } else {
                numberCreated[i] = false;
                squareYLocationA[i] = -squareHeight;
            }
        }
    }

    public void start() {
        gameRunning = true;
    }


    public static void main(String[] args) throws InterruptedException {

        class SquareClicker extends MouseAdapter {
            @Override
            public void mousePressed(MouseEvent e) {
                if ((e.getX() >= tab[0] && e.getX() <= tab[0] + 25) && (e.getY() >= squareYLocationA[0] && e.getY() <= squareYLocationA[0] + 25) && !isClicked[0]) {
                    isClicked[0] = true;

                    squareYLocationA[0] = 400;
                    counter++;
                    squaresRatio = counter / squareCounter * 100;
                    DecimalFormat myDF = new DecimalFormat("0");
                    score.setText("Current score: " + myDF.format(squaresRatio) + "%");
                } else {
                    missed++;
                }
                if ((e.getX() >= tab[1] && e.getX() <= tab[1] + 25) && (e.getY() >= squareYLocationA[1] && e.getY() <= squareYLocationA[1] + 25) && !isClicked[1]) {
                    isClicked[1] = true;

                    squareYLocationA[1] = 400;
                    counter++;
                    squaresRatio = counter / squareCounter * 100;
                    DecimalFormat myDF = new DecimalFormat("0");
                    score.setText("Current score: " + myDF.format(squaresRatio) + "%");
                } else {
                    missed++;
                }
                if ((e.getX() >= tab[2] && e.getX() <= tab[2] + 25) && (e.getY() >= squareYLocationA[2] && e.getY() <= squareYLocationA[2] + 25) && !isClicked[2]) {
                    isClicked[2] = true;

                    squareYLocationA[2] = 400;
                    counter++;
                    squaresRatio = counter / squareCounter * 100;
                    DecimalFormat myDF = new DecimalFormat("0");
                    score.setText("Current score: " + myDF.format(squaresRatio) + "%");
                } else {
                    missed++;
                }
                if ((e.getX() >= tab[3] && e.getX() <= tab[3] + 25) && (e.getY() >= squareYLocationA[3] && e.getY() <= squareYLocationA[3] + 25) && !isClicked[3]) {
                    isClicked[3] = true;

                    squareYLocationA[3] = 400;
                    counter++;
                    squaresRatio = counter / squareCounter * 100;
                    DecimalFormat myDF = new DecimalFormat("0");
                    score.setText("Current score: " + myDF.format(squaresRatio) + "%");
                } else {
                    missed++;
                }
            }
        }


        JFrame frame = new JFrame();
        mainPanel = new JPanel();
        lowerPanel = new JPanel();
        mainPanel.setBackground(Color.ORANGE);

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {

                        if (squaresRatio > 50.0) {
                            gameRunning = false;
                            JOptionPane.showMessageDialog(frame,"You won", "Result", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            gameRunning = false;
                            JOptionPane.showMessageDialog(frame,"You lost", "Result", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                },
                1000*60
        );

        lowerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Score"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        score = new JLabel();
        DecimalFormat myDF1 = new DecimalFormat("0");
        score.setText("Current score: " + myDF1.format(squaresRatio) + "%");
        score.setHorizontalAlignment(JLabel.CENTER);
        lowerPanel.add(score);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight);
        frame.setVisible(true);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.getContentPane().add(lowerPanel, BorderLayout.SOUTH);
        frame.getContentPane().addMouseListener(new SquareClicker());
        frame.setLocationRelativeTo(null);

        falling_squares game = new falling_squares();
        frame.add(game);
        game.start();

        while (gameRunning) {
            if (squareYLocationA[0] >= 400 && !isClicked[0]) {
                squaresRatio = counter/squareCounter*100;
                DecimalFormat myDF2 = new DecimalFormat("0");
                score.setText("Current score: " + myDF2.format(squaresRatio) + "%");
            }
            if (squareYLocationA[1] >= 400 && !isClicked[1]) {
                squaresRatio = counter/squareCounter*100;
                DecimalFormat myDF2 = new DecimalFormat("0");
                score.setText("Current score: " + myDF2.format(squaresRatio) + "%");
            }
            if (squareYLocationA[2] >= 400 && !isClicked[2]) {
                squaresRatio = counter/squareCounter*100;
                DecimalFormat myDF2 = new DecimalFormat("0");
                score.setText("Current score: " + myDF2.format(squaresRatio) + "%");
            }
            if (squareYLocationA[3] >= 400 && !isClicked[3]) {
                squaresRatio = counter/squareCounter*100;
                DecimalFormat myDF2 = new DecimalFormat("0");
                score.setText("Current score: " + myDF2.format(squaresRatio) + "%");
            }

            game.update();
            game.repaint();
            Thread.sleep(10);
        }
    }
}
