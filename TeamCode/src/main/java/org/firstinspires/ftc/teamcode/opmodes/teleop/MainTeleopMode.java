package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.bylazar.gamepad.GamepadManager;
import com.bylazar.gamepad.PanelsGamepad;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name = "mainTeleop")
public class MainTeleopMode extends OpMode {
    TelemetryManager panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
    // Assuming com.bylazar.gamepad.PanelsGamepad is for managers
    GamepadManager g1 = PanelsGamepad.INSTANCE.getFirstManager();
    GamepadManager g2 = PanelsGamepad.INSTANCE.getSecondManager();

    DriveSubsystem drive_base = new DriveSubsystem();

    @Override
    public void init() {
        drive_base.initialize(hardwareMap);
    }

    @Override
    public void loop() {
        drive_base.handleControllerInput(gamepad1);
//        drive_base.updateTelemetry();
        if (gamepad1.backWasPressed()) {
            drive_base.resetHeading();
        }
        updateGamepadTelemetry();
    }

    private void updateGamepadTelemetry() {
        Gamepad g11 = g1.asCombinedFTCGamepad(gamepad1);
        Gamepad g22 = g2.asCombinedFTCGamepad(gamepad2);

        panelsTelemetry.addLine("==== Buttons ====");
        panelsTelemetry.addLine("A: " + g11.a);
        panelsTelemetry.addLine("B: " + g11.b);
        panelsTelemetry.addLine("X: " + g11.x);
        panelsTelemetry.addLine("Y: " + g11.y);
        panelsTelemetry.addLine("DPad Up: " + g11.dpad_up);
        panelsTelemetry.addLine("DPad Down: " + g11.dpad_down);
        panelsTelemetry.addLine("DPad Left: " + g11.dpad_left);
        panelsTelemetry.addLine("DPad Right: " + g11.dpad_right);
        panelsTelemetry.addLine("Left Bumper: " + g11.left_bumper);
        panelsTelemetry.addLine("Right Bumper: " + g11.right_bumper);
        panelsTelemetry.addLine("Left Trigger: " + g11.left_trigger);
        panelsTelemetry.addLine("Right Trigger: " + g11.right_trigger);
        panelsTelemetry.addLine("Start / Options: " + g11.options);
        panelsTelemetry.addLine("Back / Share: " + g11.back);
        panelsTelemetry.addLine("Guide / PS: " + g11.guide);
        panelsTelemetry.addLine("Touchpad: " + g11.touchpad);
        panelsTelemetry.addLine("Left Stick Button: " + g11.left_stick_button);
        panelsTelemetry.addLine("Right Stick Button: " + g11.right_stick_button);

        panelsTelemetry.addLine("==== Sticks ====");
        panelsTelemetry.addLine("Left Stick X: " + g11.left_stick_x);
        panelsTelemetry.addLine("Left Stick Y: " + g11.left_stick_y);
        panelsTelemetry.addLine("Right Stick X: " + g11.right_stick_x);
        panelsTelemetry.addLine("Right Stick Y: " + g11.right_stick_y);

        panelsTelemetry.addLine("==== Buttons ====");
        panelsTelemetry.addLine("A: " + g22.a);
        panelsTelemetry.addLine("B: " + g22.b);
        panelsTelemetry.addLine("X: " + g22.x);
        panelsTelemetry.addLine("Y: " + g22.y);
        panelsTelemetry.addLine("DPad Up: " + g22.dpad_up);
        panelsTelemetry.addLine("DPad Down: " + g22.dpad_down);
        panelsTelemetry.addLine("DPad Left: " + g22.dpad_left);
        panelsTelemetry.addLine("DPad Right: " + g22.dpad_right);
        panelsTelemetry.addLine("Left Bumper: " + g22.left_bumper);
        panelsTelemetry.addLine("Right Bumper: " + g22.right_bumper);
        panelsTelemetry.addLine("Left Trigger: " + g22.left_trigger);
        panelsTelemetry.addLine("Right Trigger: " + g22.right_trigger);
        panelsTelemetry.addLine("Start / Options: " + g22.options);
        panelsTelemetry.addLine("Back / Share: " + g22.back);
        panelsTelemetry.addLine("Guide / PS: " + g22.guide);
        panelsTelemetry.addLine("Touchpad: " + g22.touchpad);
        panelsTelemetry.addLine("Left Stick Button: " + g22.left_stick_button);
        panelsTelemetry.addLine("Right Stick Button: " + g22.right_stick_button);

        panelsTelemetry.addLine("==== Sticks ====");
        panelsTelemetry.addLine("Left Stick X: " + g22.left_stick_x);
        panelsTelemetry.addLine("Left Stick Y: " + g22.left_stick_y);
        panelsTelemetry.addLine("Right Stick X: " + g22.right_stick_x);
        panelsTelemetry.addLine("Right Stick Y: " + g22.right_stick_y);

        panelsTelemetry.update(telemetry);

    }
}
