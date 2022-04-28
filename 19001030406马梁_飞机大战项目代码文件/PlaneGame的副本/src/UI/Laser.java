package UI;

public class Laser extends Fire
{
    public Laser(int hx,int hy)
    {
        img=App.getImg("/img/fire.png");
        w=img.getWidth()/4;
        h=img.getHeight()/4;
        x=hx;
        y=hy;

    }

    public void move()
    {
        y-=20;
    }



}
