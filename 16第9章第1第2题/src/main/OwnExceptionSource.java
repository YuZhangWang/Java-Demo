package main;

public class OwnExceptionSource
{
    public void a() throws OwnException
    {
        throw new OwnException();
    }
}
