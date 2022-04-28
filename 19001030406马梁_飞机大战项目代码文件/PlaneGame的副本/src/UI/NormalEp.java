package UI;

import java.util.Random;

//实际产品
public class NormalEp extends Ep
{
    public NormalEp()
    {
        Random rd=new Random();
        int index=rd.nextInt(15)+1;
        type=index;

        String path="/img/ep"+(index<10?"0":"")+index+".png";//随机获取飞机，1到15：[1,16);

        img=App.getImg(path);

        w=img.getWidth();
        h=img.getHeight();

        x=rd.nextInt(512-w);//在窗体大小-敌机宽度之前生成
        y=-h;
        sp=17-index;
        hp=index;
    }

    @Override
    public void move()
    {
        if(type==5)
        {
            x-=5;
            y+=sp;
        }else if(type==6)
        {
            x+=5;
            y+=sp;
        }
        //else if(type==12) {
        //x+=sp;
        //}

        y+=sp;
    }

    @Override
    public boolean shootBy(Fire f)
    {

        boolean hit =x<=f.x+f.w && x>=f.x-w && y<=f.y+f.h &&y>=f.y-h;

        return hit;
    }

    @Override
    public boolean hitBy(Hero f)
    {
        boolean hit =x<=f.x+f.w && x>=f.x-w && y<=f.y+f.h &&y>=f.y-h;
        return hit;
    }

}
