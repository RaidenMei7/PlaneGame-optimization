package UI;

import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class Ep extends FlyObject
 {
		int sp;
		int hp;
		int type;

	 
		// TODO Auto-generated method stub
		
		



		public abstract void move();


		public abstract boolean shootBy(Fire f);


		public abstract boolean hitBy(Hero f);

}
