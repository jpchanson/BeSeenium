package beseenium.view.helpers;
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
import beseenium.controller.Test;
import beseenium.exceptions.actionDataExceptions.ActionDataException;
import beseenium.exceptions.actionExceptions.ActionException;

/**
 * this is an object wrapper for the emergency shutdown method of the Test class passed 
 * in as a parameter.
 * @author Jan P.C. Hanson
 *
 */
public class EmergencyShutdown
{
	/**
	 * in case something goes wrong the test should be able to shut down the current browser instance,
	 * or else there could potentially be many, for all intents and purposes, orphan processes floating
	 * about eating up memory....remember: always kill the orphans.
	 * @param test the test to shut down.
	 * @return verification string
	 */
	public String execute(Test test)
	{
		try {test.emergencyShutdown();} 
		catch (ActionDataException | ActionException e1) 
		{
			e1.printStackTrace();
			return "CRITICAL_ERROR CANNOT PERFORM EMERGENCY SHUTDOWN, " + e1.getMessage();
		}
		return "ERROR could not run test, ";
	}
}
