package com.gos.infinite_recharge.sd_widgets.super_structure;

import com.gos.infinite_recharge.sd_widgets.super_structure.data.ControlPanelData;
import com.gos.infinite_recharge.sd_widgets.super_structure.data.LiftData;
import com.gos.infinite_recharge.sd_widgets.super_structure.data.ShooterConveyorData;
import com.gos.infinite_recharge.sd_widgets.super_structure.data.ShooterIntakeData;
import com.gos.infinite_recharge.sd_widgets.super_structure.data.ShooterWheelsData;
import com.gos.infinite_recharge.sd_widgets.super_structure.data.WinchData;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

public class SuperStructureController {

    private static final double MAX_WIDTH = 200;
    private static final double MAX_HEIGHT = 200;

    @FXML
    private Group m_group;
    
    @FXML
    private Pane m_pane;

    @FXML
    public void initialize() {

        double minWidthMultiplier = 4;
        m_pane.setMinHeight(MAX_HEIGHT * minWidthMultiplier);
        m_pane.setMinWidth(MAX_WIDTH * minWidthMultiplier);

        DoubleBinding scaleBinding = Bindings.createDoubleBinding(() -> {
            double output = Math.min(m_pane.getWidth() / MAX_WIDTH, m_pane.getHeight() / MAX_HEIGHT);
            System.out.println(output);
            return 50.0;
            // return output;
    },m_pane.widthProperty(), m_pane.heightProperty());

        Scale scale = new Scale();
        scale.xProperty().bind(scaleBinding);
        scale.yProperty().bind(scaleBinding);

        m_group.getTransforms().add(scale);
    }

    public void updateShooterConveyor(ShooterConveyorData shooterConveyorData) {
    }

    public void updateShooterIntake(ShooterIntakeData shooterIntakeData) {
    }

    public void updateShooterWheels(ShooterWheelsData shooterWheelsData) {
    }

    public void updateControlPanel(ControlPanelData controlPanelData) {
    }

    public void updateWinch(WinchData winchData) {
    }

    public void updateLift(LiftData liftData) {
    }
}
