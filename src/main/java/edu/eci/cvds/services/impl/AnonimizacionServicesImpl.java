package edu.eci.cvds.services.impl;

import edu.eci.cvds.entities.*;
import edu.eci.cvds.persistence.*;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.AnonimizacionServices;

import javax.inject.Inject;
import java.util.List;

public class AnonimizacionServicesImpl implements AnonimizacionServices {

    @Inject
    private CategoryDAO categoryDAO;

    @Inject
    private UserDAO userDAO;

    @Override
    public void registerUser(User user) throws ServicesException {
        try {
            userDAO.registerUser(user);
        }catch (PersistenceException ex){
            ex.printStackTrace();
            throw new ServicesException("Error al crear el usuario",ex);
        }
    }

    @Override
    public void registerUserV2(User user) throws ServicesException {
        try {
            userDAO.registerUserV2(user);
        }catch (PersistenceException ex){
            ex.printStackTrace();
            throw new ServicesException("Error al crear el usuario Version 2",ex);
        }
    }

    @Override
    public void registerUserV3(User user) throws ServicesException {
        try {
            userDAO.registerUserV3(user);
        }catch (PersistenceException ex){
            ex.printStackTrace();
            throw new ServicesException("Error al crear el usuario Version 3",ex);
        }
    }

    @Override
    public void registerCategory(Category c) throws ServicesException {
        try {
            categoryDAO.registerCategory(c);
        }catch (PersistenceException ex){
            throw new ServicesException("Error al crear categoria",ex);
        }
    }

    @Override
    public void updateCategory(Category category) throws ServicesException {
        try {
            categoryDAO.updateCategory(category);
        }catch (PersistenceException ex){
            throw new ServicesException("Error al actualizar la categoria",ex);
        }
    }

    @Override
    public void deleteCategory(Category category) throws ServicesException {
        try{
            categoryDAO.deleteCategory(category);
        }catch (PersistenceException ex){
            throw new ServicesException("Error al eliminar la categoria",ex);
        }
    }

    @Override
    public List<Category> loadActiveCategories(boolean status) throws ServicesException {
        try {
            return categoryDAO.loadAllActive(status);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error al cargar categorias activas",ex);
        }
    }

    @Override
    public Category loadCategory(int categoryId) throws ServicesException {
        try {
            return categoryDAO.load(categoryId);
        } catch (PersistenceException ex) {
            throw new ServicesException("Error al cargar categoria",ex);
        }
    }

    @Override
    public List<Category> loadCategories() throws ServicesException {
        try {
            return categoryDAO.loadAll();
        } catch (PersistenceException ex) {
            throw new ServicesException("Error al cargar las categorias",ex);
        }
    }

    @Override
    public List<ReportCategory> loadReportCategory() throws ServicesException {
        try {
            return categoryDAO.loadReportCategory();
        } catch (PersistenceException ex) {
            throw new ServicesException("Error al cargar el reporte de categorias",ex);
        }
    }

}
