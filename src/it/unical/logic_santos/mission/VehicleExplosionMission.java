/**
 * 
 */
package it.unical.logic_santos.mission;

import it.unical.logic_santos.logging.AircraftLoggingEvent;
import it.unical.logic_santos.logging.ILogger;
import it.unical.logic_santos.logging.ILoggingEvent;
import it.unical.logic_santos.logging.VehicleLoggingEvent;

/**
 * @author Agostino
 *
 */
public class VehicleExplosionMission implements IMission {

	private boolean missionComplete=false;
	private boolean drivingAircraft=false;

	public VehicleExplosionMission() {}
	
	@Override
	public void onLoggingEvent(ILoggingEvent loggingEvent, ILogger logger) {
		if ( loggingEvent instanceof AircraftLoggingEvent ) {
			
			AircraftLoggingEvent aircraftLoggingEvent = (AircraftLoggingEvent) loggingEvent;
			if ( aircraftLoggingEvent.getEventType()==AircraftLoggingEvent.STOLE_AIRCRAFT_EVENT )
				drivingAircraft=true;
			else if ( aircraftLoggingEvent.getEventType()==AircraftLoggingEvent.LEAVE_AIRCRAFT_EVENT )
				drivingAircraft=false;
		}
		
		else if ( loggingEvent instanceof VehicleLoggingEvent ) {
			
			VehicleLoggingEvent vehicleLoggingEvent = (VehicleLoggingEvent) loggingEvent;
			if ( (vehicleLoggingEvent.getEventType()==VehicleLoggingEvent.VEHICLE_EXPLOSION_EVENT) &&
					drivingAircraft )
				missionComplete=true;
		}
		
	}

	@Override
	public boolean check() {
		return missionComplete;
	}

	@Override
	public boolean check(float tpf) {
		return missionComplete;
	}

	@Override
	public IMission cloneMission() {
		return ( new VehicleExplosionMission() );
	}

	@Override
	public String getCompanyName() {
		return "it.unical.logic_santos";
	}

	@Override
	public String getDescription() {
		return "The goal of the Mission is to make a Vehicle explosion driving an Aircraft.\n" +
			   "Find an Aircraft in the city and stole it.\n" +
			   "Then shoot some Cannon Bullets to a Vehicle\n" +
			   "When you reach this goal the Mission is going to be completed.";
	}

	@Override
	public float getGain() {
		return 1300.0f;
	}

	@Override
	public String getName() {
		return "Vehicle Explosion";
	}

	@Override
	public boolean isComplete() {
		return missionComplete;
	}
}
