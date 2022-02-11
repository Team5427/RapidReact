package frc.robot.commands.auto;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveForwardPID extends CommandBase{
    public double distance;
    public double startingAngle;
    public double tolerance, correction;
    public double startPosition;
    public PIDController pid;

    public double kp, ki,kd;

    public void MoveForwardPID(double distance){
        addRequirements(RobotContainer.getDriveTrain());
        this.distance = distance;
        kp = 0;
        ki = 0;
        kd = 0;
        pid = new PIDController(kp, ki, kd);
    }

    @Override
    public void initialize(){
        startingAngle = RobotContainer.getAHRS().getYaw();
        tolerance = 5;
        correction = .05;
        startPosition = RobotContainer.getDriveTrain().getDistance(); // distance in meters
    }

    @Override
    public void execute(){
        RobotContainer.getDriveTrain().getLeft().set(pid.calculate(RobotContainer.getAHRS().getYaw(), startPosition));
        RobotContainer.getDriveTrain().getRight().set(Constants.AUTONOMOUS_DRIVE_SPEED);
    }

    @Override
    public boolean isFinished(){
        return RobotContainer.getDriveTrain().getDistance() - startPosition >= distance;
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getDriveTrain().stop();
    }


   
}
