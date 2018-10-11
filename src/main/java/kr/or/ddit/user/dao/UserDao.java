package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.db.SqlFactoryBuilder;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserDao implements UserDaoInf{
	public List<UserVo> selectUserAll(){
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		List<UserVo> list = session.selectList("user.selectUserAll");
		session.commit();
//		session.rollback();
		session.close();
		
		return list;
	}
	
	public UserVo selectUser(String user_id){
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		UserVo userVo = session.selectOne("user.selectUser", user_id); 
		session.commit();
		session.close();
		
		return userVo;
	}

	public UserVo selectUser(UserVo userVo){
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		UserVo userVo2 = session.selectOne("user.selectUserByVo", userVo);
		session.commit();
		session.close();
		
		return userVo2;
	}
	
	public List<UserVo> selectUserPageList(PageVo pageVo){
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		List<UserVo> pageList = session.selectList("user.selectUserPageList", pageVo);
		session.commit();
		session.close();
		
		return pageList;
	}
	
	/**
	* Method : getUserCnt
	* 작성자 : pc24
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 건수 조회
	*/
	@Override
	public int getUserCnt() {
		
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		int totalUserCnt = session.selectOne("user.getUserCnt");
		session.commit();
		session.close();
		
		return totalUserCnt;
	}
}
