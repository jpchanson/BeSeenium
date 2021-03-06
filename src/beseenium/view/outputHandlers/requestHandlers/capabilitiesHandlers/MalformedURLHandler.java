package beseenium.view.outputHandlers.requestHandlers.capabilitiesHandlers;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beseenium.controller.Test;
import beseenium.view.inputHandlers.requests.AbstractTestRequest;
import beseenium.view.outputHandlers.requestHandlers.AbstractRequestHandler;

/**
 * This class represents a handler for the case in which the authorisation string of the 
 * remote capabilities has been mutilated by the user, in which case the request should throw
 * a MalformedURLException, which this handler will catch and dispose of appropriately, by
 * by giving the user some information to that effect.
 * 
 * @author Jan P.C. Hanson
 *
 */
public class MalformedURLHandler extends AbstractRequestHandler
{
	/** refefrence to log4j logger **/
	private static final Logger logger = LogManager.getLogger
			("BeSeenium.view.outputHandlers.requestHandlers.capabilitiesHandlers.MalformedURLHandler");
	/**
	 * default ctor
	 */
	public MalformedURLHandler()
	{super();}
	
	/* (non-Javadoc)
	 * @see beseenium.view.outputHandlers.requestHandlers.AbstractRequestHandler#handleRequest(beseenium.view.inputHandlers.requests.AbstractTestRequest, beseenium.controller.Test)
	 */
	@Override
	public String handleRequest(AbstractTestRequest request, Test test)
	{
		String results=null;
		try
		{
			results = request.executeRequest(test);
		} 
		catch (MalformedURLException npe)
		{
			logger.error("BAD URL check 'auth' capability string");
			results="ERROR, BAD URL check 'auth' capability string";
		}
		catch (Exception e)
		{
			if(super.successor != null)
			{results = super.successor.handleRequest(request, test);}
			else
			{
				results = "ERROR, end of chain. see logs for stacktrace";
				logger.fatal("reached end of handler chain, exception not handled" + e + "\n");
			}
		}
	
	return results;
	}

}
