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


public class hamemail {

	
	  private static Connection connect = null;
	  private static Statement statement = null;
	  private static PreparedStatement preparedStatement = null;
	  private static ResultSet resultSet = null;
	
	public static void main(String[] args) throws Exception 
	{
		
		 Class.forName("com.mysql.jdbc.Driver");
	      connect = DriverManager.getConnection("jdbc:mysql://localhost/mini-project?" + "user=root&password=");
	      statement = connect.createStatement();
	      resultSet = statement.executeQuery("select word from spamwords where new=1");
	      int count123=0;
	      while (resultSet.next()) {
							    	  
							    String w= resultSet.getString("word");
								final String dirName="F:/java/networks project/mini-project/ham"; // path of directory you want to search 
								final String str = w; // string to search for 
						
								final File dir = new File(dirName); 
								int count=0, i=0;
								String[] split = str.split(",");
								    
								    
								
								for (File f : dir.listFiles()) { 
								if (!f.isDirectory()) { 
							
								
								for (i = 0; i < split.length; i++) {
								//	System.out.println(f + ": " + fileContains(f, split[i]));		
									if(fileContains(f, split[i]) == true)
									{
										count--;
										break;
									}
								}
								
								}
							//	resultSet = statement.executeQuery("update spamwords set emails='count' where word = 'str'");
								
								}
								float ff= ((float)count * (1+(3-(float)i)/3));
								preparedStatement = connect.prepareStatement("update spamwords set emails= ?, fitness= ?, new=0 where word = ?");
								System.out.println("count="+count123);		
							   	  preparedStatement.setInt(1, count);
							   	  preparedStatement.setFloat(2, ff);
							   	  preparedStatement.setString(3, str);
							      preparedStatement.executeUpdate();
							      count123++;
	      }
	//	readDataBase();
	      
	     int v = statement.executeUpdate("update spamwords set wheel=0  and sum = 0");
	     int v1 = statement.executeUpdate("UPDATE `spamwords` SET `new`=1 WHERE 1");
	}
		
		
//database connectivity
	

	//	public class MySQLAccess {
	
		

		  public static void readDataBase() throws Exception {
		    try {
		      // This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager.getConnection("jdbc:mysql://localhost/mini-project?" + "user=root&password=");

		      // Statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement
		          .executeQuery("select * from spamwords");
		     // writeResultSet(resultSet);
		      System.out.println("b1"); 
		      // PreparedStatements can use variables and are more efficient
		      preparedStatement = connect
		          .prepareStatement("insert into  spamwords values ('Lottery','222','0','0','0')");
		      // "myuser, webpage, datum, summary, COMMENTS from FEEDBACK.COMMENTS");
		      // Parameters start with 1
/*		      preparedStatement.setString(1, "Test");
		      preparedStatement.setString(2, "TestEmail");
		      preparedStatement.setString(3, "TestWebpage");
		      preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
		      preparedStatement.setString(5, "TestSummary");
		      preparedStatement.setString(6, "TestComment");
	*/	 //     preparedStatement.executeUpdate();
	  System.out.println("b2"); 
		      preparedStatement = connect
		          .prepareStatement("SELECT * from spamwords");
		      resultSet = preparedStatement.executeQuery();
		   //   writeResultSet(resultSet);

		      // Remove again the insert comment
/*		      preparedStatement = connect
		      .prepareStatement("delete from FEEDBACK.COMMENTS where myuser= ? ; ");
		      preparedStatement.setString(1, "Test");
		      preparedStatement.executeUpdate();
		      
		      resultSet = statement
		      .executeQuery("select * from FEEDBACK.COMMENTS");
		  //    writeMetaData(resultSet);
*/		      
		    } catch (Exception e) {
		      throw e;
		    } 
		  }
		  


/*		  private void writeMetaData(ResultSet resultSet) throws SQLException {
		    //   Now get some metadata from the database
		    // Result set get the result of the SQL query
		    
		    System.out.println("The columns in the table are: ");
		    
		    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
		      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
		    }
		  }

		  private void writeResultSet(ResultSet resultSet) throws SQLException {
		    // ResultSet is initially before the first data set
		    while (resultSet.next()) {
		      // It is possible to get the columns via name
		      // also possible to get the columns via the column number
		      // which starts at 1
		      // e.g. resultSet.getSTring(2);
		      String user = resultSet.getString("myuser");
		      String website = resultSet.getString("webpage");
		      String summary = resultSet.getString("summary");
		      Date date = resultSet.getDate("datum");
		      String comment = resultSet.getString("comments");
		      System.out.println("User: " + user);
		      System.out.println("Website: " + website);
		      System.out.println("Summary: " + summary);
		      System.out.println("Date: " + date);
		      System.out.println("Comment: " + comment);
		    }
		  }

		  // You need to close the resultSet
		  private void close() {
		    try {
		      if (resultSet != null) {
		        resultSet.close();
		      }

		      if (statement != null) {
		        statement.close();
		      }

		      if (connect != null) {
		        connect.close();
		      }
		    } catch (Exception e) {

		    }
		  
		  }

	//	}	
		
		
		
		
		*/
	
			// returns true iff f contains str 
	private static final boolean fileContains(final File f, final String str) throws IOException { 
			final BufferedReader in = new BufferedReader(new FileReader(f)); 
			String currentLine; 

			while ((currentLine = in.readLine()) != null) { 
			if (currentLine.contains(str)) {
			in.close();
			return true; 
			} 
			}

			in.close(); 
			return false; 
			}
	
	
	
		
	
	
	
}
