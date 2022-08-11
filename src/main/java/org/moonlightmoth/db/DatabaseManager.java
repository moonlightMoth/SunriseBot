package org.moonlightmoth.db;

import org.moonlightmoth.model.UserData;

public interface DatabaseManager {
    public UserData selectUserDataByID(int uid);
    public boolean insertOrAlterUserData(int uid, UserData userData);
}
