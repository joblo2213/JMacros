package io.github.joblo2213.JMacros.core.ui.overlay;

import io.github.joblo2213.JMacros.api.Profile;
import io.github.joblo2213.JMacros.core.JMacros;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Overlay {

    public static final Logger logger = LoggerFactory.getLogger(Overlay.class);

    private final Stage stage;
    private final DoubleProperty scale;

    public Overlay(Stage stage, double scale) {
        this.stage = stage;
        this.scale = new SimpleDoubleProperty();
        setScale(scale);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setAlwaysOnTop(true);
        stage.setTitle(JMacros.APPLICATION_NAME);
        stage.getIcons().add(new Image(getClass().getResourceAsStream(JMacros.APPLICATION_ICON_URL)));
    }

    public void setScale(double value) {
        scale.set(value);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setX((screen.getWidth() - scale.get() * 15) / 2);
        stage.setY((screen.getHeight() - scale.get() * 2));
    }

    public void setProfile(Profile profile) {
        logger.debug("Now displaying profile {}", profile.getName());
        stage.setScene(new ProfileScene(profile, scale));
    }

    public void show() {
        logger.debug("Showing overlay");
        stage.show();
    }

    public void hide() {
        logger.debug("Hiding overlay");
        stage.hide();
    }

}
