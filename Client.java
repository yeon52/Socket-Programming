package Test;

import java.net.*;
import java.io.*;
public class Client {
	public static void main(String[] args){
		//테이터를 송수신하기 위한 소켓을 준비
		int cnt=0;
		Socket socket = null;
		try {
			// 1. 소켓객체 생성하기
			System.out.println("서버에 접속 요청 중···");
			socket = new Socket("localhost",1999); //IP,Port 번호를 알아야 접속이 가능
			System.out.println("서버에 접속되었습니다.");

			// 2. 데이터 송수신을 위한 I/O stream 얻기
			InputStream is = socket.getInputStream(); //수신 --> read();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			OutputStream os = socket.getOutputStream(); //송신 --> write();
			PrintWriter pw = new PrintWriter(os);

			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

			// 3. 데이터 송신
			String str = "",str1="";
			System.out.println("게임을 시작하지 ⊙_⊙");
			while(true){
				//데이터 입력
				System.out.print("숫자 4개를 입력하시오>>");
				cnt++;
				str = stdin.readLine();

				if(str.length()!=4){
					System.out.println("다시 입력하세요");
					System.out.println("숫자 4개를 입력하시오>>");
					str = stdin.readLine();
				}
				//서버로 전송
				pw.println(str);
				pw.flush();

				str1 = br.readLine();

				System.out.println(">>"+str1);

				if(cnt==20) {break;}
				if(str1.subSequence(6, 8).charAt(0)!='F'){break;}
			}
			if(cnt==20) {System.out.println("졌습니다. 다음 기회에..ㅠ_ㅠ");}
			else{System.out.println("정답! 축하합니다~!");}

		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally{
			try {
				// 4. 소켓 닫기 --> 연결 끊기
				socket.close();
			}catch(IOException e) {e.printStackTrace();}
		}}
}