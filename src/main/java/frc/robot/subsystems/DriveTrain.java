package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase{
    public MotorControllerGroup left, right;
    public DifferentialDrive drive;
    public CANSparkMax leftTop;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

    public DriveTrain(MotorControllerGroup left, MotorControllerGroup right, DifferentialDrive drive, CANSparkMax leftTop){
        this.left = left;
        this.right = right;
        this.drive = drive;
        this.leftTop = leftTop;
    }

    public void takeJoystickInputs(Joystick joy){
        drive.arcadeDrive(joy.getY(), joy.getZ());
    }

    public void setLeft(double speed){
        left.set(speed);
    }

    public void setRight(double speed){
        right.set(speed);
    }

    public void stop(){
        right.stopMotor();
        left.stopMotor();
    }

    public void drivePIDInit(){
        leftTop.getEncoder().setPositionConversionFactor(.479);

        kP = 0.00000; 
        kI = 0.0000000;
        kD = 0; 
        kIz = 0; 
        kFF = 0.000015; 
        kMaxOutput = 1; 
        kMinOutput = -1;
        maxRPM = 5700;
    
        // set PID coefficients
        getPIDController().setP(kP);
        getPIDController().setI(kI);
        getPIDController().setD(kD);
        getPIDController().setIZone(kIz);
        getPIDController().setFF(kFF);
        getPIDController().setOutputRange(kMinOutput, kMaxOutput);
    }
    public SparkMaxPIDController getPIDController(){
        return leftTop.getPIDController();
    }

    public double getDistance(){
        return leftTop.getEncoder().getPosition();
    }
    
}
