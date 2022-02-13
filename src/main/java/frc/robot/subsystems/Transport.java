package frc.robot.subsystems;


import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Transport extends SubsystemBase {
    private MotorController transport_Controller;
    private AnalogInput    transport_sensor;
    public Transport(MotorController transport_Controller, AnalogInput transport_sensor){
        this.transport_Controller = transport_Controller;
        this.transport_sensor = transport_sensor;

    }
    public void move(double speed) {
        transport_Controller.set(speed);
    }
    public void stop(){
        transport_Controller.stopMotor();
    }

    public AnalogInput getSensor(){
        return transport_sensor;
    }
    public double getProxVal(){
       return (1/transport_sensor.getVoltage()) * 6.1111126 * 1/2.54;
    }

    public boolean proxCovered(){
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
