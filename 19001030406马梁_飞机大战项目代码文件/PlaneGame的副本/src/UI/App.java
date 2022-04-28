package UI;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


//处理图片的工具类
public class App
{

	public static BufferedImage getImg(String path)
	{
		
		try
		{
				BufferedImage img=ImageIO.read(App.class.getResource(path));
				return img;
	
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
			return null;
	}


}
