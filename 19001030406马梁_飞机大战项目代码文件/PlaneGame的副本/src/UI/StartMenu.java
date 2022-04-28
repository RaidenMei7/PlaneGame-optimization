package UI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;


public class StartMenu extends JFrame
        implements ActionListener, ItemListener
{

    private JButton jButton1;
    private JLabel jLabel1;
    private JRadioButton rb1 = new JRadioButton("简单",false);
    private JRadioButton rb2 = new JRadioButton("普通",false);
    private JRadioButton rb3 = new JRadioButton("困难",false);
    private ButtonGroup group = new ButtonGroup();
    private JLabel b = new JLabel();
    boolean choose=false;
    private JFrame jf=new JFrame("难度选择");
    
    public static void main(String[] args)
    {
        StartMenu frame = new StartMenu();
    }

    public StartMenu()
    {
    	setSize(512,768);
		setLocationRelativeTo(null);
    	ImageIcon bg=new ImageIcon("src/Img/von.jpg");
		JLabel label=new JLabel(bg);
		label.setSize(bg.getIconWidth(),bg.getIconHeight());
		jf.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
		//2.把窗口面板设为内容面板并设为透明、流动布局。
		JPanel pan=(JPanel)jf.getContentPane();
		pan.setOpaque(false);
		pan.setLayout(new FlowLayout(FlowLayout.CENTER,20,200));
		//3.之后把组件和面板添加到窗口面板就可以；
		jf.setSize(bg.getIconWidth(),bg.getIconHeight());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
        setTitle("难度选择");
        group.add(rb1);
        group.add(rb2);
        group.add(rb3);
        jLabel1 = new JLabel("请选择游戏难度：");
        jButton1=new JButton("开始游戏");
        jButton1.setFont(new Font("楷体",Font.BOLD,25));
        jButton1.setToolTipText("开始游戏");
        jLabel1.setFont(new Font("楷体",Font.BOLD,25)); 
        jLabel1.setForeground(Color.white);
        pan.add(jLabel1);
        pan.add(rb1);
        pan.add(rb2);
        pan.add(rb3);
        pan.add(jButton1);

        if(choose==false) {
        	jButton1.setVisible(false);
        }

        rb1.addItemListener(this);
        rb2.addItemListener(this);
        rb3.addItemListener(this);
        jButton1.addActionListener(this);
    }
    
    public void itemStateChanged(ItemEvent ie){
        if (rb1.isSelected()){
           GamePanel.level=30;
           jButton1.setVisible(true);
        }
        if (rb2.isSelected()){
            GamePanel.level=20;
            jButton1.setVisible(true);
        }
        if (rb3.isSelected()){
            GamePanel.level=5;
            jButton1.setVisible(true);
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==jButton1){
        	GameFrame.main(null);
        }
    }
}

