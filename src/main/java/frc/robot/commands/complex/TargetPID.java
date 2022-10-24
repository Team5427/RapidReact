package frc.robot.commands.complex;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;

public class TargetPID extends CommandBase{ //FIXME
    private boolean CW;
    private double tolerance;
    private DriveTrain dt;
    private Limelight ll;
    private ProfiledPIDController pid;
    private double yaw;
    private double maxOutput = Constants.TARGETING_MAX_SPEED;
    private boolean isAuto;
    private Button boundBtn;
    public TargetPID(boolean CW, double toleranceDeg, boolean isAuto, Button boundBtn) {
        dt = RobotContainer.getDriveTrain();
        ll = RobotContainer.getLimeLight();
        addRequirements(dt);
        this.CW = CW;
        this.tolerance = toleranceDeg;
        pid = new ProfiledPIDController(0.04, 0, 0, new Constraints(480, 700)); //TUNE WITH THIS
        this.isAuto = isAuto;
        this.boundBtn = boundBtn;

    }

    @Override
    public void initialize() {
        pid.setGoal(new State(0, 0));
        pid.setTolerance(tolerance, 0);
    }

    @Override
    public void execute() {
        double calc;
        if (ll.targetVisible()) {
            yaw = ll.targetX();
            calc = pid.calculate(yaw);
        } else {
            calc = CW ? -maxOutput : maxOutput;
        }
        calc = Math.abs(calc) >= maxOutput ? maxOutput * Math.signum(calc) : calc; //limits max output to .8
        dt.moveRight(calc);
        dt.moveLeft(-calc);
    }

    @Override
    public boolean isFinished() {
        if (ll.targetVisible() && pid.atGoal()) {
            return true;
        } else if (!boundBtn.get() && !isAuto) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        pid.reset(yaw);
    }

}
