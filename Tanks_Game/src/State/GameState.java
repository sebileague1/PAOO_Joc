package State;

import AudioPlayer.AudioPlayer;
import Game.*;
import State.Utils.PlayerBar;
import UI.UIManager;
import World.World;
import java.awt.*;
import static State.Utils.Levels.*;
import static State.Utils.Levels.Level.*;


public class GameState extends State {
    private World world;
    private PlayerBar playerBar;
    public static Level current_level = level_1;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, current_level);
        handler.setWorld(world);
        this.playerBar = new PlayerBar(handler);
        this.audioPlayer = new AudioPlayer(GetLevelBackgroundMusicPath(current_level));
    }

    @Override
    protected UIManager getUiManager() {
        return null;
    }

    public void startNewGame(){
        current_level = level_1;
        audioPlayer.setClip(GetLevelBackgroundMusicPath(current_level));
        audioPlayer.loop_play();
        handler.getWorld().getEntityManager().getPlayer().setNumberOfCoins(0);
        String path = GetLevelWorld(current_level);
        world.setWorld(path);
    }


    @Override
    public void tick() {
        if (handler.getWorld().getEntityManager().getCounter() == 0){
            audioPlayer.close();
            if (current_level == level_1){
                State.setState(handler.getGame().winState);

            }
            else{
                State.setState(handler.getGame().intermediateState);
            }
        }

        if (handler.getKeyManager().esc){
            handler.getGame().intermediateMenuState.setUIManagerActive(true);
            State.setState(handler.getGame().intermediateMenuState);
            audioPlayer.stop();
        }
        world.tick();
        playerBar.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        playerBar.render(g);
        g.drawString(GetLevelName(current_level), handler.getWidth()/2-30, 30);
        g.setColor(Color.yellow);
        g.drawString(Integer.toString(handler.getGame().GetFps()), handler.getWidth()-30, 30);
    }

}
