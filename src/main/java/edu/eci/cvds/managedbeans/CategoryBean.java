package edu.eci.cvds.managedbeans;

import edu.eci.cvds.entities.Category;
import edu.eci.cvds.entities.ReportCategory;
import edu.eci.cvds.services.ServicesException;
import edu.eci.cvds.services.AnonimizacionServices;
import org.primefaces.PrimeFaces;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.nio.charset.*;
import java.util.*;


@ManagedBean(name = "categoryBean")
@SessionScoped
public class CategoryBean extends BasePageBean {

    @Inject
    private AnonimizacionServices anonimizacionServices;
    private Integer categoryId;
    private Category category;
    private String name;
    private String description;
    private int age;
    private String credential;
    private String rh;
    private String vaccine;
    private int id;
    private Date creationDate;
    private boolean button;
    private List<ReportCategory> report;
    private PieChartModel pieModel;

    public void loadCategory() throws ServicesException{
        try {
            if(categoryId != null){
                category = anonimizacionServices.loadCategory(categoryId);
            }
        } catch (ServicesException ex){
            throw ex;
        }
    }

    public List<Category> getCategories() throws ServicesException{
        try {
            return anonimizacionServices.loadCategories();
        } catch (ServicesException ex){
            throw ex;
        }
    }

    public void update(){
        try {
            anonimizacionServices.updateCategory(category);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Category Updated"));
        } catch (ServicesException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Update Error", "Update Error"));
        }
    }

    public  void register(){
        try {
            anonimizacionServices.registerCategory(category);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Category Added", category.getName()));
        } catch (ServicesException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Add Error",ex.getMessage()));
        }
    }

    public void save() throws ServicesException {
        if (this.category.getId() == 0) {
            register();
        }
        else {
            update();
        }
        PrimeFaces.current().executeScript("PF('manageCategoryDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-categories");
    }

    public void erase(Category c) throws ServicesException {
        try{
            anonimizacionServices.deleteCategory(c);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Category Deleted"));
        }catch(ServicesException ex){
            ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete Error","Delete Error"));
        }
    }

    public void openNew() {
        this.category = new Category();
        //this.category.setTokenName(token());
        //metodo de anonimizacion
    }

    public Category getCategory() throws ServicesException {
        if (category == null && categoryId != null){
            category = anonimizacionServices.loadCategory(categoryId);
        }
        return category;
    }

    private void createpieModel()  {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();
        System.out.println("Intenta reporte");
        try {
            report = anonimizacionServices.loadReportCategory();
            report.stream().forEach(p -> System.out.println(p));
            System.out.println("reporte llamado");
        } catch (ServicesException e) {
            e.printStackTrace();
        }
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<ReportCategory> getReport() {
        try {
            report = anonimizacionServices.loadReportCategory();
        } catch (ServicesException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",e.getMessage()));
        }
        return report;
    }

    public void setReport(List<ReportCategory> report) {
        this.report = report;
    }

    public PieChartModel getPieModel() {
        createpieModel();
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }


}

