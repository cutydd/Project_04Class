package chatbot;

import java.io.IOException;
import java.sql.SQLException;

import chatbot.service.CommonService;
import chatbot.service.CommonServiceImpl;
import chatbot.service.ReserveService;
import chatbot.service.ReserveServiceImpl;
import chatbot.service.ReviewService;
import chatbot.service.ReviewServiceImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

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
		cs.shopTalk(root, "안녕하세요");
	}

	public void setReview(Parent reviewroot) {
		this.reviewroot = reviewroot;
	}
	
	public void reviewWrite() {
		rs.reviewWrite(reviewroot);
	}
	
	public void infoMsg() throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../shopInfo.fxml"));
		Pane p = loader.load();
		cs.shopTalk(root, p);
	}
	public void menuProc() throws IOException {
		cs.menu(root);
	}
}