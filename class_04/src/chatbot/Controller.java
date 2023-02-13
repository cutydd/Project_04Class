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
	// root 를 공용으로 써야 할것 같아요 
	// 이유는 shopInfo.fxml 에서 controller 를 지정해서 다른 객체가 생성되서
	// root 가 없어져요
	private static Parent root;
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
		System.out.println("menuProc : " + root);
		cs.menu(root);
	}
	
	public void infoAddr() throws IOException {
		String str ="서울특별시 마포구 양화로 122 3층, 4층";
		cs.userTalk(root, "주소");
		cs.shopTalk(root, str);
	}
	
	public void infoPark() throws IOException {
		String str ="주차장 정보"; //무엇을 넣어야할지..
		cs.userTalk(root, "주차장");
		cs.shopTalk(root, str);
	}
	
	public void infoCall() throws IOException {
		String str ="02-2231-6412";
		cs.userTalk(root, "전화번호");
		cs.shopTalk(root, str);
	}
}