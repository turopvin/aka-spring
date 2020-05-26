package com.home.rudziak.usecase;

import com.home.rudziak.core.configurator.annotations.InjectByType;
import com.home.rudziak.core.configurator.annotations.PostConstruct;
import com.home.rudziak.usecase.interfaces.Administrator;
import com.home.rudziak.usecase.interfaces.Washer;
import com.home.rudziak.usecase.model.Car;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AutoTechnicalService {

    @InjectByType
    private Administrator administrator;
    @InjectByType
    private Washer washer;
    @InjectByType
    private CarRepository carRepository;


    private Connection connection;

    @PostConstruct
    @SneakyThrows
    private void init() {
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3333/cars", "artsiom", "password");
        final Statement statement = connection.createStatement();
        final String initQuery = "CREATE TABLE IF NOT EXISTS cars (car_id VARCHAR(9) PRIMARY KEY, model VARCHAR(30))";
        statement.execute(initQuery);
    }

    //    @Transactional
    public void serve(Car car) {
        administrator.register(car);
        service(car);
        carRepository.save(car);
        washer.wash(car);
        administrator.giveBack(car);
    }

    private void service(Car car) {
        System.out.println(car.getModel() + " is served!");
    }
}
