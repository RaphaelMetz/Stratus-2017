package org.usfirst.frc.team1389.autonomous;

import org.usfirst.frc.team1389.robot.RobotSoftware;

import com.team1389.auto.AutoModeBase;
import com.team1389.auto.AutoModeEndedException;
import com.team1389.command_framework.CommandScheduler;
import com.team1389.command_framework.CommandUtil;
import com.team1389.trajectory.PathFollowingSystem.PathFollowCommand;
import com.team1389.trajectory.PathFollowingSystem;
import com.team1389.trajectory.Translation2d;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;
import org.usfirst.frc.team1389.robot.RobotSoftware;

import com.team1389.auto.AutoModeBase;
import com.team1389.auto.AutoModeEndedException;
import com.team1389.command_framework.CommandScheduler;
import com.team1389.command_framework.CommandUtil;
import com.team1389.hardware.inputs.software.PositionEncoderIn;
import com.team1389.trajectory.PathFollowingSystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;

/**
 * This auto dust drops off a gear
 * 
 * @author raffi_000
 *
 */
/*
 * TODO Add SubSystem functionality
 */
public class DropOffGear extends AutoModeBase {

	RobotSoftware robot;
	PathFollowingSystem cont;

	public DropOffGear(RobotSoftware robot) {
		this.robot = robot;
	}

	@Override
	public AddList<Watchable> getSubWatchables(AddList<Watchable> stem) {
		return stem.put(robot.gyro, robot.voltageDrive);
	}

	@Override
	public String getName() {
		return "Drop off Gear";
	}

	@Override
	protected void routine() throws AutoModeEndedException {
		Waypoint[] points = new Waypoint[] { new Waypoint(0, 30, 0), new Waypoint(-101, 56, Math.toRadians(300)) };
		Waypoint[] points2 = new Waypoint[] { new Waypoint(50, 50, 0), new Waypoint(-20, -20, 0) };

		runCommand(CommandUtil.combineSequential(cont.new PathFollowCommand(points, false, 180),
				CommandUtil.createCommand(robot.gyro::reset), cont.new PathFollowCommand(points2, false, 180)));
	}

}
