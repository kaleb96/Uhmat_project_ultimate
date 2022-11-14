package action.community;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.community.MateReplyWriteService;
import vo.ActionForward;
import vo.MateReplyDTO;

public class MateReplyWriteAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      System.out.println("MateReplyProAction");
      
      ActionForward forward = null;
      
      
      MateReplyDTO mateReply = new MateReplyDTO();
      int idx = Integer.parseInt(request.getParameter("idx"));
//      String nickname = request.getParameter("nickname");
      String nickname = request.getParameter("nickname");
      System.out.println("nickname" + nickname);
      mateReply.setIdx(Integer.parseInt(request.getParameter("idx")));
      mateReply.setNickname(request.getParameter("nickname"));
      mateReply.setContent(request.getParameter("content"));
//      mateReply.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
//      mateReply.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
//      mateReply.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
      mateReply.setBoard_idx(idx);
      System.out.println(mateReply);
      
      //댓글쓰기
      // MateReplyProService 의 replyMate() 메서드를 호출하여 답글 등록 작업 요청
      // => 파라미터 : CommentDTO 객체   리턴타입 : boolean(isReplySuccess)
      MateReplyWriteService service = new MateReplyWriteService();
      boolean isReplySuccess = service.replyMate(mateReply);
      
      //답글
      
      
      // 답글 등록 작업 요청 처리 결과 판별
      if(!isReplySuccess) {
         response.setContentType("text/html; charset=UTF-8");
         PrintWriter out = response.getWriter();
         out.println("<script>");
         out.println("alert('댓글 등록 실패!')");
         out.println("history.back()");
         out.println("</script>");
      } else {
         forward = new ActionForward();
         forward.setPath("MateDetail.co?idx="+ idx +"&pageNum=" +request.getParameter("pageNum"));
//         forward.setPath("MateDetail.co?pageNum=" + pageNum + "&idx=" + idx);
//         forward.setPath("MateReplyList.co?idx=" + request.getParameter("idx"));
         forward.setRedirect(true);
      }
      
      return forward;
   }

}