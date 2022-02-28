// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

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
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.MoveArm;
import frc.robot.commands.MoveArmTilt;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.MoveShooterTeleop;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lidar;
import frc.robot.commands.MoveTilt;
import frc.robot.commands.MoveTransport;
import frc.robot.commands.TeleArmTilt;
import frc.robot.commands.auto.ArmAutoTiltOut;
import frc.robot.commands.auto.AutonThreeBallsAlpha;
import frc.robot.commands.auto.AutonThreeBallsBeta;
import frc.robot.commands.auto.AutonTwoBalls;
import frc.robot.commands.auto.NoVisionAuton;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TelescopicArm;
import frc.robot.subsystems.Tilt;
import frc.robot.subsystems.Transport;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static Joystick joy;
  private static Button shooterTeleop;
  private static Button tiltUp;
  private static Button tiltDown;
  private static Button elevator_down;
  private static Button elevator_up;

  private static Joystick joy2;
  private static Button arm_extend_down;
  private static Button manual_shoot;
  private static Button auto_tilt_arm_out;
  private static Button arm_in;
  private static Button arm_out;
  private static Button auto_arm_out;
  private static Button transport_move;
  private static Button arm_tilt_in;
  private static Button arm_tilt_out;

  public static CANSparkMax shooterMotorRight;
  public static CANSparkMax shooterMotorLeft;
  public static CANSparkMax topRight, topLeft, bottomRight, bottomLeft;
  public static MotorControllerGroup left, right;
  public static MotorController intakeMotor;
  public static MotorController tiltMotor;
  public static MotorController transportMotor;
  public static MotorController elevatorMotor;
  public static MotorController armLeftMotor;
  public static MotorController armRightMotor;
  public static MotorController armTiltMotor;
  public static DifferentialDrive drive;

  private static RelativeEncoder shooterRightEnc;
  private static RelativeEncoder shooterLeftEnc;
  private static DigitalInput tilt_limit;
  private static AnalogInput transport_sensor;
  private static DigitalInput armRightLimit;
  private static DigitalInput armLeftLimit;
  private static DigitalInput armTiltLeftLimit;
  private static DigitalInput armTiltRightLimit;
  private static DigitalInput elevatorLimit;
  private static Encoder elevatorEncoder;
  private static Encoder armleftEncoder;
  private static Encoder armRightEncoder;
  private static Encoder armTiltEncoder;
  private static I2C lidar_sensor;

  private static Shooter shooter;
  private static Tilt tilt;
  private static Transport transport;
  private static Intake intake;
  private static Elevator elevator;
  private static TelescopicArm telescopicArm;
  private static DriveTrain driveTrain;
  private static Lidar lidar;

  private static SparkMaxPIDController pidcontrol_shooter_Right;
  private static SparkMaxPIDController pidcontrol_shooter_Left;

  public static PowerDistribution pdp;

  private static AHRS ahrs;

  private static SendableChooser<Command> autonChooser;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    joy = new Joystick(0);
    joy2 = new Joystick(1);

    topLeft = new CANSparkMax(Constants.TOP_LEFT_MOTOR, MotorType.kBrushless);
    topRight = new CANSparkMax(Constants.TOP_RIGHT_MOTOR, MotorType.kBrushless);
    bottomLeft = new CANSparkMax(Constants.BOTTOM_LEFT_MOTOR, MotorType.kBrushless);
    bottomRight = new CANSparkMax(Constants.BOTTOM_RIGHT_MOTOR, MotorType.kBrushless);
    left = new MotorControllerGroup(topLeft, bottomLeft);
    right = new MotorControllerGroup(topRight, bottomRight);
    drive = new DifferentialDrive(left, right);

    tiltMotor = new WPI_VictorSPX(Constants.TILT_MOTOR);
    tilt_limit = new DigitalInput(Constants.TILT_SWITCH);

    transportMotor = new WPI_VictorSPX(Constants.TRANSPORT_MOTOR);
    transport_sensor = new AnalogInput(Constants.TRANSPORT_SENSOR);

    elevatorMotor = new WPI_VictorSPX(Constants.ELEVATOR_MOTOR);
    elevatorLimit = new DigitalInput(Constants.ELEVATOR_LIMIT);
    armLeftMotor = new WPI_VictorSPX(Constants.ARM_LEFT_MOTOR);
    armRightMotor = new WPI_VictorSPX(Constants.ARM_RIGHT_MOTOR);
    armTiltMotor = new WPI_VictorSPX(Constants.ARM_TILT_MOTOR);
    elevatorEncoder = new Encoder(Constants.ELEVATOR_ENCODER_1, Constants.ELEVATOR_ENCODER_2);
    armleftEncoder = new Encoder(Constants.ARM_LEFT_ENCODER_1, Constants.ELEVATOR_ENCODER_2);
    armRightEncoder = new Encoder(Constants.ARM_RIGHT_ENCODER_1, Constants.ELEVATOR_ENCODER_1);
    armTiltEncoder = new Encoder(Constants.ARM_TILT_ENCODER_1, Constants.ARM_TILT_ENCODER_2);
    armRightLimit = new DigitalInput(Constants.ARM_RIGHT_LIMIT);
    armLeftLimit = new DigitalInput(Constants.TILT_MOTOR);
    armTiltLeftLimit = new DigitalInput(Constants.ARM_TILT_LEFT_LIMIT);
    armTiltRightLimit = new DigitalInput(Constants.ARM_TILT_RIGHT_LIMIT);

    shooterMotorRight = new CANSparkMax(Constants.SHOOTER_MOTOR_RIGHT, MotorType.kBrushless);
    shooterMotorLeft = new CANSparkMax(Constants.SHOOTER_MOTOR_LEFT, MotorType.kBrushless);
    shooterMotorLeft.setInverted(false);
    shooterMotorRight.setInverted(true);
    pidcontrol_shooter_Right = shooterMotorRight.getPIDController();
    pidcontrol_shooter_Left = shooterMotorLeft.getPIDController();
    shooterRightEnc = shooterMotorRight.getEncoder();
    shooterLeftEnc = shooterMotorLeft.getEncoder();

    lidar_sensor = new I2C(Constants.LIDAR_PORT, Constants.LIDAR_ADDRESS);

    pdp = new PowerDistribution(0, ModuleType.kCTRE);

    shooter = new Shooter(shooterMotorRight, shooterMotorLeft, shooterRightEnc, shooterLeftEnc, pidcontrol_shooter_Right, pidcontrol_shooter_Left);
    tilt = new Tilt(tiltMotor, tilt_limit);
    transport = new Transport(transportMotor, transport_sensor);
    intake = new Intake(intakeMotor);
    elevator = new Elevator(elevatorMotor, elevatorEncoder, elevatorLimit);
    telescopicArm = new TelescopicArm(tiltMotor, armLeftMotor, armRightMotor, armleftEncoder, armRightEncoder, armTiltEncoder, armRightLimit, armLeftLimit, armTiltRightLimit, armTiltLeftLimit);
    driveTrain = new DriveTrain(left, right, drive);
    lidar = new Lidar(lidar_sensor);
    ahrs = new AHRS(SPI.Port.kMXP);
    driveTrain.setDefaultCommand(new DriveWithJoystick());
    
    autonChooser.setDefaultOption("Two Ball Auton", new AutonTwoBalls());
    autonChooser.addOption("Far Ball Auton (3 balls)", new AutonThreeBallsBeta());
    autonChooser.addOption("Close Ball Auton (3 balls)", new AutonThreeBallsAlpha());
    autonChooser.addOption("No Vision Auton", new NoVisionAuton());

    SmartDashboard.putData("Auton", autonChooser);
    configureButtonBindings();

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    shooterTeleop = new JoystickButton(joy, Constants.SHOOTER_TELEOP_BUTTON);
    tiltUp = new JoystickButton(joy, Constants.TILT_UP_BUTTON);
    tiltDown = new JoystickButton(joy, Constants.TILT_DOWN_BUTTON);
    transport_move = new JoystickButton(joy, Constants.TRANSPORT_BUTTON);
    elevator_down = new JoystickButton(joy, Constants.ELEVATOR_DOWN_BUTTON);
    elevator_up = new JoystickButton(joy, Constants.ELEVATOR_UP_BUTTON);
    arm_extend_down = new JoystickButton(joy, Constants.ARM_DOWN_BUTTON);

    shooterTeleop.whenPressed(new MoveShooterTeleop());
    tiltUp.whenPressed(new MoveTilt(Constants.TILT_UP_SPEED));
    tiltDown.whenPressed(new MoveTilt(Constants.TILT_DOWN_SPEED));
    transport_move.whenPressed(new MoveTilt(Constants.TRANSPORT_SPEED));
    elevator_down.whenPressed(new MoveElevator(Constants.ELEVATOR_SPEED));
    elevator_up.whenPressed(new MoveElevator(-Constants.ELEVATOR_SPEED));
    arm_extend_down.whenPressed(new MoveElevator(Constants.ARM_SPEED));

    manual_shoot = new JoystickButton(joy2, Constants.MANUAL_SHOOT_BUTTON);
    auto_tilt_arm_out = new JoystickButton(joy2, Constants.AUTO_TILT_ARM_OUT_BUTTON);
    arm_in = new JoystickButton(joy2, Constants.ARM_IN_BUTTON);
    arm_out = new JoystickButton(joy2, Constants.ARM_OUT_BUTTON);
    auto_arm_out = new JoystickButton(joy2, Constants.AUTO_ARM_OUT_BUTTON);
    arm_tilt_in = new JoystickButton(joy2, Constants.ARM_TILT_IN_BUTTON);
    arm_tilt_out = new JoystickButton(joy2, Constants.ARM_TILT_IN_BUTTON);

    transport_move = new JoystickButton(joy2, Constants.TRANSPOT_MOVE_BUTTON);

    manual_shoot.whileHeld(new MoveShooterTeleop());
    auto_tilt_arm_out.whenPressed(new ArmAutoTiltOut(Constants.ARM_TILT_SPEED));
    arm_in.whileHeld(new MoveArmTilt(-Constants.ARM_TILT_SPEED));
    arm_out.whileHeld(new MoveArmTilt(Constants.ARM_TILT_SPEED));
    auto_arm_out.whenPressed(new MoveArm(Constants.ARM_SPEED));
    arm_tilt_in.whenPressed(new TeleArmTilt(-Constants.ARM_TILT_SPEED));
    arm_tilt_out.whenPressed(new TeleArmTilt(Constants.ARM_TILT_SPEED));
    transport_move.whileHeld(new MoveTransport(Constants.TRANSPORT_SPEED));
  
  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public static Command getAutonomousCommand() {
    return autonChooser.getSelected();
  }

  public static Shooter getShooter(){return shooter;}
  public static Joystick getJoy(){return joy;}
  public static Intake getIntake() {return intake;}
  public static Tilt getTilt() {return tilt;}
  public static Transport getTransport(){return transport;}
  public static Elevator getElevator(){return elevator;}
  public static TelescopicArm getTelescopicArm(){return telescopicArm;}
  public static DriveTrain getDriveTrain(){return driveTrain;}
  public static Lidar getLidar(){return lidar;}
  public static AHRS getAHRS(){return ahrs;}
  public static Joystick getSecondJoy(){return joy2;}

}
