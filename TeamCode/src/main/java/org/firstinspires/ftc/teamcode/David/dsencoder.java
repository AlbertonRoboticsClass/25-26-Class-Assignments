package org.firstinspires.ftc.teamcode.David;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="David Drive Encoder", group="Exercises")

public class dsencoder extends LinearOpMode{
    DcMotor leftFrontDrive;
    DcMotor rightRearDrive;
    DcMotor leftRearDrive;
    DcMotor rightFrontDrive;

    @Override
    public void runOpMode() {
        leftFrontDrive = hardwareMap.dcMotor.get("leftFrontDrive");
        leftRearDrive = hardwareMap.dcMotor.get("leftRearDrive");
        rightFrontDrive = hardwareMap.dcMotor.get("rightFrontDrive");
        rightRearDrive = hardwareMap.dcMotor.get("rightRearDrive");

        leftRearDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightRearDrive.setDirection(DcMotor.Direction.REVERSE);
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);

        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFrontDrive.setTargetPosition(3000);
        rightFrontDrive.setTargetPosition(3000);
        leftRearDrive.setTargetPosition(3000);
        rightRearDrive.setTargetPosition(3000);

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Mode", "waiting");
        telemetry.update();



        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();



        leftFrontDrive.setPower(0.25);
        rightFrontDrive.setPower(0.25);
        leftRearDrive.setPower(0.25);
        rightRearDrive.setPower(0.25);


        while (opModeIsActive() && leftFrontDrive.isBusy())
        {
            telemetry.addData("encoder-fwd-left", leftFrontDrive.getCurrentPosition() + "  busy=" + leftFrontDrive.isBusy());
            telemetry.addData("encoder-fwd-right", rightFrontDrive.getCurrentPosition() + "  busy=" + rightRearDrive.isBusy());
            telemetry.addData("encoder-fwd-leftRear", leftRearDrive.getCurrentPosition() + "  busy=" + leftRearDrive.isBusy());
            telemetry.addData("encoder-fwd-rightRear", rightRearDrive.getCurrentPosition() + "  busy=" + rightRearDrive.isBusy());
            telemetry.update();
            idle();
        }



        leftFrontDrive.setPower(0.0);
        rightRearDrive.setPower(0.0);
        leftFrontDrive.setPower(0.0);
        rightRearDrive.setPower(0.0);


        resetStartTime();

        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-fwd-left-end", leftFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-fwd-right-end", rightFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-fwd-left-end", leftRearDrive.getCurrentPosition());
            telemetry.addData("encoder-fwd-right-end", rightRearDrive.getCurrentPosition());
            telemetry.update();
            idle();
        }


        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFrontDrive.setTargetPosition(0);
        rightFrontDrive.setTargetPosition(0);
        leftRearDrive.setTargetPosition(0);
        rightRearDrive.setTargetPosition(0);

        leftFrontDrive.setPower(-0.25);
        rightFrontDrive.setPower(-0.25);
        leftRearDrive.setPower(-0.25);
        rightRearDrive.setPower(-0.25);

        while (opModeIsActive() && leftFrontDrive.getCurrentPosition() > leftFrontDrive.getTargetPosition()) {
            leftFrontDrive.getCurrentPosition();
            leftFrontDrive.getTargetPosition();
        }
        while (opModeIsActive() && leftRearDrive.getCurrentPosition() > leftRearDrive.getTargetPosition())
            leftRearDrive.getCurrentPosition();
        leftRearDrive.getTargetPosition();
        {
            telemetry.addData("encoder-back-left", leftFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-back-right", rightFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-back-left", leftRearDrive.getCurrentPosition());
            telemetry.addData("encoder-back-right", rightRearDrive.getCurrentPosition());

            telemetry.update();
            idle();
        }



        leftFrontDrive.setPower(0.0);
        rightFrontDrive.setPower(0.0);
        leftRearDrive.setPower(0.0);
        rightRearDrive.setPower(0.0);

        resetStartTime();

        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-back-left-end", leftFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-back-right-end", rightFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-back-left-end", leftRearDrive.getCurrentPosition());
            telemetry.addData("encoder-back-right-end", rightRearDrive.getCurrentPosition());
            telemetry.update();
            idle();
        }
    }

    private void resetStartTime() {
    }
}
