package project.banking.exception;

import java.io.StringWriter;


import java.io.PrintWriter;

public class ExitException extends RuntimeException {

	private Throwable cause = null;
	private String[] objs = null;
	String message = "";
	String messageId = null;
	String[] params = null;
	private Object objct = null;

	public ExitException(String messageId) {
		super(messageId);
		this.messageId = messageId;
	}

	public ExitException(ExitException exec) {
		super(exec.getMessageId());
		this.messageId = exec.getMessageId();
		this.params = exec.getParams();
		this.message = exec.getMessage();
	}

	public ExitException(Throwable cause) {
		this.cause = cause;
	}

	public ExitException(String messageId, Throwable cause) {
		super(messageId);
		this.messageId = messageId;
		this.cause = cause;
	}

	public ExitException(String messageId, String obj, Throwable cause) {
		super(messageId);
		this.messageId = messageId;
		this.cause = cause;
		if (obj != null) {
			objs = new String[1];
			objs[0] = obj;
		}
	}

	public ExitException(String messageId, String[] params) {
		super(messageId);
		this.messageId = messageId;
		this.params = params;
	}

	public ExitException(String messageId, String[] objAry, Throwable cause) {
		super(messageId);
		this.messageId = messageId;
		this.cause = cause;

		if (objAry != null) {
			objs = objAry;
		}
	}

	public ExitException(String messageId, String obj) {
		super(messageId);
		this.messageId = messageId;
		if (obj != null) {
			objs = new String[1];
			objs[0] = obj;
		}
	}

	public String getMessageId() {
		return messageId;
	}

	public String[] getParams() {
		return params;
	}

	public String[] getObject() {
		return objs;
	}

	public Throwable getCause() {
		return cause;
	}

	public String getErrorId() {
		return super.getMessage();
	}

	public void printStackTrace() {
		super.printStackTrace();
		if (cause != null) {
			System.err.println("Caused by:");
			cause.printStackTrace();
		}
	}

	public void printStackTrace(java.io.PrintStream ps) {
		super.printStackTrace(ps);
		if (cause != null) {
			ps.println("Caused by:");
			cause.printStackTrace(ps);
		}
	}

	public void printStackTrace(java.io.PrintWriter pw) {
		super.printStackTrace(pw);
		if (cause != null) {
			pw.println("Caused by:");
			cause.printStackTrace(pw);
		}
	}

	public String generateStackTraceString() {
		StringWriter s = new StringWriter();
		this.printStackTrace(new PrintWriter(s));
		return s.toString();
	}

	public String getErrorMessage() {
		try {
			if (message != null && message != "")
				return message;
			if (messageId != null) {
				if (params != null)
					this.message = MessageUtil.formatMessage(messageId, params);
				else
					message = MessageUtil.getMessageString(messageId);
			}
		} catch (Exception e) {
			// do nothing
		}
		return message;
	}

	public ExitException(Object obj, String messageId) {
		super(messageId);
		this.messageId = messageId;
		this.objct = obj;
	}

	public Object getExceptionObject() {
		return this.objct;
	}

}
// end ExitException Class