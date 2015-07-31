package beseenium.view;

import java.util.HashMap;
import java.util.Map;

import beseenium.controller.ActionController;

public class TestManager 
{
	/** holds the ActionController for each test **/
	private Map<String, ActionController> tests;
	
	/**
	 * initialises the object data
	 */
	public TestManager()
	{
		tests = new HashMap<String, ActionController>();
	}
	
	/**
	 * adds a new test to the testManager
	 * @param testID the unique identifier of the test to add
	 */
	public void addTest(String testID)
	{
		if (tests.containsKey(testID))
		{
			throw new testAlreadyExistsException("cannot add a test who already exists");
		}
		else
		{
			tests.put(testID, new ActionController());
		}		
	}
	
	/**
	 * removes a test from the testManager
	 * @param testID the unique identifier of the test to remove
	 */
	public void removeTest(String testID)
	{
		if (tests.containsKey(testID)==false)
		{
			throw new testDoesNotExistException("cannot remove a test that does not exist");
		}
		else
		{
			tests.remove(testID);
		}
	}
	
	/**
	 * gets the ActionController associated with a particular test
	 * @param testID the unique identifier of the test
	 * @return ActionController associated with a particular test
	 */
	public ActionController getTestContext(String testID)
	{
		if (tests.containsKey(testID)==false)
		{
			throw new testDoesNotExistException("cannot get data for a test that does not exist");
		}
		else
		{
			return tests.get(testID);
		}
	}
}
