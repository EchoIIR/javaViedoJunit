package echo.viedo.unit4to8;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class BankDemoTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BankDemoTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( BankDemoTest.class );
    }
    /**
     * Rigourous Test :-)
     */
    public void testBankDemo()
    {
        // 实例化Runnable子类的对象
        Cus c = new Cus();
        // 创建线程的对象，并传入Runnable子类的对象作为线程的内容
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        // 通过Thread对象，运行多线程
        t1.start();
        t2.start();
        assertTrue( true );
    }
}
