package State.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static State.Utils.Levels.Level.*;

public class Levels {

   public enum Level{
      level_1, level_2, level_3, level_4, level_5
   }

   public static Level nextLevel(Level level){
      switch(level){
         case level_1:
            return level_2;
         case level_2:
            return level_3;
         case level_3:
            return level_4;
         default:
            return level_5;
      }
   }

   public static Level previousLevel(Level level){
      switch(level){
         case level_3:
            return level_2;
         case level_4:
            return level_3;
         case level_5:
            return level_4;
         default:
            return level_1;
      }
   }

   public static String GetLevelName(Level level){
      switch(level){
         case level_1:
            return "Level 1";
         case level_2:
            return "Level 2";
         case level_3:
            return "Level 3";
         case level_4:
            return "Level 4";
         default:
            return "Final level";
      }
   }

   public static String GetLevelWorld(Level level){
      switch(level){
         case level_1:
            return  GetLevelPathFromDataBase(1);
         case level_2:
            return GetLevelPathFromDataBase(2);
         case level_3:
            return GetLevelPathFromDataBase(3);
         case level_4:
            return GetLevelPathFromDataBase(4);
         case level_5:
         default:
            return GetLevelPathFromDataBase(5);
      }
   }

   public static String GetLevelBackgroundMusicPath(Level level){
      switch(level){
         case level_1:
            return  GetLevelBackgroundFromDataBase(1);
         case level_2:
            return GetLevelBackgroundFromDataBase(2);
         case level_3:
            return GetLevelBackgroundFromDataBase(3);
         case level_4:
            return GetLevelBackgroundFromDataBase(4);
         case level_5:
         default:
            return GetLevelBackgroundFromDataBase(5);
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
