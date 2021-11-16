package studdybuddy.ui;

import studdybuddy.dataaccess.*;

/**
 * Abstract class containg methods for setting and getting Remote and Direct
 * DataAccess.
 */

abstract class DataAccessController {

    private DataAccess access = new RemoteDataAcess();

    /**
     * Method for setting a data access instance
     * 
     * @param access Remote- or DirectDataAccess instance
     */
    public void setDataAccess(DataAccess access) {
        this.access = access;
    }

    /**
     * Method for getting instance of DataAccess
     * 
     * @return DataAccess
     */
    public DataAccess getDataAccess() {
        return this.access;
    }
}
