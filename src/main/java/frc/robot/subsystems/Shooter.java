package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

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
    private double kp, ki, kd, ks, kv, ka; 
    private SparkMaxPIDController pid_Right_ss;
    public double kP_Right, kI_Right, kD_Right, kIz_Right, kFF_Right, kMaxOutput_Right, kMinOutput_Right, maxRPM_Right;
    
    public Shooter(CANSparkMax shooterMotorRight, CANSparkMax shooterMotorLeft, RelativeEncoder Right, RelativeEncoder Left, SparkMaxPIDController pid_Right, SparkMaxPIDController pid_btm)
    {
        kp = 0.068442;
        ki = 0.0;
        kd = 0.0;
        ks = .11899;
        kv = 0.12235;
        ka = 0.0032466;

        this.shooterMotorRight = shooterMotorRight;
        this.shooterMotorLeft = shooterMotorLeft;
        shooterRightEnc = Right;
        shooterLeftEnc = Left;
        pid_Right_ss = pid_Right;
        SysIDTest = new PIDController(kp, ki, kd);
        SysIDFFTest = new SimpleMotorFeedforward(ks, kv, ka);
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

        kp = 0.068442;
        ki = 0.0;
        kd = 0.0;
        ks = .11899;
        kv = 0.12235;
        ka = 0.0032466;

    }

    public void moveShooter(double tsetpoint)
    {
        pid_Right_ss.setReference(tsetpoint, CANSparkMax.ControlType.kVelocity);
        // shooterMotorLeft.set(shooterMotorRight.get());
        SmartDashboard.putNumber("Shooter Voltage", shooterMotorRight.getAppliedOutput());

    }

    public void moveShooterSydID(double setPoint_RPS) {
        shooterMotorRight.setVoltage(SysIDTest.calculate(shooterRightEnc.getVelocity()/60, setPoint_RPS) + SysIDFFTest.calculate(setPoint_RPS));
        // SmartDashboard.putNumber("SysID output", SysIDTest.calculate(shooterRightEnc.getVelocity()/60, setPoint_RPS) + SysIDFFTest.calculate(setPoint_RPS));
        // SmartDashboard.putString("PID FF values", SysIDTest.getP() + " " + SysIDTest.getI() + " " + SysIDTest.getD() + " " + SysIDFFTest.ks + " " + SysIDFFTest.kv + " ");
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