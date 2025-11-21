Logic Santos Missions
===

Java project to develop external missions for the [Logic Santos Valley](https://github.com/AgostinoRizzo/logic-santos) game (see section **Missions** for additional details about the mission system and interfaces).

## Usage
As a prerequisite, download the [`missions_lib/logic-santos-mission-interface.jar`](https://github.com/AgostinoRizzo/logic-santos/blob/main/missions_lib/logic-santos-mission-interface.jar) file, and place it under the `lib` folder of your project to access all interfaces of the mission system.

Create a new class implementing the `IMission` java interface. For example, let us create a new mission where the goal is to make a vehicle explosion driving an aircraft (for convenience, let us add two boolean fields `missionComplete` and `drivingAircraft`).
```java
public class VehicleExplosionMission implements IMission {

    private boolean missionComplete=false;
	private boolean drivingAircraft=false;

    // class body here
}
```

Implementing the `IMission` interface requires us to override the following methods.
*   `onLoggingEvent` gets invoked when a new event is triggered during gameplay. It represents the method containing the logic that collects the events that are relevant to purpose of the mission. In our example, we are interested in knowing when the player first stoles an aircraft (`AircraftLoggingEvent.STOLE_AIRCRAFT_EVENT`), and then produces a vehicle explosion (`VehicleLoggingEvent.VEHICLE_EXPLOSION_EVENT`) while driving that aircraft. To control the latter condition, it suffices to check when the player gets off the aircraft: in that case, a vehicle explosion does not have to be taken into consideration!
    ```java
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
    ```
*   `getDescription` returns a full description of the mission which will then be displayed in the corresponding game menu.
    ```java
    @Override
	public String getDescription() {
		return "The goal of the Mission is to make a Vehicle explosion driving an Aircraft.\n" +
			   "Find an Aircraft in the city and stole it.\n" +
			   "Then shoot some Cannon Bullets to a Vehicle\n" +
			   "When you reach this goal the Mission is going to be completed.";
	}
    ```
*   `getGain` returns the amount of money gained by the player in case of mission completion.
    ```java
    @Override
	public float getGain() {
		return 1300.0f;
	}
    ```
See [VehicleExplosionMission](./src/it/unical/logic_santos/mission/VehicleExplosionMission.java) for the full code of the class.

All developed missions can be found in the `it.unical.logic_santos.mission` java package.