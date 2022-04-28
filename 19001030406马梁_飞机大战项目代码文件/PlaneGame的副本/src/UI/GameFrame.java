package UI;
//游戏的窗体

import javax.swing.JFrame;
import javax.swing.*;

public class GameFrame extends JFrame{
	public GameFrame()
	{
		setTitle("飞机大战");
		setSize(512,768);

		//设置位置居中，null代表相对于屏幕的左上角
		setLocationRelativeTo(null);

		//不允许改变界面大小
		setResizable(false);

		//关闭窗体的时候退出程序
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	public static void main(String[] args)
	{
		//1。 通过Frame的构造方法制作框架/窗体
		GameFrame frame=new GameFrame();

		//2。 通过面板的构造方法制作面板
		//3。 添加键盘监听器
		//这里可以类似装饰者模式
		GamePanel panel=new GamePanel(frame);

		//调用方法，启动线程
		panel.hero.GenerateHero();
		panel.action();
		panel.action1();

		//将面板加到窗体中
		frame.add(panel);
		
		frame.setVisible(true);
		
	
	}
}
