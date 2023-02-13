package chatbot.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import chatbot.Reserve;
import chatbot.dao.DataBaseService;
import chatbot.dao.DataBaseServiceImpl;
import javafx.scene.Parent;

public class ReserveServiceImpl implements ReserveService{
	static Scanner sc = new Scanner(System.in);
	
	DataBaseService ds;
	CommonService cs;
	
	public ReserveServiceImpl() {
		ds = new DataBaseServiceImpl();
		cs = new CommonServiceImpl();
	}
	
	
	@Override
	public void reserve(Parent root) throws SQLException, IOException {
		Reserve r = new Reserve();
		cs.shopTalk(root, "+ + + 예약 + + + ");
		System.out.println("+ + + 예약 + + + ");
//		r.setName(strInput("이름"));
//		r.setPhone(strInput("전화번호"));
//		r.setMem(intInput("인원수"));
		
//		ds.reserve(r);
		
		
	}
	

	public int intInput(String str) {
		int i = -1;
		while(i == -1) {
			try {
				System.out.print(str+" 입력: ");
				i = sc.nextInt();
			} catch(Exception e) {
				System.out.println("정수를 입력해주세요.\n");
				sc = new Scanner(System.in);
			}
		}
		return i;
	}
	
	public String strInput(String str) {
		System.out.print(str+" 입력: ");
		return sc.next();
	}


}
