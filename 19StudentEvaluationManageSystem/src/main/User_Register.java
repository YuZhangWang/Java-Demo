package main;
import java.util.Scanner;
import java.util.regex.Pattern;
class UserFC { //用户注册父类

	private String userId;
	private String password;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
class Student extends UserFC {

	private String name;//姓名
	private String sex;//性别
	private String age;//年龄
	private String phone;//电话
	private String qq;
	private String email;

	Student() {}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
class Teacher extends UserFC {

	private String sex;
	private String age;
	private String position;//职称
	private String dept;//系
	private String phone;
	private String qq;

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}

}

public class User_Register {
	// 注册
	public Student register() {
		Scanner in = new Scanner(System.in);
		Student student = new Student();
		String userId = null;
		String password = null;
		String name = null;
		String sex = null;
		String age = null;
		String phone = null;
		String qq = null;
		String email = null;
		String agePattern = "[0-9]{1,2}";
		String phonePattern = "[0-9]{11}";
		String qqPattern = "[0-9]{6,10}";
		String emailPattern = "[a-zA-Z0-9]{1,20}@[a-zA-Z0-9.]{1,20}";

		// 输入用户名
		System.out.println("请输入学号：");
		userId = in.nextLine();

		// 输入密码
		System.out.println("请输入密码：");
		password = in.nextLine();

		// 输入姓名
		System.out.println("请输入姓名：");
		name = in.nextLine();

		// 选择性别
		System.out.println("请选择性别：");
		System.out.println("1.男");
		System.out.println("2.女");
		sex = in.nextLine();
		while (!sex.equals("1") && !sex.equals("2")) {
			System.out.println("请重新选择！" + sex);
			sex = in.nextLine();
		}
		if(sex.equals("1")) {
			sex = "男";
		} else {
			sex = "女";
		}

		// 输入年龄
		System.out.println("请输入年龄：");
		age = in.nextLine();
		//检查age是否是两位整数。不符合，则重新输入
		while (!Pattern.matches(agePattern, age)) {
			System.out.println("输入年龄不对！请重新输入！");
			age = in.nextLine();
		}

		// 输入phone
		System.out.println("请输入电话：");
		phone = in.nextLine();
		//检查phone是否是11位整数。不符合，则重新输入
		while (!Pattern.matches(phonePattern, phone)) {
			System.out.println("输入电话为11位整数！请重新输入！");
			phone = in.nextLine();
		}

		// 输入qq
		System.out.println("请输入qq：");
		qq = in.nextLine();
		//检查qq是否是6-10位整数。不符合，则重新输入
		while (!Pattern.matches(qqPattern, qq)) {
			System.out.println("输入qq格式不对！请重新输入！");
			qq = in.nextLine();
		}

		// 输入email
		//检查email是否符合要求。不符合，则重新输入
		System.out.println("请输入email：");
		email = in.nextLine();
		while (!Pattern.matches(emailPattern, email)) {
			System.out.println("输入email格式不对！请重新输入！");
			email = in.nextLine();
		}

		student.setUserId(userId);
		student.setPassword(password);
		student.setName(name);
		student.setSex(sex);
		student.setAge(age);
		student.setPhone(phone);
		student.setQq(qq);
		student.setEmail(email);
		return student;
	}
}

