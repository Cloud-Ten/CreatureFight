import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
/**
 * Write a description of class Charmander here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ivysaur extends Creature
{
    private Creature enemy;
    private String enemyType;
    
    public Ivysaur( World w )
    {
        super(700, true, "Grass");
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
            getWorld().showText("Charmander has fainted…", getWorld().getWidth() / 2, getWorld().getHeight() / 2 + 26);
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
            enemy.getHealthBar().add( -70 );
            if(enemyType.equalsIgnoreCase("Electric"))
            {
                enemy.getHealthBar().add( -60 / 2 );
                getWorld().showText("It's not very effective...", getWorld().getWidth() / 2, getWorld().getHeight() / 2 + 26);
                Greenfoot.delay(30);
            }
            else if(enemyType.equalsIgnoreCase("Flying"))
            {
                enemy.getHealthBar().add( -60 / 2 );
                getWorld().showText("It's not very effective...", getWorld().getWidth() / 2, getWorld().getHeight() / 2 + 26);
                Greenfoot.delay(30);
            }
            else if(enemyType.equalsIgnoreCase("Water"))
            {
                enemy.getHealthBar().add( -60 * 2 );
                getWorld().showText("It's super effective!", getWorld().getWidth() / 2, getWorld().getHeight() / 2 + 26);
                Greenfoot.delay(30);
            }
            else
            {
                
            }
        }
        world.setTurn(false);
    }
    
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
            switchCreature = world.getNewOneCreature(1);
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
                world.changePlayerOne("Golem");
            }
            
            switchCreature.switchedIn();
            world.setTurn(false);
        }
    }
    
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
