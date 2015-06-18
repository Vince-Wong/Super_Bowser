package game_logic;

public abstract class Mob extends Character
{
   private int damage;
   
   public void setDamage(int dam) { damage = dam; }
   public int getDamage() { return damage; }
   
   public abstract void move();
}
