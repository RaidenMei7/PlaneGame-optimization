package UI;

public abstract class Fire extends FlyObject
{

private AttackBehavior attackBehavior;
	public abstract void move();




	public void setAttackBehavior(AttackBehavior attackBehavior)
		{
			this.attackBehavior=attackBehavior;
		}

	public int doAttack()
	{
		if(attackBehavior!=null)
		{
			return attackBehavior.attack();
		}
		else return 1;
	}


}
