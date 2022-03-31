package edu.eci.cvds.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.eci.cvds.entities.User;
import edu.eci.cvds.persistence.*;
import edu.eci.cvds.persistence.mybatisimpl.*;
import edu.eci.cvds.services.AnonimizacionServices;
import edu.eci.cvds.services.impl.AnonimizacionServicesImpl;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class GuiceContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent servletContextEvent){
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute(Injector.class.getName());
    }

    public void contextInitialized(ServletContextEvent servletContextEvent){
        Injector injector = Guice.createInjector(new XMLMyBatisModule(){
            @Override
            protected void initialize(){

                install(JdbcHelper.PostgreSQL);

                setEnvironmentId("development");

                setClassPathResource("mybatis-config.xml");

                //Servicios
                bind(AnonimizacionServices.class).to(AnonimizacionServicesImpl.class);
                //Categoria
                bind(CategoryDAO.class).to(MyBatisCategoryDAO.class);
                //Usuario
                bind(UserDAO.class).to(MyBatisUserDAO.class);

            }
        });

        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(Injector.class.getName(), injector);
    }
}
