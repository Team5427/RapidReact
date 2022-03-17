package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase{
    private MotorController elevatorMotor;
    private Encoder elevatorEncoder;
    private DigitalInput limit;

    public Elevator(MotorController elevatorMotor, Encoder elevatorEncoder, DigitalInput limit){
        this.elevatorMotor = elevatorMotor;
        this.elevatorEncoder = elevatorEncoder;
        this.limit = limit;
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

    public boolean getElevatorLimit() {
        return !limit.get();
    }

    public boolean getElevatorEncoderLimit(){
        // return true if at limit
        return getDistance() < Constants.ELEVATOR_ENCODER_LIMIT;
    }
    
    
}
