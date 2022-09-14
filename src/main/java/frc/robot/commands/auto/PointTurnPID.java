package frc.robot.commands.auto;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class PointTurnPID extends CommandBase {

    ProfiledPIDController pid = new ProfiledPIDController(0, 0, 0, new Constraints(360, 180));
    double curRot;
    DriveTrain dt = RobotContainer.getDriveTrain();
    double m_setPointDeg;

    public PointTurnPID(double setPointDeg) {
        addRequirements(RobotContainer.getDriveTrain());
        this.m_setPointDeg = setPointDeg;
    }

    @Override
    public void initialize() {
        pid.enableContinuousInput(0, 360);
        curRot = RobotContainer.getAHRS().getRotation2d().getDegrees();
    }

    @Override
    public void execute() {
        curRot = RobotContainer.getAHRS().getRotation2d().getDegrees();
        curRot = curRot >= 0 ? (curRot % 360) : (360 - (curRot % 360));
        dt.moveLeft(pid.calculate((curRot), m_setPointDeg));
        dt.moveRight(-pid.calculate((curRot), m_setPointDeg));
    }
}
