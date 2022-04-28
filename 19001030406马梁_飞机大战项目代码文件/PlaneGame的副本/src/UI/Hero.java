package UI;

import java.awt.image.BufferedImage;

public class Hero extends FlyObject
{
	private static Hero instance = null;


	int hp;


	
	private Hero()
	{


	}

	public static Hero getInstance()
	{
		if(instance==null)
		{
			synchronized (Hero.class)
			{
				if(instance==null)
				{
					instance = new Hero();
				}
			}

		}
		return instance;

	}

	public void GenerateHero()
	{
		img=App.getImg("/Img/hero.png");
		x=200;
		y=500;
		w=img.getWidth();
		h=img.getHeight();
		hp=3;

	}

	public void moveToMouse(int mx,int my)
	{
		x=mx-w/2;
		y=my-h/2;
	}
	
	public void moveUP()
	{
		y-=10;
	}
	public void moveDown()
	{
		y+=10;
	}
	public void moveLeft()
	{
		x-=10;
	}
	public void moveRight()
	{
		x+=10;
	}




}
