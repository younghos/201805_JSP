package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {

	private UserDaoInf userDao;

	@Before
	public void before(){
		System.out.println("before");
		userDao = new UserDao();
	}
	
	@Test
	public void getSelectUserAllTest() {
		/***Given : 주어진 상황***/

		/***When : 어떤 동작 수행(메소드)***/
		List<UserVo> list = userDao.selectUserAll();
		System.out.println("list : "+list);
		
		/***Then : 결과가 어떠해야하는지 정의***/
		assertEquals(5, list.size());
	}
	
	@Test
	public void getSelectUserTest(){
		/***Given***/

		/***When***/
		UserVo userVo = userDao.selectUser("brown");

		/***Then***/
		assertNotNull(userVo);
		assertEquals("브라운", userVo.getName());
		assertEquals("brown", userVo.getUserId());
	}
	
	@Test
	public void getSelectUserByVoTest(){
		/***Given***/

		/***When***/
		UserVo userVo = new UserVo();
		userVo.setUserId("brown");
		
		UserVo userVo2 = userDao.selectUser(userVo);
		
		/***Then***/
		assertNotNull(userVo2);
		assertEquals("브라운", userVo2.getName());
		assertEquals("brown", userVo2.getUserId());
	}
	
	// 게시글 입력
	// 제목, 내용 -> board 클래스
	// 첨부파일 -> boardFile 클래스
	
	@Test
	public void getSelectUserPageListTest(){
		/***Given***/

		/***When***/
		PageVo pageVo = new PageVo();
		pageVo.setPage(1);
		pageVo.setPageSize(10);
		
		List<UserVo> pageList = userDao.selectUserPageList(pageVo);
		
		/***Then***/
		assertEquals(10, pageList.size());

	}
	

}
