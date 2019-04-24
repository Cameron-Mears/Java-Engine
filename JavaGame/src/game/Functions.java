package game;



public interface Functions
{
    default Window getWindow()
    {
        return null;
    };

    default double deltaSEC()
    {
        return Game.deltaNS()/1000000000;
    }
}