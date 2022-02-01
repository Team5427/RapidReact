package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

public class Transport extends SubsystemBase
{

    private CANSparkMax transportMotor;
    private RelativeEncoder enc;
       
    public Transport (CANSparkMax transportMotor, RelativeEncoder enc) 
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

    public CANSparkMax getTransportMotor()
    {
        return transportMotor;
    }

    public RelativeEncoder getTransportEnc()
    {
        return enc;
    }

}