// Adapted from Tapestry 4's EvenOdd class.

package eportfolium.com.karuta.batch.commons;

/**
 * Used to emit a stream of alterating string values: "even", "odd", etc. This
 * is often used in the Inspector pages to make the class of a &lt;tr&gt;
 * alternate for presentation reasons.
 * 
 * @author Howard Lewis Ship
 */

public class EvenOdd {

	private boolean even = true;

	/**
	 * Returns "even" or "odd". Whatever it returns on one invocation, it will
	 * return the opposite on the next. By default, the first value returned is
	 * "even".
	 */
	public String getNext() {
		String result = getCurrent();
		even = !even;
		return result;
	}

	public String getCurrent() {
		return even ? "even" : "odd";
	}

	public boolean isEven() {
		return even;
	}

	/**
	 * Overrides the even flag.
	 */
	public void setEven(boolean value) {
		even = value;
	}
}
