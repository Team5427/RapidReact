package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;

public class Shooter extends SubsystemBase
{
    public CANSparkMax shooterMotorRight;
    private CANSparkMax shooterMotorLeft;
    private RelativeEncoder shooterRightEnc;

    private PIDController SysIDTest;
    private SimpleMotorFeedforward SysIDFFTest;
    private double kp, ki, kd, ks, kv, ka; 
    
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
        SysIDTest = new PIDController(kp, ki, kd);
        SysIDFFTest = new SimpleMotorFeedforward(ks, kv, ka);
    }

    public void moveShooterSydID(double setPoint_RPS) {
        shooterMotorRight.setVoltage(SysIDTest.calculate(shooterRightEnc.getVelocity()/60, setPoint_RPS) + SysIDFFTest.calculate(setPoint_RPS));
    }

    public double getSpeedRPM() {
        return shooterRightEnc.getVelocity();
    }

    public void stop()
    {
        shooterMotorLeft.set(0);
        shooterMotorRight.set(0);
    }
}