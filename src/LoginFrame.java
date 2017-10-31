import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{
    private JLabel jLableID = new JLabel("ID:");
    private JLabel jLablePW = new JLabel("PassWord:");
    private JTextField jtfID = new JTextField();
    private JPasswordField jtfPW = new JPasswordField();
    private JButton jbtExit = new JButton("Exit");
    private JButton jbtLogin = new JButton("Login");

    private Container cp;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int frmW = 300, frmH = 150, screenW, screenH;
    public LoginFrame(){
        initComp();
    }
    public void initComp(){
        screenW = dim.width;
        screenH = dim.height;
        this.setBounds(screenW/2-frmW/2,screenH/2-frmH/2,frmW,frmH);
        cp = this.getContentPane();
        cp.setLayout(new GridLayout(3,2,3,3));
        jLableID.setHorizontalAlignment(JLabel.RIGHT);
        jLablePW.setHorizontalAlignment(JLabel.RIGHT);
        jbtLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtfID.getText().equals("h304") && (new String(jtfPW.getPassword())).equals("23323456")) {
                    //MainFrame mainFrame = new MainFrame();
                    MainFrame mFrm = new MainFrame(LoginFrame.this);
                    mFrm.setVisible(true);

                    LoginFrame.this.setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(LoginFrame.this,"Error");
                    System.out.println("No"+jtfID.getText()+" \tpw");
                }
            }
        });
        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        cp.add(jLableID);
        cp.add(jtfID);
        cp.add(jLablePW);
        cp.add(jtfPW);
        cp.add(jbtExit);
        cp.add(jbtLogin);
    }
    public void reset(){
        jtfID.setText("");
        jtfPW.setText("");
    }

}
