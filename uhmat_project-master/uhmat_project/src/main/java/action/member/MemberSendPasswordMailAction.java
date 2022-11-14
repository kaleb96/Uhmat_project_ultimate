package action.member;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import mail.GenerateUserAuthenticationCode;
import mail.GmailSMTPAuthenticator;
import svc.member.MemberSendAuthMailService;
import svc.member.MemberSendPasswordMailService;
import util.SHA256;
import vo.ActionForward;

public class MemberSendPasswordMailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberSendPasswordMailAction");
		
		ActionForward forward = null;
		
		// 아이디, 이메일 파라미터값 가져오기
		
		String email = request.getParameter("email");

		// ---------------------------------------------------------------------------------
		// 인증코드를 생성하는 GenerateUserAuthenticationCode 객체 생성
		GenerateUserAuthenticationCode genAuthCode = new GenerateUserAuthenticationCode(10);
		String newPasswd = genAuthCode.getAuthCode();
		System.out.println("새로운 패스워드 : " + newPasswd);
		// ---------------------------------------------------------------------------------
		// 인증코드를 메일로 발송 후 발송 성공 시 DB auth_code 테이블에 아이디, 인증코드 추가
		String sender = "uhmat@naver.com"; // 보내는 사람 주소(관리자 메일)
		String receiver = email; // 받는 사람 주소
		String subject = "[어맛] 어디가맛집이고 비밀번호 변경 메일입니다."; // 메일 제목
		// 메일 본문은 하이퍼링크를 통해 사용자가 클릭하면 인증페이지 요청되도록 설정
		// => 하이퍼링크를 통해 MemberAuth.auth 서블릿 주소를 요청하고 파라미터로 아이디, 인증코드 전달
		String content = "<h3>당신의 새로운 비밀번호는"+newPasswd+" 입니다.</h3><br><a href='http://localhost:8080/uhmat_project/MemberPasswordModifyForm.me?email=" + email + "&passwd=" + newPasswd + "'><b>비밀번호를 변경하시려면 클릭하세요</b></a>"; // 메일 본문

		try {
			// ---------------- 메일 전송을 위한 설정 작업 -----------------
			// 메일 전송 프로토콜 : SMTP(Simple Mail Tranfer Protocol) - TCP 587번 포트 사용(Gmail 기준)
			// => 시스템(서버)의 속성 정보(= 서버 정보)를 java.util.Properties 객체로 리턴받기
			Properties properties = System.getProperties();
			// 메일 전송에 필요한 기본 설정 정보를 서버 속성 정보에 추가 - Properties 객체의 put() 메서드 사용
			// 메일 전송에 사용할 메일 서버 지정(구글, 네이버, 아웃룩 등)
			properties.put("mail.smtp.host", "smtp.gmail.com"); // 구글(Gmail) SMTP 서버
			properties.put("mail.smtp.auth", "true"); // SMTP 서버에 대한 인증 여부 설정
			properties.put("mail.smtp.port", "587"); // SMTP 서비스 포트 설정
			// 메일 인증 정보 설정
			properties.put("mail.smtp.starttls.enable", "true"); // TLS 인증 사용 여부 설정
			properties.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 인증 프로토콜 버전 설정
			// 만약, TLS 버전 문제 발생(could not convert socket to TLS) 시 다음 코드 추가
//		 	properties.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // 인증 프로토콜 버전 설정
			
			// 메일 서버 인증 정보를 생성하는 사용자 정의 클래스의 인스턴스 생성
			Authenticator authenticator = new GmailSMTPAuthenticator(); // 업캐스팅 활용
			
			// 자바 메일에서 메일 전송 기본 단위를 javax.mail.Session 객체 단위로 관리하므로
			// Session 클래스의 getDefaultInstance() 메서드를 호출하여 객체 얻어오기
			// => 파라미터 : 서버 정보와 인증 정보 전달
			Session mailSession = Session.getDefaultInstance(properties, authenticator);
			
			// 서버와 인증 정보를 포함하는 MimeMessage 객체 생성
			// => 파라미터로 Session 객체 전달
			// => 생성된 MimeMessage 객체를 통해 전송할 메일에 대한 정보 생성
			Message mailMessage = new MimeMessage(mailSession); // 업캐스팅
			
			// 전송할 메일에 대한 정보 설정
			// 1. 발신자 정보 설정
			// => 단, 스팸 메일 정책으로 인해 상용 메일 사이트(구글, 네이버 등)는 발신자 주소 변경 불가능
			Address senderAddress = new InternetAddress(sender, "어맛");
			// 2. 수신자 정보 설정
			Address receiverAddress = new InternetAddress(receiver);
			// 3. Message 객체를 통해 전송할 메일에 대한 내용 정보 설정
			// 3-1) 메일 헤더 정보 설정
			mailMessage.setHeader("content-Type", "text/html; charset=UTF-8");
			// 3-2) 발신자 정보 설정
			mailMessage.setFrom(senderAddress);
			// 3-3) 수신자 정보 설정
			mailMessage.addRecipient(RecipientType.TO, receiverAddress);
			// 3-4) 메일 제목 설정
			mailMessage.setSubject(subject);
			// 3-5) 메일 본문 설정
			mailMessage.setContent(content, "text/html; charset=UTF-8");
			// 3-6) 메일 전송 날짜 및 시각 정보 설정(java.util.Date 객체 생성을 통해 시스템 시각 정보로 설정)
			mailMessage.setSentDate(new Date());
			
			// 4. 메일 전송
			// javax.mail.Transport 클래스의 static 메서드 send() 호출
			Transport.send(mailMessage);
//			System.out.println("메일이 정상적으로 전송되었습니다.");
			
			// 발송 성공 시 DB auth_code 테이블에 아이디, 인증코드 추가
			 GenerateUserAuthenticationCode genAuthPassCode = new GenerateUserAuthenticationCode(10);
			 String rawPasswd = genAuthPassCode.getAuthCode();
			 String passwd= SHA256.encodeSha256(newPasswd);
			MemberSendPasswordMailService service = new MemberSendPasswordMailService();
			boolean isNewPasswordSuccess = service.newPassword(email, passwd,""); 
			
			if(!isNewPasswordSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('새로운 패스워드 등록 실패!')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('새로운 패스워드 등록 성공!')");
				out.println("</script>");
				forward = new ActionForward();
				forward.setPath("MemberPasswordModifyForm.me");
				forward.setRedirect(false);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("SMTP 서버 설정 또는 서비스 문제 발생!");
		}
		
		return forward;
	}

}
