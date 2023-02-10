package chatbot.dao;

import java.sql.SQLException;

import chatbot.Reserve;

public interface DataBaseService {

	boolean reserve(Reserve r) throws SQLException;
}
