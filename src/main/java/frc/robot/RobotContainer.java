/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.MoveShooterTeleop;
import frc.robot.commands.MoveTransport;
import frc.robot.commands.VisionTurnRight;
import frc.robot.commands.auto.IntakeVision;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transport;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.subsystems.Shooter;

public class RobotContainer 
{
  private static Joystick joy;
  private static Button intakeButton;
  private static Button transportButton;
  private static Button shooterTeleop;
  private static Button moveElevatorUp;
  private static Button moveElevatorDown;
  private static Button visionIntake;
  private static Button visionShoot;

  private final CANSparkMax frontLeft, rearLeft;
  private final CANSparkMax frontRight,rearRight;
  private static MotorControllerGroup leftDrive;
  private static MotorControllerGroup rightDrive;
  private static MotorController transportMotor;
  private static MotorController intakeMotor;
  private static CANSparkMax shooterMotorLeft;
  private static CANSparkMax shooterMotorRight;
  private static MotorController elevatorInner1, elevatorOuter1, elevatorInner2, elevatorOuter2;
  private static MotorControllerGroup innerElevator;
  private static MotorControllerGroup outerElevator;

  private final SparkMaxPIDController leftDrivePID;
  private final SparkMaxPIDController rightDrivePID;
  private final SparkMaxPIDController shooterPID;

  private static RelativeEncoder frontLeftEnc;
  private static RelativeEncoder rearLeftEnc;
  private static RelativeEncoder frontRightEnc;
  private static RelativeEncoder rearRightEnc;
  private static Encoder transportEnc;
  private static Encoder intakeEnc;
  private static RelativeEncoder shooterMotorEncLeft;
  private static RelativeEncoder shooterMotorEncRight;
  private static Encoder elevatorEncInner;
  private static Encoder elevatorEncOuter;
  private static Ultrasonic ultra;
  private static AHRS ahrs;

  private static DifferentialDrive drive;
  private static DriveTrain driveTrain;
  private static Transport transport;
  private static Intake intake;
  private static Shooter shooter;
  private static Elevator elevator;

  public RobotContainer() 
  {
    frontLeft = new CANSparkMax(Constants.LEFT_TOP_MOTOR, MotorType.kBrushless);
    rearLeft = new CANSparkMax(Constants.LEFT_BOTTOM_MOTOR, MotorType.kBrushless);
    leftDrive = new MotorControllerGroup(frontLeft, rearLeft);
    frontRight = new CANSparkMax(Constants.RIGHT_BOTTOM_MOTOR, MotorType.kBrushless);
    rearRight = new CANSparkMax(Constants.RIGHT_TOP_MOTOR, MotorType.kBrushless);
    rightDrive = new MotorControllerGroup(frontRight, rearRight);
    frontLeftEnc = frontLeft.getEncoder();
    rearLeftEnc = rearLeft.getEncoder();
    frontRightEnc = frontRight.getEncoder();
    rearRightEnc = rearRight.getEncoder();
    leftDrivePID = frontLeft.getPIDController();
    rightDrivePID = frontRight.getPIDController();
    drive = new DifferentialDrive(leftDrive, rightDrive);
    drive.setSafetyEnabled(false);
    driveTrain = new DriveTrain(leftDrive, rightDrive, drive, frontLeftEnc, rearLeftEnc, frontRightEnc, rearRightEnc, leftDrivePID, rightDrivePID);
    driveTrain.setDefaultCommand(new DriveWithJoystick());

    intakeMotor = new WPI_VictorSPX(Constants.INTAKE_MOTOR);
    intakeEnc = new Encoder(1, 1);
    intake = new Intake(intakeMotor, intakeEnc);

    transportMotor = new WPI_VictorSPX(Constants.TRANSPORT_MOTOR);
    transportEnc = new Encoder(1, 1);
    transport = new Transport(transportMotor, transportEnc);

    shooterMotorLeft = new CANSparkMax(Constants.SHOOTER_MOTOR_TOP, MotorType.kBrushless);
    shooterMotorRight = new CANSparkMax(Constants.SHOOTER_MOTOR_BOTTOM, MotorType.kBrushless);
    shooterMotorEncLeft = shooterMotorLeft.getEncoder();
    shooterMotorEncRight = shooterMotorRight.getEncoder();
    shooterPID = shooterMotorLeft.getPIDController();
    shooter = new Shooter(shooterMotorLeft, shooterMotorRight, shooterMotorEncLeft, shooterMotorEncRight, shooterPID);

    elevatorInner1 = new WPI_VictorSPX(Constants.ELEVATOR_LEFT_MOTOR);
    elevatorOuter1 = new WPI_VictorSPX(Constants.ELEVATOR_RIGHT_MOTOR);
    elevatorInner2 = new WPI_VictorSPX(Constants.ELEVATOR_LEFT_MOTOR);
    elevatorOuter2 = new WPI_VictorSPX(Constants.ELEVATOR_RIGHT_MOTOR);
    innerElevator = new MotorControllerGroup(elevatorInner1, elevatorInner2);
    outerElevator = new MotorControllerGroup(elevatorOuter1, elevatorOuter2);
    elevatorEncInner = new Encoder(1, 1);
    elevatorEncInner = new Encoder(1, 1);
    elevator = new Elevator(innerElevator, outerElevator, elevatorEncInner, elevatorEncOuter);

    ultra = new Ultrasonic(Constants.ULTRASONIC_PING, Constants.ULTRASONIC_ECHO);
    Ultrasonic.setAutomaticMode(true);

    ahrs = new AHRS(SPI.Port.kMXP);

    configureButtonBindings();
  }

