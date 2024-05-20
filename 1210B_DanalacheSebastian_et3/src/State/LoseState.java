package State;
import AudioPlayer.AudioPlayer;
import Game.Assets;
import Game.Handler;
import UI.UIButton;
import UI.UIManager;
import java.awt.*;

public class LoseState extends State{
    private UIManager uiManager;
    public LoseState(Handler handler) {

        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        uiManager.addObject(new UIButton(handler.getGame().getWidth() / 2 - 140, handler.getGame().getHeight()-100, 280, 50, () -> {
            isUIManagerActive = false;
            handler.getGame().menuState.setUIManagerActive(true);
            State.setState(handler.getGame().menuState);
            audioPlayer.stop();
        }, "Return To Menu"));
        audioPlayer = new AudioPlayer("/sound/lose_state.wav");
    }

    @Override
    public void tick() {
        if (isUIManagerActive){
            uiManager.tick();
        }
        if (handler.getKeyManager().esc){
            handler.getGame().menuState.setUIManagerActive(true);
            State.setState(handler.getGame().menuState);
            audioPlayer.stop();
        }
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);

        if (isUIManagerActive){
            uiManager.render(g);
        }
        Font fnt1 = new Font("Comic Sans MS", Font.BOLD, 35);
        g.setColor(Color.red);
        g.setFont(fnt1);
        g.drawString("You Lose!", handler.getWidth()/2-100, handler.getHeight()/2 - 50);

        g.drawImage(Assets.pikachu_sad, handler.getWidth()/2-105, handler.getHeight()/2 , 210, 140, null);

    }
    public UIManager getUiManager() {
        return uiManager;
    }

}
