package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class MoveShooterTeleop extends CommandBase
{
    private double speed;
    public static double lsetPoint;
    public static double rsetPoint;
    public static double setPointFinal;
    
    public MoveShooterTeleop(double speed)
    {
        addRequirements(RobotContainer.getShooter());
        this.speed = speed;
    }

    @Override
    public void initialize() {

        lsetPoint = 0;
        rsetPoint = 0;
        setPointFinal = SmartDashboard.getNumber("Change RPI", 4560);
        RobotContainer.getShooter().shooterInitRight();
    }

    @Override
    public void execute() {

        // if (RobotContainer.getShooter().getRightEnc().getVelocity() >= -50 && RobotContainer.getShooter().getRightEnc().getVelocity() <= 50) {
        //     //setPointFinal = ((-RobotContainer.getJoy().getAxisType(3) + 1) * 6000);
        // }
        

        // if (RobotContainer.getShooter().getRightEnc().getVelocity() <= setPointFinal && RobotContainer.getJoy().getRawButton(1) && lsetPoint < setPointFinal) {
        //     lsetPoint = lsetPoint + setPointFinal/75;
        // } else if (lsetPoint >= setPointFinal && RobotContainer.getJoy().getRawButton(1)) {
        //     lsetPoint = setPointFinal;
        // } else if (!RobotContainer.getJoy().getRawButton(1)) {
        //     lsetPoint = 0;
        // }
        // if (RobotContainer.getShooter().getLeftEnc().getVelocity() <= setPointFinal && RobotContainer.getJoy().getRawButton(1) && rsetPoint < setPointFinal) {
        //     rsetPoint = rsetPoint + setPointFinal/75;
        // } else if (rsetPoint >= setPointFinal && RobotContainer.getJoy().getRawButton(1)) {
        //     rsetPoint = setPointFinal;
        // } else if (!RobotContainer.getJoy().getRawButton(1)) {
        //     rsetPoint = 0;
        // }
        
        // RobotContainer.getShooter().moveShooter(lsetPoint, rsetPoint);
        
        RobotContainer.getShooter().movePercent(.9);

        System.out.println("Shooter is running " + RobotContainer.getJoy().getRawButton(1));
    }

    @Override
    public boolean isFinished() {
        return !RobotContainer.getJoy().getRawButton(1);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getShooter().stop();
    }
}