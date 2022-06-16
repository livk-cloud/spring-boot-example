package org.springframework.core;

import org.springframework.lang.Nullable;

import java.io.IOException;

/**
 * <p>
 * NestedIOException
 * </p>
 *
 * @author livk
 * @date 2022/5/23
 */
public class NestedIOException extends IOException {

	static {
		// Eagerly load the NestedExceptionUtils class to avoid classloader deadlock
		// issues on OSGi when calling getMessage(). Reported by Don Brown; SPR-5607.
		NestedExceptionUtils.class.getName();
	}

	/**
	 * Construct a {@code NestedIOException} with the specified detail message.
	 * @param msg the detail message
	 */
	public NestedIOException(String msg) {
		super(msg);
	}

	/**
	 * Construct a {@code NestedIOException} with the specified detail message and nested
	 * exception.
	 * @param msg the detail message
	 * @param cause the nested exception
	 */
	public NestedIOException(@Nullable String msg, @Nullable Throwable cause) {
		super(msg, cause);
	}

	/**
	 * Return the detail message, including the message from the nested exception if there
	 * is one.
	 */
	@Override
	@Nullable
	public String getMessage() {
		return NestedExceptionUtils.buildMessage(super.getMessage(), getCause());
	}

}
