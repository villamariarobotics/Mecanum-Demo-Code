package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem {

    static DcMotor left_front_motor;
    static DcMotor left_back_motor;
    static DcMotor right_front_motor;
    static DcMotor right_back_motor;
    static Boolean reverseDirections = false;
    static Boolean FieldOriented = false;


    public static void initialize(HardwareMap hwMap) {
        left_front_motor = hwMap.get(DcMotor.class, "left_front_motor");
        right_front_motor = hwMap.get(DcMotor.class, "right_front_motor");
        left_back_motor = hwMap.get(DcMotor.class, "left_back_motor");
        right_back_motor = hwMap.get(DcMotor.class, "right_back_motor");
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

        if (!FieldOriented) {
            //robot-oriented
            left_front_motor.setPower(drive + strafe + twist);
            right_front_motor.setPower(drive - strafe - twist);
            left_back_motor.setPower(drive - strafe + twist);
            right_back_motor.setPower(drive + strafe - twist);
        } else {
            //field-oriented
            //! TO BE IMPLEMENTED WHEN NOUR SENDS ME THE CODE
        }
    }
}
