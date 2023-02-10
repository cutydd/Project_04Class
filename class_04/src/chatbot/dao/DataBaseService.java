package chatbot.dao;

import java.sql.SQLException;

import chatbot.Reserve;
import chatbot.Review;

public interface DataBaseService {

	boolean reserve(Reserve r) throws SQLException;
	
	boolean review(Review re);
}
