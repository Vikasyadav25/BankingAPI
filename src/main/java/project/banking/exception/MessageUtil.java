package project.banking.exception;

import java.io.InputStream;
import java.util.Properties;
import java.text.MessageFormat;
import java.io.*;

public class MessageUtil
{
	private	static MessageUtil mUtil;
	private	static final String	DEFAULT_MESSAGE	= "	Error Occured ";
	private	static Properties exitProperties;
    private static String resourceFile;
	private static FileInputStream fin;

	/**
	 * <p>Method Name			: MessageUtil				</p>
	 * <p>Synopsis				: Loads	the	properties file	</p>
	 * <p>Type					: constructor				</p>
	 * <p>Input	Parameters		: Exception					</p>
	 * <p>Return Type			: None						</p>
	 * <p>Access Specifier		: private					</p>
	 * @see	Exception			: Exception
	 **/

	private	MessageUtil() throws Exception
	{
		try
		{
		/*	InputStream	is = getClass().getResourceAsStream("exitResources.properties");
			exitProperties =	new	Properties();
			exitProperties.load(is);
			is.close();
		*/
		    resourceFile = System.getProperty("ulysses.resources");
            fin = new FileInputStream(resourceFile);
            exitProperties = new Properties();
            exitProperties.load(fin);
            fin.close();
		}
		catch(Exception	e)
		{
			throw e;
		}

	}

	/**
	 * <p>Method Name			: getMessageString			</p>
	 * <p>Synopsis				: Returns a	message	string	</p>
	 * <p>Type					: static					</p>
	 * <p>Input	Parameters		: String					</p>
	 * <p>Return Type			: String					</p>
	 * <p>Access Specifier		: public					</p>
	 * @see	Exception			: Exception
	 **/

	public static String getMessageString(String messageKey) throws	Exception
	{
		try
		{
			MessageUtil.getInstance();
			String message = exitProperties.getProperty(messageKey);
			if(message != null)
			   return message;
			else
				return DEFAULT_MESSAGE;
		}
		catch(Exception	e)
		{
			throw e;
		}

	}

	/**
	 * <p>Method Name			: getMessageString			</p>
	 * <p>Synopsis				: Returns a	message	string	</p>
	 * <p>Type					: static					</p>
	 * <p>Input	Parameters		: String					</p>
	 * <p>Return Type			: String					</p>
	 * <p>Access Specifier		: private					</p>
	 * @see	Exception			: Exception
	 **/

	static private MessageUtil getInstance() throws	Exception
	{
		if(null	== mUtil)
		{
			mUtil =	new	MessageUtil();
		}

		return mUtil;

	}

   /**
	 * <p>Method Name			: getMessageString			</p>
	 * <p>Synopsis				: Returns a	message	string	</p>
	 * <p>Type					: static method				</p>
	 * <p>Input	Parameters		: String					</p>
	 * <p>Input	Parameters		: Object					</p>
	 * <p>Return Type			: String					</p>
	 * <p>Access Specifier		: public					</p>
	 * @see	Exception			: Exception
	 **/

	public static String formatMessage(String messageKey, Object obj) throws Exception
	{

		MessageFormat mf = new MessageFormat(getMessageString(messageKey));
		Object[] objs =	new	Object[1];
		objs[0]	= obj;
		return mf.format(objs);
	}

	/**
	 * <p>Method Name			: getMessageString			</p>
	 * <p>Synopsis				: Returns a	message	string	</p>
	 * <p>Type					: static method				</p>
	 * <p>Input	Parameters		: String					</p>
	 * <p>Input	Parameters		: Object[]					</p>
	 * <p>Return Type			: String					</p>
	 * <p>Access Specifier		: public					</p>
	 * @see	Exception			: Exception
	 **/

	public static String formatMessage (String messageKey, Object[]	objs) throws Exception
	{
		MessageFormat mf = new MessageFormat(getMessageString(messageKey));
		return mf.format(objs);
	}

}//End MessageUtil Class