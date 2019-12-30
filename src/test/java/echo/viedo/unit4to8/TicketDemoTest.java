
package echo.viedo.unit4to8;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class TicketDemoTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TicketDemoTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TicketDemoTest.class );
    }
    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        Ticket t = new Ticket();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(t);
        t1.start();
        t2.start();
        t.flag = true;
        t3.start();
        t4.start();

        // 展示方法一
        // Ticket t1 = new Ticket();
        // Ticket t2 = new Ticket();
        // Ticket t3 = new Ticket();
        // Ticket t4 = new Ticket();

        // t1.start();
        // t2.start();
        // t3.start();
        // t4.start();
    }
}
