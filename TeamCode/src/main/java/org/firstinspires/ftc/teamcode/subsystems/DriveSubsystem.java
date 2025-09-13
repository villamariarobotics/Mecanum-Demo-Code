package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem.DriveConstants.FieldOriented;
import static org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem.DriveConstants.reverseDirections;

//import androidx.annotation.NonNull;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class DriveSubsystem {
    private static IMU gyro;
    static DcMotor left_front_motor;
    static DcMotor left_back_motor;
    static DcMotor right_front_motor;
    static DcMotor right_back_motor;

    @Configurable
public static class DriveConstants {
    static Boolean reverseDirections = false;
    static Boolean FieldOriented = false;
    static double SpeedModifier = 1.0;
}



    public static void initialize(HardwareMap hwMap) {
        left_front_motor = hwMap.get(DcMotor.class, "left_front_motor");
        right_front_motor = hwMap.get(DcMotor.class, "right_front_motor");
        left_back_motor = hwMap.get(DcMotor.class, "left_back_motor");
        right_back_motor = hwMap.get(DcMotor.class, "right_back_motor");
        gyro = hwMap.get(IMU.class, "gyro");
        gyro.initialize(
                new IMU.Parameters(
                        new RevHubOrientationOnRobot(
                                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                                RevHubOrientationOnRobot.UsbFacingDirection.LEFT
                        )
                )
        );

        if (!reverseDirections) {
            right_front_motor.setDirection(DcMotorSimple.Direction.REVERSE);
            right_back_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            left_front_motor.setDirection(DcMotorSimple.Direction.REVERSE);
            left_back_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }
    public void handleControllerInput(Gamepad gamepad) {
        // Mecanum drive is controlled with three axes: drive (front-and-back),
        // strafe (left-and-right), and twist (rotating the whole chassis).
        double drive  = gamepad.left_stick_y;
        double strafe = gamepad.left_stick_x;
        double twist  = gamepad.right_stick_x;
        double denominator = Math.max(Math.abs(drive) + Math.abs(strafe) + Math.abs(twist), 1);

        if (!FieldOriented) {
            //robot-oriented
            double left_front_power = DriveConstants.SpeedModifier * (drive + strafe + twist) / denominator;
            double right_front_power = DriveConstants.SpeedModifier * (drive - strafe - twist) / denominator;
            double left_back_power = DriveConstants.SpeedModifier * (drive - strafe + twist) / denominator;
            double right_back_power = DriveConstants.SpeedModifier * (drive + strafe - twist) / denominator;

            left_front_motor.setPower(left_front_power);
            right_front_motor.setPower(right_front_power);
            left_back_motor.setPower(left_back_power);
            right_back_motor.setPower((right_back_power));
        } else {
            //field-oriented
            double botHeading = getHeading();
            // Rotate the movement direction counter to the bot's rotation
            double rotX = drive * Math.cos(-botHeading) - strafe * Math.sin(-botHeading);
            double rotY = drive * Math.sin(-botHeading) + strafe * Math.cos(-botHeading);
            double frontLeftPower = DriveConstants.SpeedModifier * (rotY + rotX + twist) / denominator;
            double backLeftPower = DriveConstants.SpeedModifier * (rotY - rotX + twist) / denominator;
            double frontRightPower = DriveConstants.SpeedModifier * (rotY - rotX - twist) / denominator;
            double backRightPower = DriveConstants.SpeedModifier * (rotY + rotX - twist) / denominator;
            // set the power of the motors
            left_front_motor.setPower(frontLeftPower);
            left_back_motor.setPower(backLeftPower);
            right_front_motor.setPower(frontRightPower);
            right_back_motor.setPower(backRightPower);

        }
    }

    public void updateTelemetry() {
    }

    public static double getHeading() {
        return gyro.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }

    public void resetHeading() {
        gyro.resetYaw();
    }
}
