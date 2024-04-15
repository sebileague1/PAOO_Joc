package State.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Levels {

   public enum Level{
      level_1
   }


   public static String GetLevelName(Level level){
      switch(level){
         case level_1:
            return "Level 1";
         default:
            return "Level 1";
      }
   }

   public static String GetLevelWorld(Level level){
      switch(level){
         case level_1:
            return  GetLevelPathFromDataBase(1);
         default:
            return GetLevelPathFromDataBase(1);
      }
   }

   public static String GetLevelBackgroundMusicPath(Level level){
      switch(level){
         case level_1:
            return  GetLevelBackgroundFromDataBase(1);
         default:
            return GetLevelBackgroundFromDataBase(1);
      }
   }

   public static String GetLevelPathFromDataBase(int level){
      Connection c = null;
      Statement stmt = null;
      String path = "";
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:tiobe.db");
         c.setAutoCommit(false);
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM LevelPath WHERE Level=" + level + ";" );
         path = rs.getString("Path");
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Operation done successfully");
      return path;
   }

   public static String GetLevelBackgroundFromDataBase(int level){
      Connection c = null;
      Statement stmt = null;
      String path = "";
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:tiobe.db");
         c.setAutoCommit(false);
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM LevelPath WHERE Level=" + level + ";" );
         path = rs.getString("Background");
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Operation done successfully");
      return path;
   }
}
