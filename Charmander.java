import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Charmander here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Charmander extends Creature
{
    private Creature enemy;
    private String enemyType;
    
    public Charmander( World w )
    {
        super(700, 1, "Fire");
        getImage().scale(150, 100);
        w.addObject( getHealthBar(), 300, w.getHeight() - 50 );
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
            getWorld().showText("Charmander has fainted…", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
            Greenfoot.delay(100);
        }
    }    
    
    public void attack( int idx )
    {
        CreatureWorld world = (CreatureWorld)getWorld();
        
        enemy = world.getPlayerTwo();
        enemyType = enemy.getType();
        
        if( idx == 0 )
        {
            enemy.getHealthBar().add( -25 );
        }
        else
        {
            enemy.getHealthBar().add( -70 );
        }
        world.setTurnNumber(2);
    }
}