  private void configureButtonBindings() 
  {
    joy = new Joystick(0);

    intakeButton = new JoystickButton(joy, Constants.INTAKE_BUTTON);
    transportButton = new JoystickButton(joy, Constants.TRANSPORT_BUTTON);
    shooterTeleop = new JoystickButton(joy, Constants.SHOOTER_TELEOP);
    moveElevatorUp = new JoystickButton(joy, Constants.ELEVATOR_UP_BUTTON);
    moveElevatorDown = new JoystickButton(joy, Constants.ELEVATOR_DOWN_BUTTON);
    visionIntake = new JoystickButton(joy, Constants.VISION_INTAKE_BTN);
    visionShoot = new JoystickButton(joy, Constants.VISION_SHOOTER_BTN);
  
    intakeButton.whileHeld(new MoveIntake(Constants.INTAKE_TELEOP_SPEED));
    transportButton.whenPressed(new MoveTransport(Constants.TRANSPORT_TELEOP_SPEED));
    shooterTeleop.whileHeld(new MoveShooterTeleop(Constants.SHOOTER_TELEOP_SPEED));
    moveElevatorUp.whileHeld(new MoveElevator(Constants.ELEVATOR_SPEED));
    moveElevatorDown.whileHeld(new MoveElevator(-Constants.ELEVATOR_SPEED));
    visionIntake.whenPressed(new IntakeVision(0));
    visionShoot.whenPressed(new VisionTurnRight(0));
  }

  public static Command getAutonomousCommand() 
  {
    return null;
  }

  public static DriveTrain getDriveTrain(){return driveTrain;}
  public static MotorControllerGroup getLeftSCG(){return leftDrive;}
  public static MotorControllerGroup getRightSCG(){return rightDrive;}
  public static DifferentialDrive getDiffDrive(){return drive;}
  public static AHRS getAHRS(){return ahrs;}
  public static Joystick getJoy(){return joy;}
  public static Intake getIntake(){return intake;}
  public static Transport getTransport(){return transport;}
  public static Shooter getShooter(){return shooter;}
  public static Ultrasonic getUltrasonic(){return ultra;}
  public static Elevator getElevator(){return elevator;}
}