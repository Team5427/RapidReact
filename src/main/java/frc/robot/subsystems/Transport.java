package frc.robot.subsystems;


import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Transport extends SubsystemBase {
    private MotorController transportMotor;
    private AnalogInput proximity;

    public Transport(MotorController transportMotor, AnalogInput proximity){
        this.transportMotor = transportMotor;
        this.proximity = proximity;

    }
    public void move(double speed) {
        transportMotor.set(speed);
    }
    public void stop(){
        transportMotor.stopMotor();
    }

    
    public double getProxVal(){
       return (1/proximity.getVoltage()) * 6.1111126 * 1/2.54;
    }

    public boolean proxCovered(){
        // return true if covered
        return getProxVal() < Constants.COVERED;
    }

    @Override
    public void periodic(){
        if(proxCovered()){
            move(Constants.TRANSPORT_SPEED);
        }
        else{
            stop();
        }
    }
    
}
