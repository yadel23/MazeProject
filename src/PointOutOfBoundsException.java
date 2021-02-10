/**
 * Exception for an out of bounds point in the Maze.
 * 
 * @author Zach Kohlberg
 * @date(03/30/2020)
 */

public class PointOutOfBoundsException extends IndexOutOfBoundsException
{
    public PointOutOfBoundsException(String s)
    {
        super(s);
    }
}
