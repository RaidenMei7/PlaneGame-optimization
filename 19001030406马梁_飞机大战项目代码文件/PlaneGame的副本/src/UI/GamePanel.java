package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

//游戏面板部分

public class GamePanel extends JPanel
{

	//背景图
	//用观察者模式也可以换图片，比如积分到达什么情况后，update
	BufferedImage bg;

	//cautious：可以采用单例模式
	Hero hero = Hero.getInstance();

	//工厂模式优化处
	Ep e;
	EpCreator epCreator;





	
	
	
	//这个只是大本营，只是一个空间，并没有实际填充，填充的在其他地方
	List<Ep>eps=new ArrayList<Ep>();
	
	List<Fire>fs=new ArrayList<Fire>();
	
	int score;
	boolean gameover;
	int power=1;
	
	Button st;








	
	
	public void action()
	{
		//new Thread(){public void run() {...线程要做到事情...}}.start();
		//创建并启动一个线程，控制游戏场景中的物体具体移动
		//需要让线程去做某件事时，将具体代码写在run里面

		new Thread() {
			public void run()
			{
				//为了让线程能一直运行下去，写个死循环，到不玩的时break
	               while(true)
				   {
	            	   if(!gameover)
					   {
	            	   		   epEnter();
							   repaint();//
	            	 	       epMove();
	            	   }
	            	   
	            	   try
					   {
							Thread.sleep(100);
						}
					   catch (InterruptedException e)
					   {
						// TODO Auto-generated catch block
						e.printStackTrace();
					   }
				   }
			}
			
		}.start();
}
	public void action1()
	{
		new Thread()
		{
			public void run()
			{
				while(true)
				{
					if(!gameover)
					{
						shoot();
						repaint();
						fireMove();
						shootEp();
						hit();
					}
					 try
					 {
						Thread.sleep(10);
					 }
					 catch (InterruptedException e)
					 {
						// TODO Auto-generated catch block
						e.printStackTrace();
					 }
				}
			}
			
			
		}.start();
	}
	
	
	
	protected void hit()
	{
		for(int i=0;i<eps.size();i++)
		{
			Ep e=eps.get(i);
			if(e.hitBy(hero))
			{
				eps.remove(e);
				hero.hp--;
				power=1;
			}
			if(hero.hp<=0)
			{
				gameover=true;
			}
		}
		
	}
	protected void shootEp()
	{
		for(int i=0;i<fs.size();i++)
		{
			Fire f=fs.get(i);
			bang(f);
		}
		
		
	}
	
	private void bang(Fire f)
	{
		for(int i=0;i<eps.size();i++)
		{
			Ep e=eps.get(i);
			if(e.shootBy(f))//如果敌机被子弹击中
			{

				//大型判断，如果这个子弹用了策略1，则什么效果

				//普通子弹
				if(f.doAttack()==1)
				{
					e.hp--;
					if(e.hp<=0)
					{
						if(e.type==12)
						{
							power++;
							if(power>=3)
							{
								hero.hp++;
								power=3;
							}

						}
						eps.remove(e);
						score+=10;
					}
					fs.remove(f);

				}
				if(f.doAttack()==2)
				{
					e.hp=0;
					eps.remove(e);
					score+=10;
					fs.remove(f);
				}



				
			}
		}
	}
		
	
	protected void fireMove()
	{
		for(int i=0;i<fs.size();i++)
		{
			Fire f=fs.get(i);
			f.move();
		}
		
	}



	int findex=0;
	protected void shoot()
	{
		findex++;
		if(findex>=20)
		{
				if(power==1)
				{
					Fire fire1=new Laser(hero.x+43,hero.y);//子弹火力为1的时候
					fs.add(fire1);
				}
				else if(power==2)
				{
					Fire fire3=new Laser(hero.x+75,hero.y);//子弹火力为2的时候
					fs.add(fire3);
					Fire fire2=new Laser(hero.x+15,hero.y);
					fs.add(fire2);
				}
				else if(power==3)
				{
					Fire fire1=new Laser(hero.x+43,hero.y);//子弹火力为3的时候

					//将中间的变为秒杀子弹
					//成功
					fire1.setAttackBehavior(new seckill());

					fs.add(fire1);
					Fire fire2=new Laser(hero.x+15,hero.y);
					fs.add(fire2);
					Fire fire3=new Laser(hero.x+75,hero.y);
					fs.add(fire3);
				}
				findex=0;

			}




	}



	protected void epMove()
	{
		// TODO Auto-generated method stub
		for(int i=0;i<eps.size();i++)
		{
			Ep e=eps.get(i);
			e.move();
		}
			
		
	}

//敌机入场的方法
	//写在线程的死循环中，会一直生成敌机

