package android.support.v7.appcompat.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.robotium.solo.Solo;
import android.app.Activity;
import android.view.View;
import android.test.ActivityInstrumentationTestCase2;

public class TestCalculator extends ActivityInstrumentationTestCase2{
    private Solo solo;
	private Activity activity;
	private static Class<?> launchActivityClass;
	private static String packageClass = "home.jmstudios.calc";
	private static String mainActivity = "home.jmstudios.calc.Main";
	
	static{
		try{
			launchActivityClass = Class.forName(mainActivity);
		}catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
    public TestCalculator(){
    	super(packageClass,launchActivityClass);
    }
	@Before
	public void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(),getActivity());
	}

	@After
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
		super.tearDown();
	}

	@Test
	public void testcase001() {
		solo.waitForText("CE",1,5000);
		solo.clickOnButton("7");
		solo.clickOnButton("*");
		solo.clickOnButton("8");
		solo.clickOnButton("=");
		solo.sleep(500);
		assertTrue(solo.searchText("56"));
		
		solo.clickOnButton("+");
		solo.clickOnButton("3");
		solo.clickOnButton("=");
		solo.sleep(500);
		assertTrue(solo.searchText("59"));
		
	}
	
	@Test
	public void testcase002(){
		solo.waitForText("CE",1,5000);
		solo.clickOnImageButton(0);
		solo.waitForText("Settings");
		solo.clickOnText("Settings");
		solo.waitForText("Precision",1,500);
		solo.assertCurrentActivity("Current activity should be Preferences","Preferences");
		solo.clickOnText("Precision");
		solo.waitForText("OK",1,500);
		solo.clearEditText(0);
		solo.enterText(0,"5");
		solo.clickOnButton("OK");
		solo.clickOnText("Settings");
		solo.waitForActivity("Main",500);
		solo.assertCurrentActivity("Current activity should be Main", "Main");
		
		solo.clickOnButton("1");
		solo.clickOnButton("/");
		solo.clickOnButton("3");
		solo.clickOnButton("=");
		assertTrue(solo.searchText("0.33333"));
	}
	
	@Test
	public void testcase003(){
		View view = null;
		view = solo.getView("home.jmstudios.calc:id/handle");
		
		solo.clickOnView(view);
		solo.waitForText("sin",1,500);
		solo.clickOnButton("sin");
		solo.clickOnView(view);
		
		solo.clickOnButton("9");
		solo.clickOnButton("0");
		solo.clickOnButton("=");
		assertTrue(solo.searchEditText("1"));
		
	}

}
