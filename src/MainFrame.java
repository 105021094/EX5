import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

public class MainFrame extends JFrame {
    private int scrW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int scrH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW = 500, frmH = 450;
    private JMenuBar jmb = new JMenuBar();
    private JMenu jmFile = new JMenu("File");
    private JMenu jmSet = new JMenu("Set");
    private JMenu jmGame = new JMenu("Game");
    private JMenu jmAbout = new JMenu("About");
    private JMenuItem jmiExit = new JMenuItem("Exit");
    private JMenuItem jmiLoto = new JMenuItem("Loto");
    private JMenuItem jMenuItemAddCategory = new JMenuItem("Add Category");
    private JDesktopPane jdp = new JDesktopPane();

//20171031 Add Font-----------------------------------------------------------------------------------------------------
    private JMenuItem jmiSetFont = new JMenuItem("Font");
    private JPanel jpanel1 = new JPanel(new GridLayout(2,3,5,5));
    private JLabel jlbFontFamily =new JLabel("Family");
    private JLabel jlbFontStyle =new JLabel("Style");
    private JLabel jlbFontSize =new JLabel("Size");
    private JTextField jtfFamily = new JTextField("Times New Romans");
    // private JTextField jtfStyle = new JTextField("PLAIN");
    private JTextField jtfSize = new JTextField("23");
    private String[] options = {"PLAIN","BOLD","ITALIC","BOLD+ITALIC"};
    private JComboBox jcbFStyle = new JComboBox(options);

//20171031 Add Category-------------------------------------------------------------------------------------------------
    private  JInternalFrame jIfAddCategory = new JInternalFrame();
    private Container jIFAddCategoryCP;
    private JMenuBar jIFAddCategoryJmb = new JMenuBar();
    private JMenu jmData = new JMenu("Data");
    private JMenuItem jmiDataLoad = new JMenuItem("Load");
    private JMenuItem jmiDataNew = new JMenuItem("New");
    private JMenuItem jmiDataClose = new JMenuItem("Close");
    private JFileChooser jfc = new JFileChooser();
    private JTextArea jta = new JTextArea();
    private JScrollPane jsp1 = new JScrollPane(jta);


    private JInternalFrame jInternalFrame = new JInternalFrame();
    private Container jifCP;
    private JPanel jpn = new JPanel(new GridLayout(1,6,5,5));
    private JPanel jpn1 = new JPanel(new GridLayout(1,2,5,5));
    private JLabel jlbs[] = new JLabel[6];
    private int data[] = new int[6];
    private Random rnd = new Random(System.currentTimeMillis());
    private JButton jbtnColse = new JButton("Close");
    private JButton jbtnRegen = new JButton("Regen");
    private LoginFrame loginFrm;

    public MainFrame(LoginFrame frm){
        loginFrm = frm;
        initComp();
    }

    private void initComp(){
        this.setBounds(scrW/2-frmW/2,scrH/2-frmH/2,frmW,frmH);
        //this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setJMenuBar(jmb);
        this.setContentPane(jdp);


 //20171031 Add Font-------------------------------------------------------------------------------------------------------------

        jmSet.add(jmiSetFont);

        jpanel1.add(jlbFontFamily);
        jpanel1.add(jlbFontStyle);
        jpanel1.add(jlbFontSize);
        jpanel1.add(jtfFamily);
        jpanel1.add(jcbFStyle);
        jpanel1.add(jtfSize);

        this.setTitle("Main Frame");

        jmiSetFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(MainFrame.this,
                        jpanel1,
                        "Font setting",
                        JOptionPane.OK_CANCEL_OPTION);
                int fontStyle = 0;
                switch (jcbFStyle.getSelectedIndex()){
                    case 0:
                        fontStyle = Font.PLAIN;
                        break;
                    case 1:
                        fontStyle = Font.BOLD;
                        break;
                    case 2:
                        fontStyle = Font.ITALIC;
                        break;
                    case 3:
                        fontStyle = Font.BOLD + Font.ITALIC;
                        break;
                }
                if (result == JOptionPane.OK_CANCEL_OPTION){
                    UIManager.put("Menu.font",new Font(jtfFamily.getText(),fontStyle,
                            Integer.parseInt(jtfSize.getText())));
                    UIManager.put("MenuItem.font",new Font(jtfFamily.getText(),fontStyle,
                            Integer.parseInt(jtfSize.getText())));
                }
            }
        });

//20171031 Add Category-------------------------------------------------------------------------------------------------
        jMenuItemAddCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jIfAddCategory.setVisible(true);
            }
        });
        jIFAddCategoryCP = jIfAddCategory.getContentPane();
        jIFAddCategoryCP.setLayout(new BorderLayout(5,5));
        jIFAddCategoryCP.add(jsp1,BorderLayout.CENTER);
        jIfAddCategory.setJMenuBar(jIFAddCategoryJmb);
        jIfAddCategory.setBounds(0,0,500,500);
        jIfAddCategory.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jIFAddCategoryJmb.add(jmData);
        jIFAddCategoryJmb.add(jmiDataLoad);
        jIFAddCategoryJmb.add(jmiDataNew);
        jIFAddCategoryJmb.add(jmiDataClose);
        jdp.add(jIfAddCategory);
        jmiDataLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    try{
                        File inFile = jfc.getSelectedFile();
                        BufferedReader br = new BufferedReader(new FileReader(inFile));
                        System.out.println("File Name:"+ inFile.getName());
                        String str = "";
                        while ((str = br.readLine()) !=null){
                            jta.append(str + "\n");
                        }
                        System.out.println("Read file finished");
                    }catch (Exception ioe){
                        JOptionPane.showMessageDialog(null,"Open file error:"+ioe.toString());
                    }
                }
            }
        });



        jmiDataClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        jmb.add(jmFile);
        jmb.add(jmSet);
        jmb.add(jmGame);
        jmb.add(jmAbout);
        jmFile.add(jmiExit);
        jmFile.add(jmiLoto);
        jmFile.add(jMenuItemAddCategory);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loginFrm.reset();
                loginFrm.setVisible(true);
            }
        });

        jInternalFrame.setBounds(0,0,300,80);
        jifCP = jInternalFrame.getContentPane();
        jifCP.setLayout(new BorderLayout(5,5));
        jifCP.add(jpn,BorderLayout.CENTER);
        jifCP.add(jpn1,BorderLayout.SOUTH);
        jpn1.add(jbtnColse);
        jpn1.add(jbtnRegen);
        for (int i = 0;i<6;i++){
            jlbs[i] = new JLabel();
            jlbs[i].setFont(new Font(null,Font.PLAIN,22));
            jlbs[i].setBackground(Color.pink);
            jlbs[i].setHorizontalAlignment(JLabel.CENTER);
            jpn.add(jlbs[i]);
        }

        lotoGenerate();
        jbtnRegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lotoGenerate();
            }
        });
        jbtnColse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jInternalFrame.dispose();
            }
        });
        jmiLoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jInternalFrame);
                jInternalFrame.setVisible(true);
            }
        });

        jmiExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jmiExit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    }

    private void lotoGenerate(){
        int i = 0;
        while (i<6){
            data[i] = rnd.nextInt(42)+1;
            int j = 0;
            boolean flag = true;
            while (j<i && flag){
                if (data[i] == data[j]){
                    flag = false;
                }
                j++;
            }
            if (flag){
                jlbs[i].setText(Integer.toString(data[i]));
                i++;
            }
        }
    }
}
