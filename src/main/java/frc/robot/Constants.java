// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.I2C.Port;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //speeds
    public static final double SHOOTER_TELEOP_SPEED = 1.0;
    public static final double TILT_UP_SPEED = 0.5;
    public static final double TILT_DOWN_SPEED = -0.5;
    
    public static final double INTAKE_IN_SPEED = 0.5;

    public static final double INTAKE_OUT_SPEED = -0.8;

    public static final double ELEVATOR_SPEED = .5;
    public static final double ARM_TILT_SPEED = .5;
    public static final double ARM_SPEED = .5;

    //Buttons
    public static final int SHOOTER_TELEOP_BUTTON = 1;
    public static final int TILT_UP_BUTTON = 2;
    public static final int TILT_DOWN_BUTTON = 3;
    
    public static final int INTAKE_IN_BUTTON = 4;

    public static final int ELEVATOR_UP_BUTTON = 5;
    public static final int ELEVATOR_DOWN_BUTTON = 6;

    public static final int ARM_DOWN_BUTTON = 7;

    public static final int ARM_TILT_IN_BUTTON = 0;
    
    //Motor ports
    public static final int BOTTOM_LEFT_MOTOR = 0;
    public static final int BOTTOM_RIGHT_MOTOR = 0;
    public static final int TOP_RIGHT_MOTOR = 0;
    public static final int TOP_LEFT_MOTOR = 0;

    public static final int TILT_MOTOR = 1;

    public static final int SHOOTER_MOTOR_RIGHT = 2;
    public static final int SHOOTER_MOTOR_LEFT = 3;

    public static final int ELEVATOR_MOTOR = 0;

    public static final int ARM_LEFT_MOTOR = 0;
    public static final int ARM_RIGHT_MOTOR = 0;
    public static final int ARM_TILT_MOTOR = 0;

    public static final int INTAKE_MOTOR_PORT = 4;


    //Sensor Ports
    public static final int TILT_SWITCH = 2;

    public static final double COVERED = 5;
    public static final int TRANSPORT_MOTOR = 7;
    public static final int TRANSPORT_SENSOR = 9;
    public static final double TRANSPORT_SPEED = 0.5;
    public static final int TRANSPORT_BUTTON = 7;

    public static final int ELEVATOR_ENCODER_1 = 0;
    public static final int ELEVATOR_ENCODER_2 = 0;
    public static final int ARM_LEFT_ENCODER_1 = 0;
    public static final int ARM_RIGHT_ENCODER_1 = 0;
    public static final int ARM_TILT_ENCODER_1 = 0;
    public static final int ARM_TILT_ENCODER_2 = 0;

    public static final int ELEVATOR_LIMIT = 0;
    public static final int ARM_RIGHT_LIMIT = 0;
    public static final int ARM_TILT_LEFT_LIMIT = 0;
    public static final int ARM_TILT_RIGHT_LIMIT = 0;

    public static final Port LIDAR_PORT = null;
    public static final int LIDAR_ADDRESS = 0;


    //Constants (numbers, limits, etc.)
    public static final int LIDAR_MIN = 0;
    public static final int LIDAR_MAX = 0;
    






}
