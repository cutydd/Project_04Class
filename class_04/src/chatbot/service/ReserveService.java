package chatbot.service;

import java.sql.SQLException;

import javafx.scene.Parent;

public interface ReserveService {

	void reserve(Parent root) throws SQLException;

}