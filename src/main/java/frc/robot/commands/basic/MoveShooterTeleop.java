package frc.robot.commands.basic;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveShooterTeleop extends CommandBase {
    public static double setPointFinal;

    public MoveShooterTeleop() {
        addRequirements(RobotContainer.getShooter());
    }

    @Override
    public void initialize() {

        RobotContainer.getShooter().shooterInitRight();
    }

    @Override
    public void execute() {

        double dial = RobotContainer.getJoy().getRawAxis(3);
        dial = (dial * 3000) + 3000;
        SmartDashboard.putNumber("SETPOINT NEW", dial / 60);

        RobotContainer.getShooter().moveShooterSydID(dial / 60);

    }

    @Override
    public boolean isFinished() {
        return !RobotContainer.getJoy().getRawButton(Constants.MANUAL_SHOOTER_BUTTON);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getShooter().stop();
    }
}