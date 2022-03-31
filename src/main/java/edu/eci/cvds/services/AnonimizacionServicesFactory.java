package edu.eci.cvds.services;

import com.google.inject.Injector;
import edu.eci.cvds.persistence.*;
import edu.eci.cvds.persistence.mybatisimpl.*;
import edu.eci.cvds.services.impl.AnonimizacionServicesImpl;
import org.mybatis.guice.XMLMyBatisModule;

import java.util.Optional;

import static com.google.inject.Guice.createInjector;

public class AnonimizacionServicesFactory {

    private static AnonimizacionServicesFactory instance = new AnonimizacionServicesFactory();
    private static Optional<Injector> optInjector;

    private AnonimizacionServicesFactory() {
        optInjector = Optional.empty();
    }

    private Injector myBatisInjector(String env, String pathResource) {
        return createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                setEnvironmentId(env);
                setClassPathResource(pathResource);
                bind(CategoryDAO.class).to(MyBatisCategoryDAO.class);
                bind(UserDAO.class).to(MyBatisUserDAO.class);
                bind(AnonimizacionServices.class).to(AnonimizacionServicesImpl.class);
            }
        });
    }

    public AnonimizacionServices getSolidaridadServicios(){
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("development","mybatis-config.xml"));
        }

        return optInjector.get().getInstance(AnonimizacionServices.class);
    }


    public AnonimizacionServices getSolidaridadServicesTesting(){
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("test","mybatis-config-h2.xml"));
        }

        return optInjector.get().getInstance(AnonimizacionServices.class);
    }


    public static AnonimizacionServicesFactory getInstance(){
        return instance;
    }
}
