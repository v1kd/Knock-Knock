package com.asu.sva.assign.exceptions;

/**
 * @author Vikranth
 *
 */
public class SVAAppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SVAAppException() {
		super();
	}

	/**
	 * @param message
	 */
	public SVAAppException(final String message) {
		super(message);
	}

	/**
	 * @param t
	 */
	public SVAAppException(final Throwable t) {
		super(t);
	}

	/**
	 * @param e
	 */
	public SVAAppException(final Exception e) {
		super(e);
	}

}
