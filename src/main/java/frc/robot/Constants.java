/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants 
{
    //****** NUMBERS *******/
    public static final double LIDAR_COEFFICIENT = 1000;
    //↑ Undecided as of 2/10/22.


    //Joystick buttons
    public static final int INTAKE_BUTTON = 2;
    public static final int TRANSPORT_BUTTON = 7;
    public static final int SHOOTER_BUTTON = 11;
    public static final int SHOOTER_TELEOP = 1;
    public static final int ELEVATOR_UP_BUTTON = 6;
    public static final int ELEVATOR_DOWN_BUTTON = 3;
    public static final int VISION_INTAKE_BTN = 4;
    public static final int VISION_SHOOTER_BTN = 5;

    //Speeds
    public static final double INTAKE_TELEOP_SPEED = 0.7;
    public static final double TRANSPORT_TELEOP_SPEED = -0.7;
    public static final double PULLEY_TELEOP_SPEED = 1.0;
    public static final double TILT_SPEED = 1.0;
    public static final double SHOOTER_TELEOP_SPEED = 1.0;
    public static final double AUTONOMOUS_SPEED = 0.4;
    public static final double ELEVATOR_SPEED = 0.6;

    /*****************Motor ports*****************/
    public static final int LEFT_TOP_MOTOR = 14; 
	public static final int LEFT_BOTTOM_MOTOR = 15;
	public static final int RIGHT_TOP_MOTOR = 0;
    public static final int RIGHT_BOTTOM_MOTOR = 1;
    public static final int INTAKE_MOTOR = 10; 
    public static final int TRANSPORT_MOTOR = 12;
    public static final int SHOOTER_MOTOR_TOP = 9;
    public static final int SHOOTER_MOTOR_BOTTOM = 6;
    public static final int ELEVATOR_LEFT_MOTOR = 13;
    public static final int ELEVATOR_RIGHT_MOTOR = 2;
    public static final int ELEVATOR_TELE_MOTOR_1 = 2;
    public static final int ELEVATOR_TELE_MOTOR_2 = 2;

    /*******************Sensors*******************/
    public static final int ULTRASONIC_PING = 22;
    public static final int ULTRASONIC_ECHO = 23;

    /*******************Encoder Ports*******************/
    public static final int ELEVATOR_INNER_ENC_1 = 0;
    public static final int ELEVATOR_INNER_ENC_2 = 1;
    public static final int ELEVATOR_OUTER_ENC_1 = 2;
    public static final int ELEVATOR_OUTER_ENC_2 = 3;
    public static final int ELEVATOR_TELE_ENC_1 = 2;
    public static final int ELEVATOR_TELE_ENC_2 = 3;
    public static final int TRANSPORT_ENC_1 = 4;
    public static final int TRANSPORT_ENC_2 = 5;
    public static final int INTAKE_ENC_1 = 6;
    public static final int INTAKE_ENC_2 = 7;
}
