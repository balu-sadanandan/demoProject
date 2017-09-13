package emp_database;

public class Emp {
	protected int emp_id;
	protected String name;
	protected String cno;
	protected String mailid;

	public Emp(int id) {
		this.emp_id = id;
	}

	public Emp(int emp_id, String name, String cno, String mailid) {
		super();
		this.emp_id = emp_id;
		this.name = name;
		this.cno = cno;
		this.mailid = mailid;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}

}
