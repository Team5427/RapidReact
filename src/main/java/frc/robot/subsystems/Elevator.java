package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase{
    private MotorController elevatorMotor;
    private Encoder elevatorEncoder;
    private DigitalInput limitSwitch;

    public Elevator(MotorController elevatorMotor, Encoder elevatorEncoder, DigitalInput limitSwitch){
        this.elevatorMotor = elevatorMotor;
        this.elevatorEncoder = elevatorEncoder;
        this.limitSwitch = limitSwitch;
    }

    public void move(double speed){
        elevatorMotor.set(speed);
    }

    public void stop(){
        elevatorMotor.stopMotor();
    }

    public double getDistance(){
        return elevatorEncoder.getDistance();
    }

    public boolean getLimit(){
        return !limitSwitch.get();
    }
    
}
