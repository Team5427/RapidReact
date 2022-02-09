package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Transport extends SubsystemBase
{

    private MotorController transportMotor;
    private Encoder enc;
       
    public Transport (MotorController transportMotor, Encoder enc) 
    {
        this.transportMotor = transportMotor;
        this.enc = enc;
    }

    public void stop()
    {
        transportMotor.stopMotor();
    }

    public void moveTransport(double speed)
    {
        transportMotor.set(speed);
    }

    public MotorController getTransportMotor()
    {
        return transportMotor;
    }

    public Encoder getTransportEnc()
    {
        return enc;
    }

}