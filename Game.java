package Test;

class Game2{
	public static int MAX_COUNT = 4;  // 입력받는 숫자의 자릿수 
	public static int MAX_INPUT = 20; // 최대 게임 횟수
	int x=0, y=0; //x= 스트라이크 갯수, y=볼 갯수
	private boolean val=false;
	private int baseNumber[] = new int[MAX_COUNT]; //정답배열
	private int userNumber[] = new int[MAX_COUNT]; //사용자 입력 값 배열
	private int cnt=0;

	Game2(){}

	void randomInt(){ //랜덤으로 정답 배열 만들기
		do{baseNumber[0] = (int)(Math.random()*10);
		}while(baseNumber[0]==0);

		for(int i=1;i<baseNumber.length;i++){
			baseNumber[i] = (int)(Math.random()*10);
			for(int j=0;j<i;j++){
				while(baseNumber[i]==baseNumber[j] || baseNumber[i]==0){
					baseNumber[i] = (int)(Math.random()*10); j=0;
				}
			}
		} 
	}

	void inputUserNumber1(String in1){ //사용자값 입력 받아 처리

		String in = in1;
		for(int i=0;i<userNumber.length;i++){
			userNumber[i]=in.charAt(i)-48;
		}
		countSB(baseNumber,userNumber);

	}
	int vvaall=1;
	synchronized void countSB(int a[],int b[]){ //스트라이크 볼 카운트

		x=0; y=0; //스트라이크, 볼 갯수 초기화
		for(int i=0;i<a.length;i++){
			for(int j=0;j<b.length;j++){
				if(b[i]==a[j])
					if(i==j) x+=1;
					else y+=1;
			}
		} 
		cnt++;
		if(cnt==MAX_INPUT) {vvaall=2;return;} //20번 넘으면 종료
		if(x==MAX_COUNT) {val=true;return;} //4스트라이크면 종료
	}
	public int getX() { return x;}
	public int getY() { return y;}

	int getCount(){ return cnt; } 
	boolean getValue(){ return val; } 
	int getvvaall() {return vvaall;}}