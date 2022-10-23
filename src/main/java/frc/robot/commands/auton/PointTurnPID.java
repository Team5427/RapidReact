package frc.robot.commands.auton;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class PointTurnPID extends CommandBase {

    ProfiledPIDController pid = new ProfiledPIDController(0, 0, 0, new Constraints(180, 90));
    double curRot;
    DriveTrain dt = RobotContainer.getDriveTrain();
    double m_setPointDeg;

    public PointTurnPID(double setPointDeg) {
        addRequirements(RobotContainer.getDriveTrain());
        this.m_setPointDeg = setPointDeg;
    }

    @Override
    public void initialize() {
        pid.enableContinuousInput(-180, 180);
        pid.setTolerance(5);
    }

    @Override
    public void execute() {
        curRot = dt.getPose().getRotation().getDegrees();
        dt.moveLeft(pid.calculate(curRot, m_setPointDeg));
        dt.moveRight(-pid.calculate(curRot, m_setPointDeg));
    }

    @Override
    public boolean isFinished() {
        if (pid.atSetpoint()) { 
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void end(boolean interruptible) {
        pid.reset(0);
    }
}
