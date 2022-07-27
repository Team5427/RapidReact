package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;

public class Shooter extends SubsystemBase
{
    public CANSparkMax shooterMotorRight;
    private CANSparkMax shooterMotorLeft;
    private RelativeEncoder shooterRightEnc;
    private RelativeEncoder shooterLeftEnc;

    private PIDController SysIDTest;
    private SimpleMotorFeedforward SysIDFFTest;
    private double kp, ki, kd, ks, kv; 
    private SparkMaxPIDController pid_Right_ss;
    public double kP_Right, kI_Right, kD_Right, kIz_Right, kFF_Right, kMaxOutput_Right, kMinOutput_Right, maxRPM_Right;
    
    public Shooter(CANSparkMax shooterMotorRight, CANSparkMax shooterMotorLeft, RelativeEncoder Right, RelativeEncoder Left, SparkMaxPIDController pid_Right, SparkMaxPIDController pid_btm)
    {
        kp = 0.061118;
        ki = 0.0;
        kd = 0.0;
        ks = 0.071977;
        kv = 0.12174;
        this.shooterMotorRight = shooterMotorRight;
        this.shooterMotorLeft = shooterMotorLeft;
        shooterRightEnc = Right;
        shooterLeftEnc = Left;
        pid_Right_ss = pid_Right;
        SysIDTest = new PIDController(kp, ki, kd);
        SysIDFFTest = new SimpleMotorFeedforward(ks, kv);
    }

    public CANSparkMax getShooterMotorRight()
    {
        return shooterMotorRight;
    }

    public CANSparkMax getShooterMotorLeft()
    {
        return shooterMotorLeft;
    }

    public RelativeEncoder getRightEnc () 
    {
        return shooterRightEnc;
    }

    public RelativeEncoder getLeftEnc () 
    {
        return shooterLeftEnc;
    }

    public void shooterInitRight() {
    
        // kP_Right = 0.00000; 
        // kI_Right = 0.000000;
        // kD_Right = 0.000; 
        // kIz_Right = 0; 
        // kFF_Right = 0.00002; 
        // kMaxOutput_Right = 1; 
        // kMinOutput_Right = -1;
        // maxRPM_Right = 5874;

        // pid_Right_ss.setP(kP_Right);
        // pid_Right_ss.setI(kI_Right);
        // pid_Right_ss.setD(kD_Right);
        // pid_Right_ss.setIZone(kIz_Right);
        // pid_Right_ss.setFF(kFF_Right); //This is kV from SysID
        // pid_Right_ss.setOutputRange(kMinOutput_Right, kMaxOutput_Right);

        kp = 0.061118;
        ki = 0.0;
        kd = 0.0;
        ks = 0.071977;
        kv = 0.12174;

    }

    public void moveShooter(double tsetpoint)
    {
        pid_Right_ss.setReference(tsetpoint, CANSparkMax.ControlType.kVelocity);
        // shooterMotorLeft.set(shooterMotorRight.get());
        SmartDashboard.putNumber("Shooter Voltage", shooterMotorRight.getAppliedOutput());

    }

    public void moveShooterSydID(double setPoint_RPS) {
        shooterMotorRight.setVoltage(SysIDTest.calculate(shooterRightEnc.getVelocity()/60, setPoint_RPS) + SysIDFFTest.calculate(setPoint_RPS));
        SmartDashboard.putNumber("SysID output", SysIDTest.calculate(shooterRightEnc.getVelocity()/60, setPoint_RPS) + SysIDFFTest.calculate(setPoint_RPS));
        SmartDashboard.putString("PID FF values", SysIDTest.getP() + " " + SysIDTest.getI() + " " + SysIDTest.getD() + " " + SysIDFFTest.ks + " " + SysIDFFTest.kv + " ");
        // shooterMotorRight.setVoltage(3);
    }

    public void movePercent(double speed){
        SmartDashboard.putNumber("Shooter Voltage",shooterMotorRight.get());

        shooterMotorRight.set(speed);
        // shooterMotorLeft.set(speed);
    }

    public void moveDynamic() {
        pid_Right_ss.setReference(Robot.wantedSetPoint, CANSparkMax.ControlType.kVelocity);
    }

    public void stop()
    {
        shooterMotorLeft.set(0);
        shooterMotorRight.set(0);
    }

    public double getLidarSpeed(){
        //replace with lidar values
        return .5;
    }
}