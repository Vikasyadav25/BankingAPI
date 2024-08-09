package project.banking.exception;


public class NoDataFoundException extends ExitException

{

    public NoDataFoundException(Throwable cause)

    {

        super(cause);

    }

    public NoDataFoundException(String sId)

    {

        super(sId);

    }

    public NoDataFoundException(String sId,Throwable cause)

    {

        super(sId,cause);

    }

    public NoDataFoundException(String msgId,String obj,Throwable cause)

    {

        super(msgId,obj,cause);

    }

    public NoDataFoundException(String msgId,String obj)

    {

        super(msgId,obj);

    }

    public NoDataFoundException(String msgId, String[] objAry, Throwable cause)

    {

        super(msgId,objAry,cause);

    }

    public NoDataFoundException(String msgId, String[] objAry)

    {

        super(msgId,objAry);

    }

}
