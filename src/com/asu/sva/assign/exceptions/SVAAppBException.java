package com.asu.sva.assign.exceptions;

/**
 * @author Vikranth
 *
 */
public class SVAAppBException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SVAAppBException() {
		super();
	}

	/**
	 * @param message
	 */
	public SVAAppBException(final String message) {
		super(message);
	}

	/**
	 * @param t
	 */
	public SVAAppBException(final Throwable t) {
		super(t);
	}

	/**
	 * @param e
	 */
	public SVAAppBException(final Exception e) {
		super(e);
	}

}
