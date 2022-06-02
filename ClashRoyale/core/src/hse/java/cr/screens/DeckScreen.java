package hse.java.cr.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import hse.java.cr.Assets;
import hse.java.cr.Starter;
import hse.java.cr.buttons.UIButton;
import hse.java.cr.model.CardInfo;

public class DeckScreen implements Screen {
    private MainScreen mainScreen;
    private SpriteBatch batch;
    private Assets assets;
    private final Starter game;
    private OrthographicCamera camera;
    private Sprite gameBackground;
    private CardInfo cardInfos;
    private UIButton backButton;
    private UIButton leftButton;
    private UIButton rightButton;
    private UIButton addButton;
    private UIButton deleteButton;
    private Stage stage;

    public DeckScreen(@NotNull Starter game, MainScreen screen) {
        this.game = game;
        mainScreen = screen;
    }

    @Override
    public void show() {
        stage = new Stage();
        assets = game.getAssets();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        cardInfos = new CardInfo();
        cardInfos.add(assets.get(Assets.fire), "Fire", "Spell", 0, 0, 0);
        cardInfos.add(assets.get(Assets.grayGolem), "GrayGolem", "Character", 0, 0, 0);
        cardInfos.add(assets.get(Assets.greenGoblin), "GreenGoblin", "Character", 0, 0, 0);
        cardInfos.add(assets.get(Assets.greenGolem), "GreenGolem", "Character", 0, 0, 0);
        cardInfos.add(assets.get(Assets.brownGolem), "BrownGolem", "Character", 0, 0, 0);
        gameBackground = new Sprite(assets.get(Assets.gameBackground));
        gameBackground.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backButton = new UIButton("Play", assets);
        leftButton = new UIButton("Play", assets);
        rightButton = new UIButton("Play", assets);
        addButton = new UIButton("Play", assets);
        deleteButton = new UIButton("Play", assets);
        rightButton = new UIButton("Play", assets);
        addButton.setBounds(3 * Gdx.graphics.getWidth() / 10, 0, Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        deleteButton.setBounds( 3 * Gdx.graphics.getWidth() / 10, 2 * Gdx.graphics.getHeight() / 10, Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        leftButton.setBounds(0, 0, Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        rightButton.setBounds( 6 * Gdx.graphics.getWidth() / 10, 0, Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        backButton.setBounds( 0, 8.7f * Gdx.graphics.getHeight() / 10, Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);

        stage.addActor(cardInfos);
        stage.addActor(backButton);
        stage.addActor(leftButton);
        stage.addActor(rightButton);
        stage.addActor(addButton);
        stage.addActor(deleteButton);
        Gdx.input.setInputProcessor(stage);

        stage.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                if (backButton.getState().equals(UIButton.State.HOVERED)) {
                    backButton.setState(UIButton.State.PRESSED);
                    backButton.playSound();
                }
                if (leftButton.getState().equals(UIButton.State.HOVERED)) {
                    leftButton.setState(UIButton.State.PRESSED);
                    leftButton.playSound();
                }
                if (rightButton.getState().equals(UIButton.State.HOVERED)) {
                    rightButton.setState(UIButton.State.PRESSED);
                    rightButton.playSound();
                }
                if (addButton.getState().equals(UIButton.State.HOVERED)) {
                    addButton.setState(UIButton.State.PRESSED);
                    addButton.playSound();
                }
                if (deleteButton.getState().equals(UIButton.State.HOVERED)) {
                    deleteButton.setState(UIButton.State.PRESSED);
                    deleteButton.playSound();
                }
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.8f, 0.8f, 0.8f, 1f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.enableBlending(); // Enable alpha
        batch.begin();

        gameBackground.setAlpha(0.6f);
        gameBackground.draw(batch);
        batch.end();

        stage.draw();
        if (backButton.getState().equals(UIButton.State.PRESSED)) {
            game.setScreen(mainScreen);
        }
        if (leftButton.getState().equals(UIButton.State.PRESSED)) {
            leftButton.setState(UIButton.State.NORMAL);
            cardInfos.setIndex((cardInfos.getIndex() - 1 + cardInfos.getSize()) % cardInfos.getSize());
        }
        if (rightButton.getState().equals(UIButton.State.PRESSED)) {
            rightButton.setState(UIButton.State.NORMAL);
            cardInfos.setIndex((cardInfos.getIndex() + 1) % cardInfos.getSize());
        }
        if (addButton.getState().equals(UIButton.State.PRESSED)) {
            addButton.setState(UIButton.State.NORMAL);
            cardInfos.addCard(cardInfos.getIndex());
        }
        if (deleteButton.getState().equals(UIButton.State.PRESSED)) {
            deleteButton.setState(UIButton.State.NORMAL);
            cardInfos.deleteCard(cardInfos.getIndex());
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
