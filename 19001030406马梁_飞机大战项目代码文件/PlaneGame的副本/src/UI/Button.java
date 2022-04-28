package UI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Button extends JFrame
{
	JButton b1,b2,b3;
	int x,y,x1,y1;
	
	public Button(int x,int y,int x1,int y1,String a,String b,String c)
	{
		
		setSize(x,y);//300,200;
		setLocation(x1,y1);
		
		
		//setLayout(new GridLayout(3,3));
	}
	//public void reset(boolean pause) {
		
		
		//if(pause==true) {
		//	this.setVisible(true);
			
		//}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}
