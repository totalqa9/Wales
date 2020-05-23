import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SampleTestngProgram {
	
	@Test
	public  void m2()
	{
		String filepath = System.getProperty("user.dir")+"\\config\\config.properties";
		System.out.println(filepath);
	}
}
