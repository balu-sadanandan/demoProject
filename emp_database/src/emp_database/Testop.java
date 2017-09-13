package emp_database;
import java.util.*;
import java.sql.*;
import java.io.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Testop {
		public static void main(String[] args) throws SQLException {
			Empdao t1= new Empdao("jdbc:mysql://localhost:3306/emp_db","root","1234");
			//Emp e=new Emp(16,"name","123456789","xxx");
			//t1.addEmployee(e);
			List<Emp> e= t1.listAll();
			Iterator itr =e.iterator();
			while(itr.hasNext())
			{
				Emp eit =(Emp) itr.next();
				System.out.println(eit.getEmp_id()+" "+eit.getName()+" "+eit.getCno()+" "+eit.getMailid());
			}
			
	
		
		
		}
	

}
