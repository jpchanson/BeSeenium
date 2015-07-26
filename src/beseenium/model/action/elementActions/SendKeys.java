package beseenium.model.action.elementActions;

import java.util.List;

import org.openqa.selenium.WebElement;

import beseenium.exceptions.ActionDataException;
import beseenium.exceptions.ActionException;
import beseenium.model.action.AbstractAction;
import beseenium.model.actionData.ActionData;

/**
 * This is a sendKeys Action, it simulates a human typing into an element, which 
 * may set its value. Make sure to change the <strong>input parameter</strong>
 * to the value that you wish to enter into the element.
 * @author JPC Hanson
 *
 */
public class SendKeys extends AbstractAction 
{
	/**
	 * constructor, initialises Action using the ActionData passed in as a param
	 * @param context an ActionData
	 */
	public SendKeys(ActionData context) 
	{super(context);}
	
	/**
	 * This performs a sendKeys action.
	 * @param n the index of the element to send the keys to (as taken from the ActionData)
	 * @return String verifying SendKeys action
	 * @throws ActionDataException
	 */
	@Override
	public String execute(int n) throws ActionDataException 
	{
		List<WebElement> elements = super.context.getElement();
		String tag = elements.get(n).getTagName();
		String id = elements.get(n).getAttribute("id");
		String text = elements.get(n).getText();
		String classOf = elements.get(n).getText();
		
		
		elements.get(n).sendKeys(super.context.getInputParam());;	
		
		return "<"+tag+" class='"+classOf+ "' id='"+id+"'>"
				+text +"</"+tag+">  populated with: '" +
				super.context.getInputParam()+"'";
	}
}