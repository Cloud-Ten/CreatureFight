import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends Actor
{
    private TextBox titleBar;
    private TextBox menuItems;
    private MenuCommands commands;
    private int fontSize = 24;
    private boolean visible = false;
    private Color mainFG;
    private Color mainBG;
    private Color secondFG;
    private Color secondBG;
    private int titleHeight;
    private int menuHeight;
    private int distance;
    
    public Menu( String tb, String i, int fs, Color mfg, Color mbg, Color sfg, Color sbg, MenuCommands mc )
    {
        mainFG = mfg;
        mainBG = mbg;
        secondFG = sfg;
        secondBG = sbg;
        
        titleBar = new TextBox( tb, fs, true, mainFG, mainBG );
        menuItems = new TextBox( i, fs, true, secondFG, secondBG );
        
        commands = mc;
        fontSize = fs;
    }
    
    public Menu()
    {
        this( "not initialized", "none", 24, Color.BLACK, Color.GRAY, Color.BLACK, Color.WHITE, null ); 
    }
    
    protected void addedToWorld( World w )
    {
        w.addObject( titleBar, getX(), getY() );
        titleHeight = titleBar.getImage().getHeight();
        menuHeight = menuItems.getImage().getHeight(); 
    }
    
    /**
     * Act - do whatever the Menu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        handleMouse();
    }
    
    private void handleMouse()
    {
        CreatureWorld world = (CreatureWorld)getWorld();
        
        if( world.getPlayerOneTurn() == true )
        {
            if(titleBar.getText().equalsIgnoreCase("Fight"))
            {
                distance = 300;
            }
            else
            {
                distance = 450;
            }
        }
        else
        {
            if(titleBar.getText().equalsIgnoreCase("Fight"))
            {
                distance = 600;
            }
            else
            {
                distance = 600;
            }
        }
        
        if(getObjectsInRange(distance, Creature.class).get(0).getWhetherPlayerOne() == true && world.getPlayerOneTurn() == true)
        {
            if( Greenfoot.mouseClicked(titleBar) )
            {
                if( visible == false )
                {
                    getWorld().addObject( menuItems, getX(), getY() + (titleHeight + menuHeight) /2 );
                }
                else
                {
                    getWorld().removeObject( menuItems );
                }
            
                visible = !visible;
            }
        
            if( Greenfoot.mouseClicked(menuItems) )
            {
                MouseInfo mi = Greenfoot.getMouseInfo();
                int menuIndex = ( (mi.getY() - menuItems.getY() + menuHeight / 2 ) - 1 ) / fontSize;
                getWorld().removeObject(menuItems);
                commands.execute(menuIndex, world.getPlayerOne() );
            }
        }
        else if(getObjectsInRange(distance, Creature.class).get(0).getWhetherPlayerOne() == false && world.getPlayerOneTurn() == false)
        {
            if( Greenfoot.mouseClicked(titleBar) )
            {
                if( visible == false )
                {
                    getWorld().addObject( menuItems, getX(), getY() + (titleHeight + menuHeight) /2 );
                }
                else
                {
                    getWorld().removeObject( menuItems );
                }
            
                visible = !visible;
            }
        
            if( Greenfoot.mouseClicked(menuItems) )
            {
                MouseInfo mi = Greenfoot.getMouseInfo();
                int menuIndex = ( (mi.getY() - menuItems.getY() + menuHeight / 2 ) - 1 ) / fontSize;
                getWorld().removeObject(menuItems);
                commands.execute(menuIndex, world.getPlayerTwo() );
            }
        }
        
    }
}
