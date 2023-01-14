import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JComponent implements ActionListener {
    static int winW = 710;
    static int winH = 480;
    Timer timer = new Timer(1, this);
    Car bmw = new Car(70,210,"БМВ","src/БМВ.png");
    Car mersedes = new Car(70,210,"Мерседес","src/Мерседес.png");
    Car lada = new Car(70,210,"Жигули","src/имба.jpg");
    Car hunday = new Car(70,210,"Хундай","src/хундай.jpg");
    Car jaguar = new Car(70,210,"Ягуар","src/Ягуар.png");
    String checkWin = "";

    public static void main(String[] args) {
        Main t = new Main();

        JFrame frame = new JFrame("Гоночки");
        frame.setSize(winW, winH);
        frame.setLocation(334, 34);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.add(t);
        frame.setVisible(true);
    }

    public synchronized void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Car.setG2(g2d);
        Car.setWinW(winW);
        g2d.setBackground(Color.WHITE);
        checkWin = bmw.moveCar();
        checkWin = mersedes.moveCar();
        checkWin = lada.moveCar();
        checkWin = hunday.moveCar();
        checkWin = jaguar.moveCar();
        if (!checkWin.equals("")) {
            g2d.drawString(checkWin, 200,200);
        }
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (checkWin.equals(""))
            repaint();
    }
}

class Car extends JComponent {
    static int winW;
    int x = 0;
    static int count = 0;
    int y = 20;
    int h;
    int w;
    Image carImage;
    static Graphics2D g2;
    String name;
    static String win = "";
    static boolean isWin = false;

    public static void setWinW(int winW) {
        Car.winW = winW;
    }

    public static void setG2(Graphics2D g2) {
        Car.g2 = g2;
    }

    Car(int h, int w, String name, String imagePath) {
        this.carImage = new ImageIcon(imagePath).getImage();
        this.h = h;
        this.w = w;
        this.name = name;
        y += (count++)*80;
    }

    public void paintCar() {
        g2.drawImage(carImage, x,y,w,h,null);
    }

    public void carPosition() {
        if (!isWin) {
            x += (int) (Math.random() * 4);
            if (x >= winW - w) {
                win = name + " выигрывает гонку!";
                isWin = true;
            }
        }
    }


    public String moveCar() {
        carPosition();
        paintCar();
        return win;
    }
}