	//通过修改level的数值，可以设置敌机生成的速度，如果，配合线程；
	//这个是限制了生成的数量
	//线程那个就影响很多，也影响到了移动的速度等
	public static int index =0,level=15;
	
	protected void epEnter()
	{
		// TODO Auto-generated method stub
		index++;
		if(index>=level)
		{
			//System.out.println(level);
			//这里得到敌机
			epCreator = new NormalEpCreator();
			e = epCreator.getEp();
			eps.add(e);
			index=0;
		}
	}


	public GamePanel(GameFrame frame)
	{
		setBackground(Color.pink);
		bg=App.getImg("/Img/bg1.jpg");
		
		
		//使用步骤：
		//1 创建鼠标适配器
		//2 确定需要监听的鼠标事件
		//3 将适配器加到监听器中


		MouseAdapter adapter=new MouseAdapter()
		{
			//mouseMoved
			
			//mousePressed();鼠标按下去的事件
			//mouseEntered();鼠标移入游戏界面
			//mouseExited();鼠标移出游戏界面
		
			//
            
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(gameover)
				{
					Hero hero = Hero.getInstance();
					hero.GenerateHero();
					eps=new ArrayList<Ep>();//,eps.clear();
					fs.clear();
					gameover=false;
					score=0;
					Random rd=new Random();
					int index=rd.nextInt(5)+1;
					bg=App.getImg("/Img/bg"+index+".jpg"); 
					repaint();
					
				}
				
			}

			
			public void mouseMoved(MouseEvent e)
			 {
			
				int mx=e.getX();
				int my=e.getY();

				if(!gameover)
				{
					hero.moveToMouse(mx, my);
					repaint();
				}
			}
			
			
		};

		addMouseListener(adapter);
		addMouseMotionListener(adapter);
		
		
		
		KeyAdapter kd=new KeyAdapter()
		{
		
			
		public void keyPressed(KeyEvent e) {

			//每一个键都对应一个一个数字
			//获取键盘上按键的数字
			int keyCode=e.getKeyCode();
			if (keyCode==KeyEvent.VK_UP) {hero.moveUP();}
			else if(keyCode==KeyEvent.VK_DOWN) {hero.moveDown();}
			else if(keyCode==KeyEvent.VK_LEFT) {hero.moveLeft();}
			else if(keyCode==KeyEvent.VK_RIGHT) {hero.moveRight();}
			else if(keyCode==KeyEvent.VK_ESCAPE) {}
			
			repaint();
		}
			
		};
		//键盘监听器要加到窗体上
		frame.addKeyListener(kd);
		
		
	}

	@Override
	public void paint(Graphics g)
	{
		// TODO Auto-generated method stub
		super.paint(g);

		//画图片，一般都需要3个步骤，图片，横坐标，纵坐标,null

		//敌机和英雄机画的顺序决定了谁会把谁掩盖掉；
		//后画的会把先画的掩盖掉；无论什么也都是这样，所以要先画背景，再画飞机


		//其实还可以增加图片都宽度，高度，默认不写都话就是原图都大小
		//如果想要改变图片都大小的话，应该这样(图片，图片的横坐标，图片的纵坐标，图片的宽度，图片的高度，null);

		//设置图片左上角坐标，所以一开始位置为0，0，填满

		//画背景图
		g.drawImage(bg, 0, 0, null);







		//画英雄机的图片
		g.drawImage(hero.img,hero.x,hero.y,hero.w,hero.h,null);

		//敌机的生成
		for(int i=0;i<eps.size();i++)
		 {
			//从集合中得到敌机
			Ep ep=eps.get(i);
			g.drawImage(ep.img,ep.x,ep.y,ep.w,ep.h,null);
		}

		//子弹的生成
		for(int i=0;i<fs.size();i++)
		{
			Fire fire=fs.get(i);
			g.drawImage(fire.img, fire.x, fire.y, fire.w, fire.h, null);
		}


		//分数
		g.setColor(Color.white);
		g.setFont(new Font("楷体",Font.BOLD,20));
		g.drawString("分数"+score,10,30);

		//画英雄机的血量
		for(int i=0;i<hero.hp;i++)
		{
			g.drawImage(hero.img, 455-35*i, 5, 30, 30, null);
			
		}


  		//游戏结束时
		if(gameover)
		{
		g.setColor(Color.red);
		g.setFont(new Font("楷体",Font.BOLD,35));
		g.drawString("Game Over",170,300);

		g.setColor(Color.green);
		g.setFont(new Font("楷体",Font.BOLD,25));
		g.drawString("请点击刷新重开",170,350);
		
		}	
		
		
	}
	

	
	


}
