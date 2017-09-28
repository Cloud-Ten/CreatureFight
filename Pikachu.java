import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Charmander here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pikachu extends Creature
{
    /**
     * method will scale pikachu down to a smaller size and adds the healthbar.
     */
    public Pikachu( World w )
    {
        super(700, 2);
        getImage().scale(150, 100);
        w.addObject( getHealthBar(), 450, w.getHeight() - 680 );
    }
    
    /**
     * Act - do whatever the Charmander wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        CreatureWorld playerWorld = (CreatureWorld)getWorld();
        if( getHealthBar().getCurrent() <= 0)
        {
            getWorld().showText("Pikachu has fainted...", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
            Greenfoot.delay(100);
        }
    }    
}
