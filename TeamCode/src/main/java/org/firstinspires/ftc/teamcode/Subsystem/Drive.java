package org.firstinspires.ftc.teamcode.Subsystem;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.hardware.DcMotor;
public class Drive {

    private DcMotor BL; // R
    private DcMotor FL; // R
    private DcMotor FR;
    private DcMotor BR;

    private double speed;
    private double turnMult = .9;
    private double slowMult = 0.4;
    private double fastMult = 1;


    public Drive(HardwareMap hardwareMap){
        BL = hardwareMap.get(DcMotor.class, "BL");
        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BL.setDirection(DcMotor.Direction.REVERSE);

        FL = hardwareMap.get(DcMotor.class, "FL");
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setDirection(DcMotorSimple.Direction.REVERSE);

        BR = hardwareMap.get(DcMotor.class, "BR");
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setDirection(DcMotor.Direction.FORWARD);

        FR = hardwareMap.get(DcMotor.class, "FR");
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void updateDrive(Gamepad gamepad){
        BL.setPower((turnMult * gamepad.right_stick_x * speed) + (speed * gamepad.left_stick_x) + (speed * gamepad.left_stick_y));
        BR.setPower((turnMult * gamepad.right_stick_x * -speed) + (-speed * gamepad.left_stick_x) + (speed * gamepad.left_stick_y));

        FL.setPower((turnMult * gamepad.right_stick_x * speed) + (-speed * gamepad.left_stick_x) + (speed * gamepad.left_stick_y));
        FR.setPower((turnMult * gamepad.right_stick_x * -speed) + (speed * gamepad.left_stick_x) + (speed * gamepad.left_stick_y));


        if (gamepad.leftStickButtonWasPressed())
            speed = slowMult;
        else if (gamepad.rightStickButtonWasPressed())
            speed = fastMult;
        else
            speed = .725;
    }
}
