package State;
import Entity.Creature.Player;
import Game.Assets;
import Game.Handler;
import UI.UIButton;
import UI.UIManager;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WinState extends State{
    private UIManager uiManager;
    private boolean access_to_database = false;
    public static MyResult highScoreData;

    public WinState(Handler handler) {

        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        uiManager.addObject(new UIButton(handler.getGame().getWidth() / 2 - 140, handler.getGame().getHeight()-100, 280, 50, () -> {
            isUIManagerActive = false;
            handler.getGame().menuState.setUIManagerActive(true);
            hasGameStarted = false;
            State.setState(handler.getGame().menuState);
            access_to_database = false;
        }, "Return To Menu"));
    }

    @Override
    public void tick() {
        if (isUIManagerActive){
            uiManager.tick();
        }
        if (handler.getKeyManager().esc){
            handler.getGame().menuState.setUIManagerActive(true);
            State.setState(handler.getGame().menuState);
            access_to_database = false;
        }
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);

        if (isUIManagerActive){
            uiManager.render(g);
        }
        Font fnt1 = new Font("Comic Sans MS", Font.BOLD, 35);
        g.setColor(Color.blue);
        g.setFont(fnt1);

        String text = "You WIN!";
        int text_width = g.getFontMetrics().stringWidth(text);
        g.drawString(text, handler.getWidth()/2-text_width/2, handler.getHeight()/2 - 100);

        g.setColor(Color.white);
        fnt1 = new Font("Comic Sans MS", Font.BOLD, 25);
        g.setFont(fnt1);
        int score = Player.GetScore();

        text = "Score : ";
        text_width = g.getFontMetrics().stringWidth(text);
        g.drawString(text + score, handler.getWidth()/2-text_width/2, handler.getHeight()/2-50);

        if (!access_to_database){
            insertScore(score);
            highScoreData = insertBestScore(score);
            access_to_database = true;
        }

        fnt1 = new Font("Comic Sans MS", Font.BOLD, 20);
        g.setFont(fnt1);
        g.setColor(Color.yellow);
        text = "Best Score :";
        text_width = g.getFontMetrics().stringWidth(text);
        g.drawString(text, handler.getWidth()/2-text_width/2, handler.getHeight()/2-10);

        text = highScoreData.getDate() + " ......... "+ highScoreData.getScore() ;
        text_width = g.getFontMetrics().stringWidth(text);
        g.drawString(text, handler.getWidth()/2-text_width/2, handler.getHeight()/2+20);

        g.drawImage(Assets.pikachu_happy, handler.getWidth()/2-70, handler.getHeight()/2+ 30 , 120, 145, null);

    }
    public UIManager getUiManager() {
        return uiManager;
    }

    public static String returnCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static void insertScore(int score){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:tiobe.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String current_date = returnCurrentDate();

            String sql =  "INSERT INTO Score (Date,Score) " +
                    "VALUES ('" + current_date + "'," +  score + ");";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public static MyResult insertBestScore(int score){
        Connection c = null;
        Statement stmt = null;
        int highscore = 0;
        String highScoreDate="";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:tiobe.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String current_date = returnCurrentDate();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM BestScore;" );

            if (!rs.next() ) {
                String sql =  "INSERT INTO BestScore (Date,Score) " +
                        "VALUES ('" + current_date + "'," +  score + ");";
                stmt.executeUpdate(sql);
                c.commit();
                highScoreDate = current_date;
                highscore = score;
            } else {
                int HighScore = rs.getInt("Score");
                highScoreDate = rs.getString("Date");
                highscore = HighScore;
                if (HighScore <= score){
                    highscore = score;
                    highScoreDate = current_date;
                    String sql =  "UPDATE BestScore set Score =" + score +", Date = '" + current_date + "' where Score="+HighScore+";";
                    stmt.executeUpdate(sql);
                    c.commit();
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return new MyResult(highscore, highScoreDate);
    }
}

final class MyResult {
    private final int score;
    private final String date;

    public MyResult(int score, String date) {
        this.score = score;
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }
}