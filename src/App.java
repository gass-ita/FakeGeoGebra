
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App implements ActionListener {

    Board board;
    JTextField textField;

    public static void main(String[] args)  throws Exception  {
        new App();
    }

    public App() throws Exception {
        JFrame frame = new JFrame("App");
        JButton button = new JButton("Add");
        textField = new JTextField();
        textField.setBounds(10, 40, 100, 30);
        button.setBounds(10, 10, 100, 30);
        
        button.addActionListener(this);
        button.setActionCommand("AddButton");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setLocation(500, 500);
        board = new Board(100,100,600,600, new Function[]{
            

        });
        frame.add(button);
        frame.add(textField);
        frame.add(board);

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getActionCommand().equals("AddButton")){
            board.addFunction(new Function(textField.getText()));
            textField.setText("");
        }
        
    }

}
