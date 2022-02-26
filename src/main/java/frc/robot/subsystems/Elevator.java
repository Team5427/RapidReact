package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase{
    private MotorController elevatorMotor;
    private Encoder elevatorEncoder;

    public Elevator(MotorController elevatorMotor, Encoder elevatorEncoder){
        this.elevatorMotor = elevatorMotor;
        this.elevatorEncoder = elevatorEncoder;
        elevatorEncoder.reset();
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

    
    
}
