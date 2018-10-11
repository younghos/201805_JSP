package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.dao.UserDaoInf;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserServiceTest {
	
	// junit 실행주기
	// @BeforeClass 이 적용된 메서드 실행(최초 1회) 단, static 메서드로 선언
	// @Before 어노테이션이 적용된 메서드 실행(테스트 메서드 실행전 실행)
	// @Test 
	// @After 어노테이션이 적용된 메서드 실행(테스트 메서드 실행 후 실행)
	// @AfterClass 어노테이션이 적용된 메서드 실행(최초 1회) 단, static 메서드로 선언

	// beforeClass
	// before -> Test( selectUserAll ) -> after
	// before -> Test( selectUser(String) ) -> after
	// before -> Test( selectUser(UserVo) ) -> after
	// afterClass
	
	private UserServiceInf userService;
	
	@BeforeClass
	public static void beforeClass(){
		System.out.println("beforeClass");
	}
	
	@AfterClass
	public static void afterClass(){
		System.out.println("afterClass");
	}
	
	@Before
	public void before(){
		System.out.println("before");
		userService = new UserService();
	}
	
	@After
	public void after(){
		System.out.println("after");
	}
	
	@Test
	public void getSelectUserAllTest() {
		/***Given : 주어진 상황***/

		/***When : 어떤 동작 수행(메소드)***/
		List<UserVo> list = userService.selectUserAll();
		System.out.println("list : "+list);
		
		/***Then : 결과가 어떠해야하는지 정의***/
		assertEquals(105, list.size());
	}
	
	@Test
	public void getSelectUserTest(){
		/***Given***/

		/***When***/
		UserVo userVo = userService.selectUser("brown");

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
		
		UserVo userVo2 = userService.selectUser(userVo);
		
		/***Then***/
		assertNotNull(userVo2);
		assertEquals("브라운", userVo2.getName());
		assertEquals("brown", userVo2.getUserId());
	}
	
	@Test
	public void getSelectUserPageListTest(){
		/***Given***/
		
		/***When***/
		PageVo pageVo = new PageVo();
		pageVo.setPage(1);
		pageVo.setPageSize(10);
		
		Map<String, Object> pageList = userService.selectUserPageList(pageVo);
		List<UserVo> userList = (List<UserVo>) pageList.get("userList");
		
		/***Then***/
		assertEquals(10, userList.size());

	}
}
