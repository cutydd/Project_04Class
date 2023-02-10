package chatbot;

import java.io.IOException;
import java.sql.SQLException;

import chatbot.service.CommonService;
import chatbot.service.CommonServiceImpl;
import chatbot.service.ReserveService;
import chatbot.service.ReserveServiceImpl;
import chatbot.service.ReviewService;
import chatbot.service.ReviewServiceImpl;
import javafx.scene.Parent;

public class Controller {
	private Parent root;
	private ReserveService rvs;
	private ReviewService rs;
	private CommonService cs;
	private Parent reviewroot;
	
	public Controller() {
		rvs = new ReserveServiceImpl();
		rs = new ReviewServiceImpl();
		cs = new CommonServiceImpl();
	}
	
	public void setRoot(Parent root) {
		this.root=root;
	}
	
	public void reserveProc() throws SQLException {
		rvs.reserve(root);
	}
	
	public void reviewProc() throws IOException {
		rs.reviewProc(root);
	}
	
	public void sendMsg() throws IOException {
		cs.userTalk(root);
	}

	public void setReview(Parent reviewroot) {
		// TODO Auto-generated method stub
		this.reviewroot = reviewroot;
	}
	
	public void reviewWrite() {
		rs.reviewWrite(reviewroot);
	}
}