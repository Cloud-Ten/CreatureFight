import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
/**
 * Write a description of class Charmander here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pidgeot extends Creature
{
    private Creature enemy;
    private String enemyType;

    public Pidgeot( World w )
    {
        super(650, false, "Flying");
        getImage().scale(150, 100);
        w.addObject( getHealthBar(), 450, w.getHeight() - 680 );
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
            getWorld().showText( "Pidgeot has fainted...", getWorld().getWidth() / 2, getWorld().getHeight() / 2 + 26 );
            Greenfoot.delay(30);
            
            if(playerWorld.getNewTwoCreature(0).getHealthBar().getCurrent() > 0)
            {
                switchCreature(0);
                playerWorld.setTurn(false);
                getWorld().showText("", getWorld().getWidth() / 2, getWorld().getHeight() / 2 + 26);
                getWorld().removeObject(this);
            }
            else if(playerWorld.getNewTwoCreature(1).getHealthBar().getCurrent() > 0)
            {
                switchCreature(1);
                playerWorld.setTurn(false);
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
        
        enemy = world.getPlayerOne();
        enemyType = enemy.getType();
        
        attackAnimation();
        
        if( idx == 0 )
        {
            enemy.getHealthBar().add( -30 );
        }
        else
        {
            enemy.getHealthBar().add( -65 );
            
            if(enemyType.equalsIgnoreCase("Rock"))
            {
                enemy.getHealthBar().add( -55 / 2 );
                getWorld().showText("It not very effective...", getWorld().getWidth() / 2, getWorld().getHeight() / 2 + 26);
                Greenfoot.delay(30);
            }
            else if(enemyType.equalsIgnoreCase("Grass"))
            {
                enemy.getHealthBar().add( -55 * 2 );
                getWorld().showText("It super effective!", getWorld().getWidth() / 2, getWorld().getHeight() / 2 + 26);
                Greenfoot.delay(30);
            }
            else
            {
                enemy.getHealthBar().add( -55 );
            }
        }
        world.setTurn(true);
    }
    
    /**
     * attackAnimation will animate pidgeot by setting the location of it's image to a certain point
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
            setLocation(getX() - 1, getY() - 2);
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
            switchCreature = world.getNewTwoCreature(0);
        }
        else
        {
            switchCreature = world.getNewTwoCreature(1);
        }
        
        if(switchCreature.getHealthBar().getCurrent() <= 0)
        {
            JOptionPane.showMessageDialog( null, "This creature has fainted! Please select a different creature." );
        }
        else
        {
            while(getX() < getWorld().getWidth() - 1)
            {
                setLocation(getX() + 5, getY());
                Greenfoot.delay(2);
            }
            getImage().setTransparency(0);
            getHealthBar().getImage().setTransparency(0);
            
            if(idx == 0)
            {
                world.changePlayerTwo("Pikachu");
            }
            else
            {
                world.changePlayerTwo("Lapras");
            }
            
            switchCreature.switchedIn();
            world.setTurn(true);
        }
    }
    
    /**
     * switchedIn will move pidgeot in to a specific point in the world if the user
     * is switching to charmander from another creature
     * 
     * @param no parameters
     * @return nothing is returned
     */
    public void switchedIn()
    {
        getImage().setTransparency(255);
        getHealthBar().getImage().setTransparency(255);
        
        while(getX() > 675)
        {
            setLocation(getX() - 5, getY());
            Greenfoot.delay(2);
        }
    }
}
