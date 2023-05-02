package service;

import controller.LoginUser;
import dao.UserDao;
import vo.User;

public class UserService {
	
	private UserDao userDao = new UserDao();
	
	/*
	 * 회원가입 서비스
	 * 
	 * 반환타입: void
	 * 메서드이름: registerUser
	 * 매개변수: User
	 * 업무로직
	 * 	- 아이디/비밀번호/이름이 포한된 User객체를 전달받아서 회원가입시킨다.
	 * 	- 1. 아이디 중복 체크를 수행한다.
	 * 		- UserDao객체의 getUserById()메서드를 호출해서 아이디로 사용자 정보를 조회한다.
	 * 		- 사용자의 정보가 이미 존재하면 예외를 던진다.
	 * 	- 2. 신규사용자 정보를 저장한다.
	 * 		- UserDao객체의 insertUser()메서드를 호출해서 사용자정보를 저장시킨다.
	 */
	public void registerUser(User user) {
		// 아이디 중복 체크
		User savedUser = userDao.getUserById(user.getId());
		if (savedUser != null) {
			throw new RuntimeException("["+user.getId()+"] 이미 사용중인 아이디입니다.");
		}
		// 아이디가 중복되지 않는다면 신규사용자의 정보를 DB에 저장
		userDao.insertUser(user);
	}
	
	/*
	 * 로그인 서비스
	 * 
	 * 반환타입: LoginUser
	 * 메서드명: login
	 * 매개변수: String id, String password
	 * 업무로직
	 * 	- 아이디/비밀번호를 전달받아서 사용자 인증작업을 수행하고,
	 * 	  인증이 완료되면 번호/아아디/이름이 포함된 LoginUser객체를 반환한다.
	 * 	- 1. 사용자 정보를 조회한다.
	 * 		- UserDao객체의 getUserById() 메서드를 호출해서 아이디로 사용자 정보를 조회한다.
	 * 		- 조회된 사용자정보가 null이면 가입된 아이디가 존재하지 않으므로 예외를 던진다.
	 * 	- 2. 비밀번호를 체크한다.
	 * 		- 조회된 사용자정보의 비밀번호와 입력한 비밀번호를 비교한다.
	 * 		- 비밀번호가 일치하지 않으면 예외를 던진다.
	 * 	- 3. LoginUser를 반환한다.
	 * 		- 인증이 완료되었으므로, LoginUser객체를 생성해서 값을 담고 반환한다.
	 */
	public LoginUser login(String userId, String password) {
		User savedUser = userDao.getUserById(userId);
		
		if (savedUser == null) {
			throw new RuntimeException("["+userId+"] 가입된 아이디가 존재하지 않습니다.");
		}
		if (!savedUser.getPassword().equals(password) ) {
			throw new RuntimeException("["+password+"] 비밀번호가 일치하지 않습니다.");
		}
		// LoginUser객체를 반환할 때 이처럼 객체를 변수에 담아 반환하는 것도 가능하지만,
//		LoginUser loginUser = new LoginUser(savedUser.getNo(), savedUser.getId(), savedUser.getName());
//		return loginUser;
		
		// 객체를 참조변수에 담아서 기능을 실행하거나 활용하는 것이 아니라면 객체 생성과 동시에 반환할 수 있다.
		return new LoginUser(savedUser.getNo(), savedUser.getId(), savedUser.getName());
	}
	
}
