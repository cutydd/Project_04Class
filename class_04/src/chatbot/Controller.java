package chatbot;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import chatbot.service.CommonService;
import chatbot.service.CommonServiceImpl;
import chatbot.service.ReserveService;
import chatbot.service.ReserveServiceImpl;
import chatbot.service.ReviewService;
import chatbot.service.ReviewServiceImpl;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class Controller implements Initializable{
	private static Parent root;
	private ReserveService rvs;
	private ReviewService rs;
	private CommonService cs;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		rvs = new ReserveServiceImpl();
		rs = new ReviewServiceImpl();
		cs = new CommonServiceImpl();
		
	}

	@SuppressWarnings("static-access")
	public void setRoot(Parent root) {
		this.root=root;
	}

	public void reserveProc() throws Exception {
		rvs.reserve(root);
	}
	
	public void setReserve() throws Exception {
		cs.userTalk(root, "예약하기");
		rvs.insertReserve(root);
	}

	public void reviewProc() throws IOException {
		rs.reviewProc(root);
	}

	public void sendMsg() throws IOException {
		cs.userTalk(root);
		cs.shopTalk(root, "안녕하세요");
	}

	public void reviewWrite() {
		rs.reviewWrite(root);
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

	public void infoAddr() throws IOException {
		String str ="서울특별시 마포구 양화로 122 3층, 4층";
		cs.userTalk(root, "주소");
		cs.shopTalk(root, str);
	}

	public void infoPark() throws IOException {
		String str ="매장 입구 앞 주차, 매장이용시 2시간 무료주차";
		cs.userTalk(root, "주차장");
		cs.shopTalk(root, str);
	}

	public void infoCall() throws IOException {
		String str ="02-2231-6412";
		cs.userTalk(root, "전화번호");
		cs.shopTalk(root, str);
	}

}