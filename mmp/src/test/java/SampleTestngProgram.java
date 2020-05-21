import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SampleTestngProgram {

	
//	@BeforeTest
//	public void m1()
//	{
//		String s = null;
//		System.out.println(s.length());
//	}
//	@BeforeClass(alwaysRun=true)
//	public void display()
//	{
//		System.out.println("in display");
//	}
	
	@Test
	public  void m2()
	{
		String filepath = System.getProperty("user.dir")+"\\config\\config.properties";
		System.out.println(filepath);
	}
}
