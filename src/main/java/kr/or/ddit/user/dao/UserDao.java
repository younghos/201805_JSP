package kr.or.ddit.user.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.db.SqlFactoryBuilder;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserDao implements UserDaoInf{
	public List<UserVo> selectUserAll(){
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		return session.selectList("user.selectUserAll");
	}
	
	public UserVo selectUser(String user_id){
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		return session.selectOne("user.selectUser", user_id);
	}

	public UserVo selectUser(UserVo userVo){
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		return session.selectOne("user.selectUserByVo", userVo);
	}
	
	public List<UserVo> selectUserPageList(PageVo pageVo){
		SqlSessionFactory factory = SqlFactoryBuilder.getSqlSessionFactory();
		SqlSession session = factory.openSession();
		
		return session.selectList("user.selectUserPageList", pageVo );
	}
}
