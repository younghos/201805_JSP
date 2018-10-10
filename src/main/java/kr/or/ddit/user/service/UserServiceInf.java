package kr.or.ddit.user.service;

import java.util.List;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

public interface UserServiceInf {
	
	public List<UserVo> selectUserAll();
	
	public UserVo selectUser(String user_id);
	
	public UserVo selectUser(UserVo userVo);
	
	public List<UserVo> selectUserPageList(PageVo pageVo);
}
