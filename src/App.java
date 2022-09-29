
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App implements ActionListener {

    Board board;
    JTextField textField;
    JButton button;
    JFrame frame;
    JCheckBox checkBox;

    public static void main(String[] args){
        //new App();


        
        TreeNode node = new TreeNode("^");
        node.left = new TreeNode("27");
        node.right = new TreeNode("/");
        node.right.left = new TreeNode("1");
        node.right.right = new TreeNode("3");

        try {
            System.out.println(node.evaluateTreeNode());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public App() throws Exception {
        frame = new JFrame("App");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);

        button = new JButton("Add");
        button.setBounds(10, 10, 100, 30);
        button.addActionListener(this);
        button.setActionCommand("AddButton");

        textField = new JTextField();
        textField.setBounds(10, 40, 100, 30);
        
        board = new Board(100,100,600,600);
        
        
        checkBox = new JCheckBox();
        checkBox.setBounds(110, 40, 30, 30);

        frame.add(button);
        frame.add(textField);
        frame.add(board);
        frame.add(checkBox);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getActionCommand().equals("AddButton")){
            board.addFunction(new Function(textField.getText(), checkBox.isSelected()));
            textField.setText("");
        }
        
    }

}
