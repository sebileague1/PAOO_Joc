package State;
import Game.Handler;
import UI.UIButton;
import UI.UIManager;
import java.awt.*;
import static State.Utils.Levels.GetLevelName;

public class IntermediateState extends State {
    private UIManager uiManager;

    public IntermediateState(Handler handler) {

        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;
        uiManager.addObject(new UIButton(handler.getGame().getWidth() / 2 - 105, handler.getGame().getHeight() - 100, 210, 50, () -> {
            isUIManagerActive = false;
            handler.getGame().gameState.startNextLevel();
            State.setState(handler.getGame().gameState);
        }, "Next Level"));
    }

    @Override
    public void tick() {
        if (isUIManagerActive) {
            uiManager.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        if (isUIManagerActive) {
            uiManager.render(g);
        }
        Font fnt1 = new Font("Comic Sans MS", Font.BOLD, 25);
        g.setColor(Color.cyan);
        g.setFont(fnt1);
        String text = GetLevelName(GameState.current_level) + " passed";
        int width = g.getFontMetrics().stringWidth(text);
        g.drawString(text, handler.getWidth() / 2 - width/2, handler.getHeight() / 2 - 50);
    }

    public UIManager getUiManager() {
        return uiManager;
    }

}
