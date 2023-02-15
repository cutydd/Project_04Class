package chatbot.service;

import java.io.IOException;
import java.sql.SQLException;

import chatbot.Reserve;
import chatbot.dao.DataBaseService;
import chatbot.dao.DataBaseServiceImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ReserveServiceImpl implements ReserveService{
	DataBaseService ds;
	CommonService cs;
	
	public ReserveServiceImpl() {
		ds = new DataBaseServiceImpl();
		cs = new CommonServiceImpl();
	}
	
	
	@Override
	public void reserve(Parent root) throws Exception {
		int team = ds.reserveTeam();
		String str = "현재 예약 팀: "+ team+"팀";
		
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../reserveInfo.fxml"));
		Pane p = loader.load();
		
		Label content = (Label) p.lookup("#content");
		
		content.setText(str);

		cs.shopTalk(root,p);

	}
	
	@Override
	public void insertReserve(Parent root) throws Exception{
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../reserve.fxml"));
		Pane p = loader.load();
		
		cs.shopTalk(root, p);
		
		Button btn = (Button) p.lookup("#reserve");
		btn.setOnAction(e -> {
			TextField txtFld[] = {
					(TextField) p.lookup("#name"), 
					(TextField) p.lookup("#mem"), 
					(TextField) p.lookup("#phone")};
			
			//빈칸 검사
			for (TextField txt : txtFld) {
				if(txt.getText() == null || txt.getText().equals("")) {
					try {
						cs.shopTalk(root, "빈칸이 들어가면 안됩니다.");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					btn.setDisable(true);
					return;
				}
			}
			
			Reserve r = new Reserve();
			r.setName(txtFld[0].getText());
			r.setMem(Integer.parseInt(txtFld[1].getText()));
			r.setPhone(txtFld[2].getText());
			
			
			//DB에 넣기
			try {
				if(ds.reserve(r)) {
					cs.shopTalk(root, r.getName()+"님, "+r.getMem()+"명 예약되셨습니다.");
				} else {
					cs.shopTalk(root, "예약 실패");
				}
			} catch (SQLException e1) {
				System.out.println("DB오류");
				//e1.printStackTrace();
			} catch (IOException e1) {
				System.out.println("fxml 오류");
				//e1.printStackTrace();
			}
			
			btn.setDisable(true);
		});
		
		
	}
}
