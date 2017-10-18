/**
 * Write a description of class FightCommands here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FightCommands implements MenuCommands
{
    
    public void execute( int idx, Creature c )
    {
        c.attack( idx );
    }
}
