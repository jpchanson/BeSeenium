package beseenium.model.action.pageActions;

import beseenium.exceptions.ActionDataException;
import beseenium.model.action.AbstractAction;
import beseenium.model.actionData.ActionData;
/**
 * This is a GetURL Action, it retrieves the URL of the current page
 * @author JPC Hanson
 *
 */
public class GetURL extends AbstractAction 
{
	/**
	 * constructor, initialises Action using the ActionData passed in as a param
	 * @param context an ActionData
	 */
	public GetURL(ActionData context) 
	{super(context);}
	
	/**
	 * This performs a GetURL action.
	 * @param n meaningless pass in any old value
	 * @return String containing the URL of the current page
	 * @throws ActionDataException
	 */
	@Override
	public String execute(int n) throws ActionDataException 
	{	
		return super.context.getDriver().getCurrentUrl();
	}
}