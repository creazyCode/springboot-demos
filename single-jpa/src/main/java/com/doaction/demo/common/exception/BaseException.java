package com.doaction.demo.common.exception;


import com.doaction.demo.common.error.IBaseCode;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 8838429880037766070L;
	/**
	 * 应答码
	 */
	private IBaseCode result;

	/**
	 * 构造方法
	 * 
	 * @param result
	 */
	public BaseException(IBaseCode result) {
		this(result, result.message());
	}

	/**
	 * 构造方法
	 * 
	 * @param result
	 * @param message
	 */
	public BaseException(IBaseCode result, String message) {
		super(message);
		this.result = result;
	}

	/**
	 * 构造方法
	 * 
	 * @param result
	 * @param cause
	 */
	public BaseException(IBaseCode result, Throwable cause) {
		this(result, result.message(), cause);
	}

	/**
	 * 构造方法
	 * 
	 * @param result
	 * @param message
	 * @param cause
	 */
	public BaseException(IBaseCode result, String message, Throwable cause) {
		super(message, cause);
		this.result = result;
	}

	public void setResult(IBaseCode result) {
		this.result = result;
	}

	public IBaseCode getResult() {
		return result;
	}
}