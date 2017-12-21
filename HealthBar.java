import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    //Need to add constants here
    private final int WIDTH = 200;
    private final int HEIGHT = 30;
    
    private GreenfootImage frame;
    private GreenfootImage healthBar;

    private Color good;
    private Color warning;
    private Color danger;

    //Need to add code here
    private int max;
    private int current;
    private int speed;
    private int target;

   /**
     * Default constructor for objects of the HealthBar class
     * 
     * @param There are no parameters
     * @return an object of the HealthBar type
     */
    public HealthBar()
    {
        //Need to add code
        frame = new GreenfootImage( WIDTH, HEIGHT );
        healthBar = new GreenfootImage ( WIDTH, HEIGHT );

        frame.setColor( Color.GRAY );
        frame.fillRect(0, 0, WIDTH, HEIGHT);

        good = Color.GREEN;
        warning = Color.YELLOW;
        danger = Color.RED;

        //Need to add code
        max = 1000;
        current = 600;
        target = current;
        speed = 1;

        updateBar();
    }

    /**
     * Constructor for objects of the HealthBar class that allows for customization
     * 
     * @param c is the current value of health
     * @param m is the maximum health amount
     * @param s is the speed the health bar's health with change at
     * @return an object of the HealthBar type
     */
    public HealthBar(int c, int m, int s)
    {

        //Need to add code
        frame = new GreenfootImage( WIDTH, HEIGHT );
        healthBar = new GreenfootImage ( WIDTH, HEIGHT );

        //frame.setColor( Color.GRAY );
        //frame.fillRect(0, 0, WIDTH, HEIGHT);

        good = Color.GREEN;
        warning = Color.YELLOW;
        danger = Color.RED;

        //Need to add code
        max = m;
        current = c;
        target = current;
        speed = s;


        updateBar();
    }

    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //Need to add code
        if( current > target )
        {
            current -= speed;
            
            if( current <= target )
            {
                current = target;
            }
        }
        else
        {
            current += speed;
            
            if( current >= target )
            {
                current = target;
            }
        }
        
        updateBar();
    }

    /**
     * updateBar updates the image of the health bar when the current health
     * changes values
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void updateBar()
    {
        //Need to add code
        GreenfootImage text = new GreenfootImage( WIDTH, HEIGHT );
        
        double ratio = ( current * 1.0 ) / ( max * 1.0 );
        
        int healthWidth = (int)Math.round( ratio * frame.getWidth() );

        //Need to add if statements
        if( current > max / 2 )
        {
            healthBar.setColor( good );
        }
        else if(current > max / 4)
        {
            healthBar.setColor( warning );
        }
        else
        {
            healthBar.setColor( danger );
        }

        healthBar.clear();
        healthBar.fillRect( 0, 0, healthWidth, HEIGHT );

        text.clear();
        text.setColor( Color.BLACK );
        text.setFont( new Font( "Times New Roman", 20 ) );
        text.drawString(current + " / " + max, 0, HEIGHT-text.getFont().getSize()/2);

        frame.clear();
        frame.setColor( Color.GRAY );
        frame.fillRect(0, 0, WIDTH, HEIGHT);
        frame.drawImage( healthBar, 0, 0 );
        frame.drawImage( text, WIDTH/3, 0 );

        setImage( frame );
    }

    /**
     * add will change the current value of the health in the health bar
     * 
     * @param change is the amount that the current health will be changed by
     * @return Nothing is returned
     */
    public void add( int change )
    {
        //Need to add code
        target += change;
        
        if( target > max )
        {
            target = max;
        }
        //checks if target health is greater than max
        if( target < 0 )
        {
            target = 0;
        } 
    }

    /**
     * setTarget will change the target value to whatever the user chooses
     * 
     * @param t is the new, user-chosen target value
     * @return Nothing is returned
     */
    public void setTarget( int t )
    {
        target = t;
    }

    /**
     * setCurrent will change the current value to whatever the user chooses
     * 
     * @param c is the new, user-chosen current value
     * @return Nothing is returned
     */
    public void setCurrent( int c )
    {
        current = c;
    }

    /**
     * setMax will change the maximum value to whatever the user chooses
     * 
     * @param m is the new, user-chosen maximum value
     * @return Nothing is returned
     */
    public void setMax( int m )
    {
        max = m;
    }

    /**
     * setSpeed will change the speed to whatever the user chooses
     * 
     * @param s is the new, user-chosen speed
     * @return Nothing is returned
     */
    public void setSpeed( int s )
    {
        speed = s;
    }

    /**
     * getMax returns the maximum value for use in other sections of code or for the user's information
     * 
     * @param There are no parameters
     * @return an integer representing the maximum value
     */
    public int getMax()
    {
        //Change this
        return max;
    }

    /**
     * getCurrent returns the current health value for use in other sections of code or for the user's information
     * 
     * @param There are no parameters
     * @return an integer representing the current health value
     */
    public int getCurrent()
    {
        //Change this
        return current;
    }

}
