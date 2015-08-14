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
package beseenium.exceptions.testExceptions;
/**
 * 
 *
 * @author Jan P.C. Hanson
 *
 */
public class TestAlreadyExistsException extends TestException 
{
	/**
	 * needed to avoid warnings
	 */
	private static final long serialVersionUID = -4809217893822758020L;

	/**
	 * default constructor
	 */
	protected TestAlreadyExistsException()
	{
		super();
	}
	
	/**
	 * instsnciate excecption with message 
	 * @param message string to display
	 */
	public TestAlreadyExistsException(String message)
	{
		super(message);
	}
}