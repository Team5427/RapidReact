package frc.robot.commands.auto;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class PointTurn extends CommandBase{
    // ur a homo @prat
    private double setpoint;
    private AHRS navx;
    private double speed = 1;
    public PointTurn(double setpoint){
        addRequirements(RobotContainer.getDriveTrain());
        this.setpoint = setpoint;
        navx = RobotContainer.getAHRS();
        
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        double currentAngle = (navx.getAngle() > 0)?(navx.getAngle() % 360) : (0 - (navx.getAngle() %360));

        double error = setpoint - navx.getAngle();

        if(error > 180){
            speed *= -1;
        }

        RobotContainer.getDriveTrain().moveRight(speed);
        RobotContainer.getDriveTrain().moveLeft(-speed);
    }


}
