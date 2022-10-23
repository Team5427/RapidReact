package frc.robot.commands.complex;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;

public class TargetPID extends CommandBase{
    private boolean CW;
    private double tolerance;
    private DriveTrain dt;
    private Limelight ll;
    private PIDController pid;
    private double yaw;
    public TargetPID(boolean CW, double toleranceDeg) {
        dt = RobotContainer.getDriveTrain();
        ll = RobotContainer.getLimeLight();
        addRequirements(dt);
        this.CW = CW;
        this.tolerance = toleranceDeg;
        // pid = new PIDController(Constants.TARGETING_P, 0, 0);
        pid = new PIDController(Constants.TARGETING_P, 0, 0); //TUNE WITH THIS
    }

    @Override
    public void initialize() {
        pid.setSetpoint(0);
        pid.setTolerance(tolerance, 0.5);
    }

    @Override
    public void execute() {
        double calc;
        if (ll.targetVisible()) {
            yaw = ll.targetX();
            calc = pid.calculate(yaw);
        } else {
            calc = CW ? -0.8 : 0.8;
        }
        dt.moveRight(calc);
        dt.moveLeft(-calc);
    }

    @Override
    public boolean isFinished() {
        if (ll.targetVisible()) {
            return pid.atSetpoint();
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        pid.reset();
    }

}
