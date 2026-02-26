// autonomous program that drives bot forward a set distance, stops then
// backs up to the starting point using encoders to measure the distance.

package org.firstinspires.ftc.teamcode.Josiah;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;





@Autonomous(name="Drive Encoder", group="Exercises")
//@Disabled
public class Chapter1016 extends LinearOpMode
{
    DcMotor leftFrontDrive;
    DcMotor leftRearDrive;
    DcMotor rightFrontDrive;
    DcMotor rightRearDrive;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException
    {
        leftFrontDrive = hardwareMap.dcMotor.get("leftFrontDrive");
        leftRearDrive = hardwareMap.dcMotor.get("leftRearDrive");
        rightFrontDrive = hardwareMap.dcMotor.get("rightFrontDrive");
        rightRearDrive = hardwareMap.dcMotor.get("rightRearDrive");

        // You will need to set this based on your robot's
        // gearing to get forward control input to result in
        // forward motion.
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftRearDrive.setDirection(DcMotor.Direction.REVERSE);
//---------------------------------------------------------------------------------------------


        // reset encoder counts kept by motors.
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set motors to run forward for 5000 encoder counts.
        leftFrontDrive.setTargetPosition(3000);
        leftRearDrive.setTargetPosition(3000);
        rightFrontDrive.setTargetPosition(3000);
        rightRearDrive.setTargetPosition(3000);

        // set motors to run to target encoder position and stop with brakes on.
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.
        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set all motors to 25% power. Movement will start. Sign of power is
        // ignored as sign of target encoder position controls direction when
        // running to position.
        leftFrontDrive.setPower(0.25);
        leftRearDrive.setPower(0.25);
        rightFrontDrive.setPower(0.25);
        rightRearDrive.setPower(0.25);

        // wait while opmode is active and left motor is busy running to position.
        while (opModeIsActive() && leftFrontDrive.isBusy())
        {
            telemetry.addData("encoder-fwd-front-left", leftFrontDrive.getCurrentPosition() + " busy=" + leftFrontDrive.isBusy());
            telemetry.addData("encoder-fwd-back-left", leftRearDrive.getCurrentPosition() + " busy=" + leftRearDrive.isBusy());
            telemetry.addData("encoder-fwd-front-right", rightFrontDrive.getCurrentPosition() + " busy=" + rightFrontDrive.isBusy());
            telemetry.addData("encoder-fwd-back-right", rightRearDrive.getCurrentPosition() + " busy=" + rightRearDrive.isBusy());
            telemetry.update();
            idle();
        }

        // set motor power to zero to turn off motors.
        leftFrontDrive.setPower(0.0);
        rightFrontDrive.setPower(0.0);
        leftRearDrive.setPower(0.0);
        rightRearDrive.setPower(0.0);

        // wait 5 sec to you can observe the final encoder position.
        runtime.reset();

        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-fwd-left-end", leftFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-fwd-right-end", rightFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-rear-left-end", leftRearDrive.getCurrentPosition());
            telemetry.addData("encoder-rear-right-end", rightRearDrive.getCurrentPosition());
            telemetry.update();
            idle();
        }

        // From current position back up to starting point.
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFrontDrive.setTargetPosition(0);
        leftRearDrive.setTargetPosition(0);
        rightFrontDrive.setTargetPosition(0);
        rightRearDrive.setTargetPosition(0);
        
        // Power sign matters again as we are running without encoder.
        leftFrontDrive.setPower(-0.25);
        rightFrontDrive.setPower(-0.25);
        leftRearDrive.setPower(-0.25);
        rightRearDrive.setPower(-0.25);

        while (opModeIsActive() && leftFrontDrive.getCurrentPosition() > leftFrontDrive.getTargetPosition())
        {
            telemetry.addData("encoder-back-front-left", leftFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-back-front-right", rightFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-back-rear-left", leftRearDrive.getCurrentPosition());
            telemetry.addData("encoder-back-rear-right", rightRearDrive.getCurrentPosition());
            telemetry.update();
            idle();
        }

        // set motor power to zero to stop motors.
        leftFrontDrive.setPower(0.0);
        rightFrontDrive.setPower(0.0);
        leftRearDrive.setPower(0.0);
        rightRearDrive.setPower(0.0);

        // wait 5 sec
        runtime.reset();
        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-back-left-end", leftFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-back-right-end", rightFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-back-rear-left-end", leftRearDrive.getCurrentPosition());
            telemetry.addData("encoder-back-rear-right-end", rightRearDrive.getCurrentPosition());
            telemetry.update();
            idle();
        }
    }
}