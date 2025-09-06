package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

public class MainTeleopMode extends OpMode {

    DriveSubsystem drive_base = new DriveSubsystem();

    @Override
    public void init() {
        drive_base.initialize(hardwareMap);
    }

    @Override
    public void loop() {
        drive_base.handleControllerInput(gamepad1);
        drive_base.updateTelemetry();
    }
}
