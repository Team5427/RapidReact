package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class DriveTrain extends SubsystemBase{

    private MotorControllerGroup left, right;
    private RelativeEncoder leftEnc, rightEnc;
    private DifferentialDrive drive;
    private AHRS gyro;
    private DifferentialDriveOdometry m_odometry;

    public DriveTrain(MotorControllerGroup left, MotorControllerGroup right, DifferentialDrive drive, RelativeEncoder leftEnc, RelativeEncoder rightEnc, AHRS gyro){
        this.left = left;
        this.right = right;
        this.drive = drive;
        this.leftEnc = leftEnc;
        this.rightEnc = rightEnc;
        // leftEnc.setInverted(true);
        leftEnc.setPositionConversionFactor(Units.inchesToMeters(6) * Math.PI * (1/9.01)); //Converts rotation to meters vvv FIXME
        rightEnc.setPositionConversionFactor(Units.inchesToMeters(6) * Math.PI * (1/9.01));
        leftEnc.setVelocityConversionFactor(Units.inchesToMeters(6) * Math.PI * (1/9.01) / 60); //Converts RPM to m/s vvv
        rightEnc.setVelocityConversionFactor(Units.inchesToMeters(6) * Math.PI * (1/9.01) / 60);
        // this.gyro = gyro;
        this.gyro = RobotContainer.getAHRS();
        resetEncoders();
        m_odometry = new DifferentialDriveOdometry(gyro.getRotation2d());
    }

    public void moveLeft(double speed){
        left.set(speed);
    }

    public void moveRight(double speed){
        right.set(speed);
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(leftEnc.getVelocity(), rightEnc.getVelocity());
    }

    public void driveWithJoystick(Joystick joy){

        drive.arcadeDrive(-joy.getY()* .96, joy.getZ() * .75);
    }

    public void getHeading() {
        gyro.getRotation2d().getDegrees();

    }

    public void zeroHeading() {
        gyro.reset();
    }

    public void setVolts(double leftV, double rightV) {
        left.setVoltage(leftV);
        right.setVoltage(rightV);
        drive.feed();
        SmartDashboard.putNumber("leftVolt", leftV);
        SmartDashboard.putNumber("rightVolt", rightV);
    }


    public void resetEncoders() {
        leftEnc.setPosition(0);
        rightEnc.setPosition(0);
    }

    public double getAvgEncoderDist() {
        return (leftEnc.getPosition() + rightEnc.getPosition())/2;
    }

    public RelativeEncoder getLeftEnc() {
        return leftEnc;
    }

    public RelativeEncoder getRightEnc() {
        return rightEnc;
    }

    public void setMaxOutput(double maxOutput) {
        drive.setMaxOutput(maxOutput);
    }

    @Override
    public void periodic() {
      // Update the odometry in the periodic block
        m_odometry.update(
            gyro.getRotation2d(), leftEnc.getPosition(), rightEnc.getPosition());


        SmartDashboard.putString("CurPose", m_odometry.getPoseMeters().toString());
        // SmartDashboard.putString("gyroRot", gyro.getRotation2d().toString());
        // SmartDashboard.putNumber("leftEnc", leftEnc.getPosition());
        // SmartDashboard.putNumber("rightEnc", rightEnc.getPosition());
        // SmartDashboard.putNumber("leftPerc", left.get());
        // SmartDashboard.putNumber("rightPerc", right.get());

        if (RobotContainer.getJoy().getPOV() == 0) {
            RobotContainer.getDriveTrain().resetOdometry(new Pose2d(0, 0, new Rotation2d(0)));
        }

        // if (RobotContainer.getJoy().getRawButton(10)) {
        //     left.set(.3);
        //     right.set(.3);
        // } else if (RobotContainer.getJoy().getRawButton(9)) {
        //     left.set(-.3);
        //     right.set(-.3);
        // } else {
        //     stop();
        // }
        SmartDashboard.putNumber("enc output", rightEnc.getPosition());
        SmartDashboard.putNumber("enc outputv", rightEnc.getVelocity());
    }
  
    public Pose2d getPose() {
      return m_odometry.getPoseMeters();
    }

    public void resetOdometry(Pose2d pose) {
        resetEncoders();
        m_odometry.resetPosition(pose, gyro.getRotation2d());
    }

    public void stop(){
        left.stopMotor();
        right.stopMotor();
    }

    // @Override
    // public void periodic() {
    //     // SmartDashboard.putNumber("dt speed", getRightEnc().getVelocity());
    //     //Uncomment when testing
    // }
}
