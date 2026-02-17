@TeleOp(name = "PedroTeleOp", group = "Drive")
public class PedroTeleOp extends OpMode {

    PedroDrive drive;   
    @Override
    public void init() {
        drive = new PedroDrive(hardwareMap);

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

        double forward = -gamepad1.left_stick_y;
        double strafe  = -gamepad1.left_stick_x;
        double turn    = -gamepad1.right_stick_x;

        PoseVelocity targetVelocity = new PoseVelocity(
                forward,
                strafe,
                turn
        );

        drive.setWeightedDrivePower(targetVelocity);

        drive.update();

        telemetry.addData("X", drive.getPose().x);
        telemetry.addData("Y", drive.getPose().y);
        telemetry.addData("Heading", drive.getPose().heading);
    }
}
