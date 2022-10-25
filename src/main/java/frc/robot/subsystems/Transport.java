package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Transport extends SubsystemBase {
    private VictorSPX transportMotor;
    private AnalogInput proximity;
    private Timer timer = new Timer();

    public Transport(VictorSPX transportMotor, AnalogInput proximity){
        this.transportMotor = transportMotor;
        this.proximity = proximity;
        timer.reset();

    }
    public void move(double speed) {
        transportMotor.set(ControlMode.PercentOutput, speed);
    }
    public void stop(){
        transportMotor.neutralOutput();
    }

    public double getProxVal(){
       return (1/proximity.getVoltage()) * 6.1111126 * 1/2.54;
    }    
}
