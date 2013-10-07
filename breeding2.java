import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//package de.vogella.mysql.first;

		import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
		import java.sql.DriverManager;
		import java.sql.PreparedStatement;
		import java.sql.ResultSet;
		import java.sql.SQLException;
		import java.sql.Statement;
import java.util.Date;



public class breeding2 {

	  private static Connection connect = null;
	  private static Statement statement = null;
	  private static PreparedStatement preparedStatement = null;
	  private static ResultSet resultSet = null;
	  private static ResultSet resultSet2 = null;
	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		 Class.forName("com.mysql.jdbc.Driver");
	      connect = DriverManager.getConnection("jdbc:mysql://localhost/mini-project?" + "user=root&password=");
	      statement = connect.createStatement();
	      int count123=0;
	      for(int x=0;x<600;x++){
	    	  String w;
	    	  resultSet = statement.executeQuery("select * from spamwords where wheel=0 order by fitness desc limit 1");
	    	 // while(resultSet.next()) {
	    	  count123++;
	    	  System.out.println("count="+count123);
	    	  if (resultSet.next()) {
	    		  
	    		 
	    		   w= resultSet.getString("word");
				    preparedStatement = connect.prepareStatement("update spamwords set wheel=1 where word = ?");
				   	preparedStatement.setString(1, w);
				   	preparedStatement.executeUpdate();
				   	resultSet2 = statement.executeQuery("select * from spamwords where wheel=0 order by fitness desc");	  
				   	float prevf=0;
				    while (resultSet2.next()) {
				    		prevf= prevf + resultSet2.getFloat("fitness");
				    		
				    	    preparedStatement = connect.prepareStatement("update spamwords set sum= ? where word = ?");
				    	    preparedStatement.setFloat(1, prevf);
				    	    preparedStatement.setString(2, resultSet2.getString("word"));
						   	preparedStatement.executeUpdate();
				    	
				    	
				    }
				    resultSet2 = statement.executeQuery("select * from spamwords where wheel=0 order by fitness desc");
				    float ran = (float)(Math.random()*prevf);
				//    System.out.println(ran);
				    String catw;
				    String[] split;
				    while (resultSet2.next()) {
				    	if(ran < resultSet2.getFloat("sum"))
				    	{
				    		preparedStatement = connect.prepareStatement("update spamwords set wheel=1 where word = ?");
				    	    preparedStatement.setString(1, resultSet2.getString("word"));
						   	preparedStatement.executeUpdate();
						   	
						   	
						    preparedStatement = connect.prepareStatement("insert into  spamwords values (?,?,?,?,?,?)");
						    	split= resultSet2.getString("word").split(",");
						    /*	for (int i = 0; i < split.length; i++) {
						    		if
						    		
						    	}*/
					     	      preparedStatement.setString(1, w+","+(resultSet2.getString("word")));
							      preparedStatement.setInt(2, 0);
							      preparedStatement.setFloat(3, 0);
							      preparedStatement.setInt(4, 1);
							      preparedStatement.setFloat(5, 0);
							      preparedStatement.setInt(6, 1);
							   	preparedStatement.executeUpdate();
							   	
						   	break;
						   	
				    	}
				    	
				    }
				  
	    	//  }
	    	  
	    	  }
	      }
	      
     }
}

