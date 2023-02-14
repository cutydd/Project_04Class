package chatbot.service;

import java.io.IOException;

import chatbot.Review;
import chatbot.dao.DataBaseService;
import chatbot.dao.DataBaseServiceImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ReviewServiceImpl implements ReviewService{
	CommonService cs;
	DataBaseService ds;

	public ReviewServiceImpl() {
		ds = new DataBaseServiceImpl();
		cs = new CommonServiceImpl();
	}

	@Override
	public void reviewProc(Parent root) throws IOException {
		// TODO Auto-generated method stub

		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../reviewTable.fxml"));
		Pane p = loader.load();

		cs.shopTalk(root, p);
		reviewSend(root);
		
//		FXMLLoader loader1 = new FXMLLoader(
//				getClass().getResource("../../reviewSend.fxml"));
//		Pane p1 = loader.load();
//		cs.userTalk(root,p1);
//		
	}
	
	@Override
	public void reviewSend(Parent root) throws IOException {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../reviewSend.fxml"));
		Pane h = loader.load();
		
		ComboBox<Double> cmbStar = (ComboBox<Double>) h.lookup("#cmbStar");
		cmbStar.getItems().addAll(0.5,1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0);
		
		cs.userTalk(root,h);
	}

	@Override
	public void reviewWrite(Parent root) {
		// TODO Auto-generated method stub
		Review r = new Review();

		ComboBox<Double> cmbStar = (ComboBox<Double>) root.lookup("#cmbStar");
		if(cmbStar.getValue() == null) {
			cs.errorMsg("별점", "별점 선택", "별점이 선택되지 않았습니다.");
			cmbStar.requestFocus();
			return;
		} else if(cmbStar.getValue().equals(0.5)) {
			r.setStar(0.5);
		} else if(cmbStar.getValue().equals(1.0)) {
			r.setStar(1.0);
		} else if(cmbStar.getValue().equals(1.5)) {
			r.setStar(1.5);
		} else if(cmbStar.getValue().equals(2.0)) {
			r.setStar(2.0);
		} else if(cmbStar.getValue().equals(2.5)) {
			r.setStar(2.5);
		} else if(cmbStar.getValue().equals(3.0)) {
			r.setStar(3.0);
		} else if(cmbStar.getValue().equals(3.5)) {
			r.setStar(3.5);
		} else if(cmbStar.getValue().equals(4.0)) {
			r.setStar(4.0);
		} else if(cmbStar.getValue().equals(4.5)) {
			r.setStar(4.5);
		} else if(cmbStar.getValue().equals(5.0)) {
			r.setStar(5.0);
		}

		TextField txtFld = (TextField) root.lookup("#reviewContent");

		if(txtFld.getText().isEmpty()) {
			cs.errorMsg("리뷰", "리뷰 작성", "리뷰 내용 없음");
		} else {
			r.setComments(txtFld.getText());
		}

		if(ds.review(r)) {
			txtFld.clear();
			//콤보박스 초기화
			//    	  cmbStar.setPromptText("별점");
		}

	}

}