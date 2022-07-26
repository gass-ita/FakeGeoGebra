
import javax.swing.JFrame;


public class App {

    public static void main(String[] args) throws Exception {
        new App();
    }

    public App() throws Exception {
        JFrame frame = new JFrame("App");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setLocation(500, 500);
        Board board = new Board(100,100,600,600, new Function[]{
            new Function("sin(x)"),
            new Function("cos(x)"),
            new Function("tan(x)"),
            new Function("sqrt(x)"),
            new Function("(x)^2")
        });
        frame.add(board);
    }

}
