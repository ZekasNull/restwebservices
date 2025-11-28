package database;

import java.sql.SQLException;

/**
 * Representerar ett svar från databasen
 */
public class DatabaseResponse {
    private final boolean success;
    private SQLException error;
    private int errorCode;

    /**
     * Bygger ett nytt svar.
     *
     * @param success Huruvida anropet lyckades eller inte
     */
    public DatabaseResponse(boolean success)
    {
        this.success = success;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setError(SQLException error)
    {
        this.error = error;
        errorCode = error.getErrorCode();
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    @Override
    public String toString()
    {
        return "Database returned error code \"" + error.getErrorCode() + "\" and message \"" + error.getMessage() + "\"";
    }
}
