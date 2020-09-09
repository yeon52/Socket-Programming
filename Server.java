package Test;

import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args){

		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket(1999);

			// 2. 클라이언트의 접속요청을 대기
			while(true)
			{
				System.out.println("서버 접속 대기 중···");
				socket = serverSocket.accept();
				System.out.println("클라이언트가 접속되었습니다. ");

				// 별도 쓰레드 만들어 자료 송수신하게 한다.
				EchoThread echoThread = new EchoThread(socket);
				echoThread.start();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally{
			try {
				// 3. 소켓 닫기 --> 연결 끊기
				socket.close();
			}catch(IOException e) {e.printStackTrace();}
		}
	}

}

//쓰레드 클래스
class EchoThread extends Thread{

	Socket socket;

	InputStream is=null;
	BufferedReader br=null;

	OutputStream os=null;
	PrintWriter pw=null;


	EchoThread(){}
	EchoThread(Socket socket){
		this.socket = socket;
		try{
			// 3. 소켓으로부터 송수신을 위한 I/O stream 얻기
			is = socket.getInputStream(); //수신 --> read();
			br = new BufferedReader(new InputStreamReader(is));
			os = socket.getOutputStream(); //송신 --> write();
			pw = new PrintWriter(os);

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
	}
	public void run(){
		Game2 zz;

		try {
			String str = "";
			int cnt=0;
			boolean val=false;
			char val1;
			int x,y;
			int vvaall;


			zz = new Game2();  //야구게임 클래스 객체 생성
			zz.randomInt();

			while(!val){
				str=br.readLine(); //라인단위로 받아서 스트링에 저장 

				zz.inputUserNumber1(str); //입력 받은 숫자를 배열에 담기

				x=zz.getX(); 

				y=zz.getY();

				vvaall=zz.getvvaall();

				cnt = zz.getCount(); //카운트 

				val = zz.getValue(); //종료
				if(val) val1 = 'T';
				else val1 = 'F';


				System.out.println(str+" :"+x+"S "+y+"B"+" "+val1+" "+"남은 횟수 : "+(20-cnt));
				pw.println(x+"S "+y+"B"+" "+val1+" "+"남은 횟수 : "+(20-cnt)); //출력
				pw.flush();
				if(vvaall == 2) {System.out.println("이겼습니다!");return;}
				if(val1 =='T') {System.out.println("졌습니다. 클라이언트 승리");}
			}

		} catch (IOException e) {
			System.out.println("데이터 "
					+ "송수신에러");
			e.printStackTrace();
		}finally{
			close();
		}

	} 
	public void close(){
		try {
			// 4. 소켓 닫기 --> 연결 끊기
			is.close();
			br.close();
			os.close();
			pw.close();
			socket.close();
		}catch(IOException e) {
			System.out.println("close에러");
			e.printStackTrace();
		}
	}
}    