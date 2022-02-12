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

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.MoveElevatorInner;
import frc.robot.commands.MoveIntake;
import frc.robot.commands.MoveShooterTeleop;
import frc.robot.commands.MoveTransport;
import frc.robot.commands.auto.IntakeVision;
import frc.robot.commands.auto.ShootVision;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Climber;
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

  private static CANSparkMax frontLeft, rearLeft;
  private static CANSparkMax frontRight,rearRight;
  private static MotorControllerGroup leftDrive;
  private static MotorControllerGroup rightDrive;
  private static MotorController transportMotor;
  private static MotorController intakeMotor;
  private static CANSparkMax shooterMotorLeft;
  private static CANSparkMax shooterMotorRight;
  private static MotorController climberInner1, climberOuter1, climberInner2, climberOuter2, climberTele1, climberTele2;
  private static MotorControllerGroup innerClimber;
  private static MotorControllerGroup outerClimber;
  private static MotorControllerGroup teleClimber;

  private static SparkMaxPIDController leftDrivePID;
  private static SparkMaxPIDController rightDrivePID;
  private static SparkMaxPIDController shooterPID;

  private static RelativeEncoder frontLeftEnc;
  private static RelativeEncoder rearLeftEnc;
  private static RelativeEncoder frontRightEnc;
  private static RelativeEncoder rearRightEnc;
  private static Encoder transportEnc;
  private static Encoder intakeEnc;
  private static RelativeEncoder shooterMotorEncLeft;
  private static RelativeEncoder shooterMotorEncRight;
  private static Encoder climberEncInner1;
  private static Encoder climberEncInner2;
  private static Encoder climberEncOuter1;
  private static Encoder climberEncOuter2;
  private static Encoder climberEncTele1;
  private static Encoder climberEncTele2;

  private static Ultrasonic ultra;
  private static AHRS ahrs;
  private static AnalogInput lidar;
  private static DigitalInput limit_climber_inner_1;
  private static DigitalInput limit_climber_inner_2;
  private static DigitalInput limit_climber_outer_1;
  private static DigitalInput limit_climber_outer_2;
  private static DigitalInput limit_climber_tele_1;
  private static DigitalInput limit_climber_tele_2;

  private static DifferentialDrive drive;
  private static DriveTrain driveTrain;
  private static Transport transport;
  private static Intake intake;
  private static Shooter shooter;
  private static Climber climber;

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
    intakeEnc = new Encoder(Constants.INTAKE_ENC_1, Constants.INTAKE_ENC_2);
    intake = new Intake(intakeMotor, intakeEnc);

    transportMotor = new WPI_VictorSPX(Constants.TRANSPORT_MOTOR);
    transportEnc = new Encoder(Constants.TRANSPORT_ENC_1, Constants.TRANSPORT_ENC_2);
    transport = new Transport(transportMotor, transportEnc);

    shooterMotorLeft = new CANSparkMax(Constants.SHOOTER_MOTOR_TOP, MotorType.kBrushless);
    shooterMotorRight = new CANSparkMax(Constants.SHOOTER_MOTOR_BOTTOM, MotorType.kBrushless);
    shooterMotorEncLeft = shooterMotorLeft.getEncoder();
    shooterMotorEncRight = shooterMotorRight.getEncoder();
    shooterPID = shooterMotorLeft.getPIDController();
    shooter = new Shooter(shooterMotorLeft, shooterMotorRight, shooterMotorEncLeft, shooterMotorEncRight, shooterPID);

    climberInner1 = new WPI_VictorSPX(Constants.ELEVATOR_LEFT_MOTOR);
    climberOuter1 = new WPI_VictorSPX(Constants.ELEVATOR_RIGHT_MOTOR);
    climberInner2 = new WPI_VictorSPX(Constants.ELEVATOR_LEFT_MOTOR);
    climberOuter2 = new WPI_VictorSPX(Constants.ELEVATOR_RIGHT_MOTOR);
    climberTele1 = new WPI_VictorSPX(Constants.ELEVATOR_TELE_MOTOR_1);
    climberTele2 = new WPI_VictorSPX(Constants.ELEVATOR_TELE_MOTOR_2);
    climberInner1.setInverted(true);
    climberOuter1.setInverted(true);
    climberTele1.setInverted(true);
    innerClimber = new MotorControllerGroup(climberInner1, climberInner2);
    outerClimber = new MotorControllerGroup(climberOuter1, climberOuter2);
    teleClimber = new MotorControllerGroup(climberTele1, climberTele2);
    climberEncInner1 = new Encoder(Constants.ELEVATOR_INNER_ENC_1, Constants.ELEVATOR_INNER_ENC_2);
    climberEncInner1.setReverseDirection(true);
    climberEncInner2 = new Encoder(Constants.ELEVATOR_INNER_ENC_1, Constants.ELEVATOR_INNER_ENC_2);
    climberEncOuter1 = new Encoder(Constants.ELEVATOR_OUTER_ENC_1, Constants.ELEVATOR_OUTER_ENC_2);
    climberEncOuter1.setReverseDirection(true);
    climberEncOuter2 = new Encoder(Constants.ELEVATOR_OUTER_ENC_1, Constants.ELEVATOR_OUTER_ENC_2);
    climberEncTele1 = new Encoder(Constants.ELEVATOR_TELE_ENC_1, Constants.ELEVATOR_TELE_ENC_2);
    climberEncTele1.setReverseDirection(true);
    climberEncTele2 = new Encoder(Constants.ELEVATOR_TELE_ENC_1, Constants.ELEVATOR_TELE_ENC_2);
    limit_climber_inner_1 = new DigitalInput(Constants.LMT_SWITCH_INNER_CLIMBER_1);
    limit_climber_inner_2 = new DigitalInput(Constants.LMT_SWITCH_INNER_CLIMBER_2);
    limit_climber_outer_1 = new DigitalInput(Constants.LMT_SWITCH_OUTER_CLIMBER_1);
    limit_climber_outer_2 = new DigitalInput(Constants.LMT_SWITCH_OUTER_CLIMBER_2);
    limit_climber_tele_1 = new DigitalInput(Constants.LMT_SWITCH_TELE_CLIMBER_1);
    limit_climber_tele_2 = new DigitalInput(Constants.LMT_SWITCH_TELE_CLIMBER_2);
    climber = new Climber(climberInner1, climberInner2, climberOuter1, climberOuter2, climberTele1, climberTele2, innerClimber, outerClimber, teleClimber, climberEncInner1, climberEncInner2, climberEncOuter1, climberEncOuter2, climberEncTele1, climberEncTele2, limit_climber_inner_1, limit_climber_inner_2, limit_climber_outer_1, limit_climber_outer_2, limit_climber_tele_1, limit_climber_tele_2);

    ahrs = new AHRS(SPI.Port.kMXP);

    lidar = new AnalogInput(0);

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
    moveElevatorUp.whileHeld(new MoveElevatorInner(Constants.ELEVATOR_SPEED));
    moveElevatorDown.whileHeld(new MoveElevatorInner(-Constants.ELEVATOR_SPEED));
    visionIntake.whenPressed(new IntakeVision(0, true));
    visionShoot.whenPressed(new ShootVision(0, true));
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
  public static Climber getClimber(){return climber;}
  public static AnalogInput getLIDAR(){return lidar;}
}