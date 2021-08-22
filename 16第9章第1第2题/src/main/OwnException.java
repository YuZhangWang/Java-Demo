package main;

public class OwnException extends Exception
{
    public OwnException()
    {
        this("出现异常！");
    }

    public OwnException(String msg)
    {
        super(msg);
    }
}
