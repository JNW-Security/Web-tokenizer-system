package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.ReportCategory;

import java.util.List;

public interface CategoryDAO {

    public void registerCategory(Category category) throws PersistenceException;
    public void updateCategory(Category category) throws PersistenceException;
    public void deleteCategory(Category category) throws PersistenceException;
    public Category load(int categoryId) throws PersistenceException;
    public List<Category> loadAll() throws PersistenceException;
    public List<Category> loadAllActive(boolean status) throws PersistenceException;
    public List<ReportCategory> loadReportCategory() throws PersistenceException;
}
