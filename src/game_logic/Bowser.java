package game_logic;

public class Bowser extends Character
{
   public void onCollision(Mob enemy) {
      //TODO
   }
   
   public void onCollision(Item thing) {
      //TODO
   }
   
   public void onCollision(Door entrance) {
      //TODO
   }

   @Override
   public void onCollision(Entity ent)
   {
      // TODO Auto-generated method stub
      
   }
}

//TODO Classes not made yet, declared as non-public class here to avoid red lines 
class Mob {}

class Item {}

class Door {}