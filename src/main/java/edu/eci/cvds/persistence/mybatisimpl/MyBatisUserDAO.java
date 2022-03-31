package edu.eci.cvds.persistence.mybatisimpl;

import edu.eci.cvds.entities.User;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.UserDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.UserMapper;

import javax.inject.Inject;

public class MyBatisUserDAO implements UserDAO {
    @Inject
    private UserMapper userMapper;
    @Override
    public User getUser(String username) throws PersistenceException {
        try {
            return userMapper.loadUser(username);
        } catch (org.apache.ibatis.exceptions.PersistenceException ex){
            throw new PersistenceException("Error al consultar el usuario",ex);

        }
    }

    @Override
    public void registerUser(User user) throws PersistenceException {
        try {
            userMapper.registerUser(user);
        } catch (org.apache.ibatis.exceptions.PersistenceException ex){
            throw new PersistenceException("Error al crear el usuario",ex);
        }
    }

    @Override
    public void registerUserV2(User user) throws PersistenceException {
        try {
            userMapper.registerUserV2(user);
        } catch (org.apache.ibatis.exceptions.PersistenceException ex){
            throw new PersistenceException("Error al crear el usuario version 2",ex);
        }
    }

    @Override
    public void registerUserV3(User user) throws PersistenceException {
        try {
            userMapper.registerUserV3(user);
        } catch (org.apache.ibatis.exceptions.PersistenceException ex){
            throw new PersistenceException("Error al crear el usuario version 3",ex);
        }
    }
}
