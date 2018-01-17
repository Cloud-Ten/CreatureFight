import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
/**
 * Write a description of class Charmander here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Golem extends Creature
{
    private Creature enemy;
    private String enemyType;
    
    public Golem( World w )
    {
        super(700, true, "Rock");
        getImage().scale(150, 100);
        w.addObject( getHealthBar(), 300, w.getHeight() - 50 );
        getHealthBar().getImage().setTransparency(0);
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
            getWorld().showText("Golem has fainted…", getWorld().getWidth() / 2, getWorld().getHeight() / 2 + 26);
            Greenfoot.delay(30);
            
            if(playerWorld.getNewOneCreature(0).getHealthBar().getCurrent() > 0)
            {
                switchCreature(0);
                playerWorld.setTurn(true);
                
                getWorld().showText("", getWorld().getWidth() / 2, getWorld().getHeight() / 2 + 26);
                getWorld().removeObject(this);
            }
            else if(playerWorld.getNewOneCreature(2).getHealthBar().getCurrent() > 0)
            {
                switchCreature(1);
                playerWorld.setTurn(true);
                
                getWorld().showText("", getWorld().getWidth() / 2, getWorld().getHeight() / 2 + 26);
                getWorld().removeObject(this);
            }

        }
    }    
    
    /**
     * attack changes the amount of health in the enemy health bar under a certain set of conditions
     * 
     * @param a variable that gets changed whenever the user clicks a button in the menu
     * @return nothing is returned
     */
    public void attack( int idx )
    {
        CreatureWorld world = (CreatureWorld)getWorld();
        
        enemy = world.getPlayerTwo();
        enemyType = enemy.getType();
        attackAnimation();
        
        if( idx == 0 )
        {
            enemy.getHealthBar().add( -30 );
        }
        else
        {
            if(enemyType.equalsIgnoreCase("Electric"))
            {
                enemy.getHealthBar().add( -80 * 2 );
                getWorld().showText("It's super effective!", getWorld().getWidth() / 2, getWorld().getHeight() / 2 + 26);
                Greenfoot.delay(30);
            }
            else if(enemyType.equalsIgnoreCase("Flying"))
            {
                enemy.getHealthBar().add( 0 );
                getWorld().showText("It has no effect", getWorld().getWidth() / 2, getWorld().getHeight() / 2 + 26);
                Greenfoot.delay(30);
            }
            else
            {
                enemy.getHealthBar().add(-80);
            }
        }
        world.setTurn(false);
    }
    
    /**
     * attackAnimation will animate golem by setting the location of it's image to a certain point
     * 
     * @param no parameters
     * @return nothing is returned
     */
    private void attackAnimation()
    {
        int originalX = getX();
        int originalY = getY();
        
        for(int i = 0; i < 16; i++)
        {
            setLocation(getX() + 1, getY() - 2);
            Greenfoot.delay(1);
        }
        
        setLocation(originalX, originalY);
    }
    
    /**
     * switchCreature switches out this current creature to another creature of the user's choice
     * 
     * @param a variable that gets changed when the user clicks a button in the menu
     * @return nothing is returned
     */
    public void switchCreature( int idx )
    {
        CreatureWorld world = (CreatureWorld)getWorld();
        Creature switchCreature;
        
        if(idx == 0)
        {
            switchCreature = world.getNewOneCreature(0);
        }
        else
        {
            switchCreature = world.getNewOneCreature(2);
        }
        
        if(switchCreature.getHealthBar().getCurrent() <= 0)
        {
            JOptionPane.showMessageDialog( null, "This creature has fainted! Please select a different creature." );
        }
        else
        {
            while(getX() > 0)
            {
                setLocation(getX() - 5, getY());
                Greenfoot.delay(2);
            }
            getImage().setTransparency(0);
            getHealthBar().getImage().setTransparency(0);
            
            if(idx == 0)
            {
                world.changePlayerOne("Charmander");
            }
            else
            {
                world.changePlayerOne("Ivysaur");
            }
            
            switchCreature.switchedIn();
            world.setTurn(false);
        }
    }
    
    /**
     * switchedIn will move golem in to a specific point in the world if the user
     * is switching to charmander from another creature
     * 
     * @param no parameters
     * @return nothing is returned
     */
    public void switchedIn()
    {
        getImage().setTransparency(255);
        getHealthBar().getImage().setTransparency(255);
        
        while(getX() < 75)
        {
            setLocation(getX() + 5, getY());
            Greenfoot.delay(2);
        }
    }
}
