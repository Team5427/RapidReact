package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Transport extends SubsystemBase {
    private MotorController transportMotor;
    private AnalogInput proximity;
    private boolean b;
    private Timer timer = new Timer();

    public Transport(MotorController transportMotor, AnalogInput proximity){
        this.transportMotor = transportMotor;
        this.proximity = proximity;
        timer.reset();

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
        if (proxCovered() && !RobotContainer.getJoy().getRawButton(Constants.TRANSPORT_BACK_BUTTON)){
            move(.5);
            b = true;
        } else {
            if (b) {
                timer.start();
                move(.4);
                if (timer.get() > .2) {
                    stop();
                    timer.reset();
                    b = false;
                }
            } else {
                stop();
            }
            
        }
    }
    
}
