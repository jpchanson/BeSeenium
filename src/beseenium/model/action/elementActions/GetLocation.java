package beseenium.model.action.elementActions;

import java.util.List;

import org.openqa.selenium.WebElement;

import beseenium.exceptions.actionDataExceptions.ActionDataException;
import beseenium.exceptions.actionExceptions.ActionException;
import beseenium.model.action.AbstractAction;
import beseenium.model.actionData.ActionData;

/**
 * This is a GetLocation Action, It retrieves the location of the top left corner of the 
 * element currently stored in the ActionData object.
 * @author JPC Hanson
 *
 */
public class GetLocation extends AbstractAction 
{
	/**
	 * constructor, initialises Action using the ActionData passed in as a param
	 * @param context an ActionData
	 */
	public GetLocation(ActionData context) 
	{super(context);}
	
	/**
	 * This performs a GetLocation action.
	 * @param n the index of the element to get the location of (as taken from the ActionData)
	 * @return String containing the location of the WebElement[n] contained in the ActionData.
	 * @throws ActionDataException
	 * @throws ActionException 
	 */
	@Override
	public String execute(int n) throws ActionDataException, ActionException 
	{
		List<WebElement> elements = super.context.getElement();			
		String result ="";
		
		if(n==-1)
		{
			for(int element = 0; element < elements.size(); ++element)
			{result += elements.get(element).getLocation().toString()+" , ";}
			return result;
		}
		else
		{
			try
			{return elements.get(n).getLocation().toString();}
		
			catch (IndexOutOfBoundsException e)
			{throw new ActionException(this.toString()+": you provided an invalid index");}
		}
	}
}
