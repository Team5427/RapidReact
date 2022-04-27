package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase{

    private MotorControllerGroup left, right;
    private DifferentialDrive drive;
    private SlewRateLimiter xLimiter = new SlewRateLimiter(.10);
    private SlewRateLimiter zLimiter = new SlewRateLimiter(.10);

    public DriveTrain(MotorControllerGroup left, MotorControllerGroup right, DifferentialDrive drive){
        this.left = left;
        this.right = right;
        this.drive = drive;
    }

    public void moveLeft(double speed){
        left.set(speed);
    }

    public void moveRight(double speed){
        right.set(speed);
    }

    public void driveWithJoystick(Joystick joy){

        drive.arcadeDrive(xLimiter.calculate(SmartDashboard.getEntry("/SmartDashboard/x").getDouble(0)), zLimiter.calculate(SmartDashboard.getEntry("/SmartDashboard/z").getDouble(0)));
    }

    public void stop(){
        left.stopMotor();
        right.stopMotor();
    }
}
