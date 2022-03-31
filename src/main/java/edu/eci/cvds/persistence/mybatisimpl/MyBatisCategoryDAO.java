package edu.eci.cvds.persistence.mybatisimpl;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.ReportCategory;
import edu.eci.cvds.persistence.CategoryDAO;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.mybatisimpl.mappers.CategoryMapper;

import javax.inject.Inject;
import java.util.List;

public class MyBatisCategoryDAO implements CategoryDAO {

    @Inject
    private CategoryMapper categoryMapper;

    @Override
    public void registerCategory(Category category) throws PersistenceException{
        try {
            categoryMapper.addCategory(category);
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar categoria",e);
        }
    }

    @Override
    public void updateCategory(Category category) throws PersistenceException {
        try {
            categoryMapper.modifyCategory(category);
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al actualizar la categoria",e);
        }
    }

    @Override
    public void deleteCategory(Category category) throws PersistenceException {
        try{
            categoryMapper.eraseCategory(category);
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al eliminar la categoria",e);
        }
    }

    @Override
    public Category load(int categoryId) throws PersistenceException {
        try {
            return categoryMapper.loadC(categoryId);
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar la categoria",e);
        }
    }

    @Override
    public List<Category> loadAll() throws PersistenceException {
        try {
            return categoryMapper.loadAllC();
        } catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar categorias",e);
        }
    }

    @Override
    public List<Category> loadAllActive(boolean status) throws PersistenceException {
        try {
            return categoryMapper.loadAllActive(true);
        } catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar categorias activas",e);
        }
    }

    @Override
    public List<ReportCategory> loadReportCategory() throws PersistenceException {
        try {
            return categoryMapper.loadReportCategory();
        } catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al cargar el reporte de categorias",e);
        }
    }


}
