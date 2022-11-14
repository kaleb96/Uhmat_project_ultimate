package mail;

import java.util.Random;

public class GenerateUserAuthenticationCode {
	private String authCode; // 인증코드 저장할 변수

	// =========================================================================================
	// 만약, 인증코드를 x자리의 난수로 생성하는 경우
//	public GenerateUserAuthenticationCode(int codeLength) {
//		Random r = new Random();
////		// 0000 ~ 9999 사이 범위 난수 생성
////		int rNum = r.nextInt(10000);
//		
//		// 만약, codeLength 값을 통해 자릿수를 전달받을 경우(10 ^ x 연산식 사용 금지! XOR 기호 취급)
//		// => Math 클래스의 pow() 메서드를 통해 제곱식 계산
//		int rNum = r.nextInt((int)Math.pow(10, codeLength)); // 10 의 codeLength 승 계산(정수화 필요)
//		
//		// 생성되는 난수는 0 ~ 9999 사이로 4자리가 아닌 숫자는 숫자 그대로 표시됨
//		// => 따라서, 모든 숫자를 4자리의 문자형태로 변환
//		//    String.format() 메서드를 호출하여 형식지정문자로 "%04d" 지정하면
//		//    4자리 10진수에서 모자라는 숫자는 모두 0으로 채움
////		authCode = String.format("%04d", rNum);
//		//    => 만약, 코드 길이 전달 시 자릿수 부분을 문자열 결합으로 채움
//		authCode = String.format("%0" + codeLength + "d", rNum);
//	}
	// =========================================================================================
	// 인증코드를 x 자리의 문자 코드로 생성하는 경우
	// 인증 코드를 생성하기 위해 필요한 코드값 집합을 배열로 생성
	// => 영문대문자,소문자,숫자 사용한다고 가정(특수문자 !, # 추가)
	private final char[] codeTable = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '!'
	};
	
	// 생성자를 통해 코드 길이(자릿수) 전달받음
	public GenerateUserAuthenticationCode(int codeLength) {
		// Random 클래스의 nextInt() 메서드를 통해 배열 길이 범위 내의 값을 난수로 생성하여
		// 난수에 해당하는 배열 인덱스에 위치한 문자를 추출하여 문자열 결합을 통해 난수문자열 생성
		// => 이 때, codeLength 값만큼 반복문을 통해 문자열 길이로 사용
		Random r = new Random();
		int tableLength = codeTable.length; // 배열 크기
		
		// java.lang.StringBuffer 클래스를 활용하여 문자열 결합 수행
		// => StringBuffer 클래스는 버퍼를 활용하여 문자열을 다루므로
		//    String 클래스보다 편집에 유리함(편집 시 공간의 낭비가 적고, 속도가 빠름)
		StringBuffer buffer = new StringBuffer();
		
		// for문을 사용하여 전달받은 코드길이(codeLength) 만큼 반복
		for(int i = 0; i < codeLength; i++) { // 또는 for(int i = 1; i <= codeLength; i++)
			// Random 인스턴스의 nextInt() 메서드를 호출하여
			// 난수 범위를 테이블 크기로 한정한 후 생성된 난수를 통해
			// 배열 내의 인덱스에 있는 문자 추출 후 버퍼에 저장
			int rNum = r.nextInt(tableLength); // 테이블 크기 한정 범위 내의 난수 생성
			buffer.append(codeTable[rNum]); // 난수를 인덱스로 활용하여 배열 내의 문자를 버퍼에 추가
			// ex) 난수 rNum 이 5일 때 codeTable[5] = 'F' 이므로
			//     버퍼 문자열(처음엔 널스트링)에 'F' 를 추가(결합)
		}
		
		// 생성된 버퍼 내의 인증코드를 문자열로 변환하여 변수에 저장
		authCode = buffer.toString();
	}

	public String getAuthCode() {
		return authCode;
	}
}
