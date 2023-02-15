package chatbot.service;

import java.io.IOException;
import java.util.List;

import chatbot.Review;
import chatbot.dao.DataBaseService;
import chatbot.dao.DataBaseServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ReviewServiceImpl implements ReviewService{
	CommonService cs;
	DataBaseService ds;
	@FXML static TableColumn<Review, Double> star;
	@FXML static TableColumn<Review, String> comments;
	static int cnt=0;

	public ReviewServiceImpl() {
		ds = new DataBaseServiceImpl();
		cs = new CommonServiceImpl();
	}

	public Pane getReview(Parent root) throws IOException {

		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../reviewTable.fxml"));
		Pane p = loader.load();
		
		List<Review> l = ds.reviewTable();
		Label starLabel = (Label) p.lookup("#starSum");
		TableView<Review> reviewList = (TableView<Review>) p.lookup("#reviewTable");
		
		String r = Double.toString(ds.starSum());
		starLabel.setText(r);
		
		star = new TableColumn<Review, Double> ("별점");
		comments = new TableColumn<Review, String> ("리뷰");
		star.setCellValueFactory(new PropertyValueFactory<Review, Double>("star"));
		comments.setCellValueFactory(new PropertyValueFactory<Review, String>("comments"));

		reviewList.getColumns().setAll(star,comments);

		ObservableList<Review> list = FXCollections.observableArrayList(l);
		
		
 		reviewList.setItems(list);
 		return p;
	}
	
	@Override
	public void reviewProc(Parent root) throws IOException {
		// TODO Auto-generated method stub
		Pane p = getReview(root);
		if(cnt<=0) {
			cs.shopTalk(root, p);
			reviewSend(root);
		} else {
			cs.shopTalk(root, p);
			cs.shopTalk(root, "이미 리뷰를 작성 하셨습니다.");
		}
		
		
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
		} else {
			r.setStar(cmbStar.getValue());
		}
		TextField txtFld = (TextField) root.lookup("#reviewContent");

		if(txtFld.getText().isEmpty()) {
			cs.errorMsg("리뷰", "리뷰 작성", "리뷰 내용 없음");
			txtFld.focusedProperty();
			return;
		} else {
			r.setComments(txtFld.getText());
		}

		if(ds.review(r)) {
			txtFld.clear();
			cnt++;
			//콤보박스 초기화
			//    	  cmbStar.setPromptText("별점");
		}

	}

}