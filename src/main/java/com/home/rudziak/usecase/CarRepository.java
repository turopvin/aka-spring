package com.home.rudziak.usecase;

import com.home.rudziak.core.configurator.annotations.InjectByType;
import com.home.rudziak.core.data.DataSource;
import com.home.rudziak.usecase.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarRepository {

    @InjectByType
    private DataSource connection;


    public void save(Car car) {
        final Connection conn = this.connection.getConnection();
        try {
            final String insertQuery = "INSERT INTO cars(car_id, model) VALUES('d229p092d','bmw')";
            final PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(insertQuery);

            preparedStatement.execute(insertQuery);
//            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
