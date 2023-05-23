package com.yedam.notice.mapper;

import java.util.List;

//import org.apache.ibatis.annotations.Param;

import com.yedam.notice.domain.ReplyVO;

public interface ReplyMapper {
	public List<ReplyVO> replyList(int noticeId);
	// 댓글등록.
	public int insertReply(ReplyVO vo);
	public int deleteReply(int replyId);
	// public int updateReply(@Param("replyId") int replyId, @Param("reply") String reply);  // 이 경우 xml에 파라미터 타입 미지정
	public int updateReply(ReplyVO vo);
	public ReplyVO getReply(int replyId);
}
