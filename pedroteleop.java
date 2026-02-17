@TeleOp(name = "PedroTeleOp", group = "Drive")
public class PedroTeleOp extends OpMode {

    PedroDrive drive;   // your Pedro drive class

    @Override
    public void init() {
        drive = new PedroDrive(hardwareMap);

        // USE YOUR PEDRO TUNER VALUES
        drive.setTranslationalPID(
                PedroConstants.TRANSLATIONAL_kP,
                PedroConstants.TRANSLATIONAL_kI,
                PedroConstants.TRANSLATIONAL_kD
        );

        drive.setHeadingPID(
                PedroConstants.HEADING_kP,
                PedroConstants.HEADING_kI,
                PedroConstants.HEADING_kD
        );
    }

    @Override
    public void loop() {

        // joystick → velocity targets
        double forward = -gamepad1.left_stick_y;
        double strafe  = -gamepad1.left_stick_x;
        double turn    = -gamepad1.right_stick_x;

        // field-centric optional (if Pedro supports it)
        PoseVelocity targetVelocity = new PoseVelocity(
                forward,
                strafe,
                turn
        );

        // send target to Pedro
        drive.setWeightedDrivePower(targetVelocity);

        // run PID update
        drive.update();

        telemetry.addData("X", drive.getPose().x);
        telemetry.addData("Y", drive.getPose().y);
        telemetry.addData("Heading", drive.getPose().heading);
    }
}
