package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.User;

public interface UserDAO {
    public User getUser(String username) throws PersistenceException;

    public void registerUser(User user) throws PersistenceException;

    public void registerUserV2(User user) throws PersistenceException;

    public void registerUserV3(User user) throws PersistenceException;
}
