package edu.eci.cvds.services;

import edu.eci.cvds.entities.*;

import java.util.List;

public interface AnonimizacionServices {

    public void registerUser(User user) throws ServicesException;

    public void registerCategory(Category c) throws ServicesException;

    public void updateCategory(Category category) throws ServicesException;

    public void deleteCategory(Category category) throws ServicesException;

    public Category loadCategory(int categoryId) throws ServicesException;

    public List<Category> loadCategories() throws ServicesException;

    //public User getUser(String username) throws ServicesException;

    public List<Category> loadActiveCategories(boolean status) throws ServicesException;

    public List<ReportCategory> loadReportCategory() throws ServicesException;

    public void registerUserV2(User user) throws ServicesException;;

    public void registerUserV3(User user) throws ServicesException;
}
