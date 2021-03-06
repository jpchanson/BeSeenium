package beseenium.controller;
/** Copyright(C) 2015 Jan P.C. Hanson & BeSeen Marketing Ltd
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import java.net.MalformedURLException;
import java.util.List;

import beseenium.controller.ActionDataFactory.ActionDataFactory;
import beseenium.controller.ActionFactory.ActionFactory;
import beseenium.exceptions.actionDataExceptions.ActionDataException;
import beseenium.exceptions.actionDataExceptions.ActionDataFactoryException;
import beseenium.exceptions.actionExceptions.ActionException;
import beseenium.exceptions.actionExceptions.ActionFactoryException;
import beseenium.model.ActionInvoker;
import beseenium.model.actionData.ActionData;
/**
 * blueprint for creating Test objects, a Test is ostensibly a series of actions
 * that are performed one after the other. provides methods for adding/removing actions
 * configuring remote capabilities and other functionality that relates to creating and 
 * using a test.
 * 
 * this class acts as the client of command pattern governing the storage and execution 
 * of Abstract Actions.
 * @author Jan P.C. Hanson
 *
 */
public class Test 
{
	/** reference to internal ActionFactory **/
	private ActionFactory actionFactory;
	/** reference to internal ActionDataFactory **/
	private ActionDataFactory actionDataFactory;	
	/** reference to Action Invoker. **/
	private ActionInvoker invoker;
	
	/**
	 * constructs the Test object
	 * @throws ActionDataFactoryException
	 * @throws MalformedURLException
	 */
	public Test() throws ActionDataFactoryException, MalformedURLException
	{
		try
		{
			this.actionDataFactory = new ActionDataFactory();
			this.actionFactory = new ActionFactory(actionDataFactory);
		}
		
		catch(ActionDataFactoryException | MalformedURLException e )
		{
			e.getStackTrace();
		}
	}
	
	/**
	 * Set the browser that you want this test to run on
	 * @param browserDriver
	 * @throws ActionDataException
	 */
	public void setBrowser(String browserDriver) throws ActionDataException
	{
		ActionData actionData = actionDataFactory.makeActionData(browserDriver);	
		this.invoker = new ActionInvoker(actionData);
	}
	
	/**
	 * When using a 'remote' Driver set a capability of the driver i.e. key='os' value='windows'
	 * @param key
	 * @param value
	 * @throws MalformedURLException 
	 */
	public void configureRemoteDriver(String key, String value) throws MalformedURLException
	{
		this.actionDataFactory.setCapabilities(key, value);
	}
	
	/**
	 * Add an action to this test
	 * @param actionName the name of the Action to add
	 * @param inputParameter the input parameter (if applicable) of the action to add
	 * @param optionalIndex an optional index (if applicable) to specify the element that
	 * this action will be performed on(assuming one of the previous actions was a FindElement
	 * action)
	 * @throws ActionFactoryException
	 */
	public void addAction(String actionName, String inputParameter, int optionalIndex) 
			throws ActionFactoryException
	{
		this.invoker.add(actionFactory.makeAction(actionName), inputParameter, optionalIndex);
	}
	
	/**
	 * remove an action from this test.
	 * @param actionToRemove the index of the action to be removed from the test
	 */
	public void removeAction(int actionToRemove)
	{
		this.invoker.remove(actionToRemove);
	}
	
	/**
	 * executes all actions in this test, in the sequence they were added
	 * @return List<String> containing the results of each action
	 * @throws ActionDataException
	 * @throws ActionException
	 */
	public List<String> executeActions() throws ActionDataException, ActionException
	{
		return this.invoker.execute();
	}
	
	/**
	 * remove all actions from this test and close the browser instance associated with it.
	 * @throws ActionDataException
	 * @throws ActionException
	 */
	public void emergencyShutdown() throws ActionDataException, ActionException
	{
		if(this.invoker != null)
		{
			for(int i = this.invoker.size()-1; i >= 0; --i)
			{
				this.invoker.remove(i);
			}
			this.invoker.add(this.actionFactory.makeAction("BrowserQuit"), "", 0);
			this.invoker.execute();
		}
	}
}
