package UI;

//无敌版本，将敌机一下秒杀的情况
//策略模式，接口是一种行为的抽象

public class seckill implements AttackBehavior
{


    @Override
    public int attack()
    {
        return 2;

    }
}
