package org.loksatta.android.core;

public class Event extends AbstractCore {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Title of the Event
	 */
	private String title;

	/**
	 * Full Description of the Event
	 */
	private String description;

	/**
	 * Location at which event is organizing
	 */
	private String location;

	/**
	 * When the event starts
	 */
	private String when;

	/**
	 * Map Location of the event which includes longitude and latitude
	 */
	private String mapLocation;

	/**
	 * attendance of event
	 */
	private String attendace;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the when
	 */
	public String getWhen() {
		return when;
	}

	/**
	 * @param when
	 *            the when to set
	 */
	public void setWhen(String when) {
		this.when = when;
	}

	/**
	 * @return the mapLocation
	 */
	public String getMapLocation() {
		return mapLocation;
	}

	/**
	 * @param mapLocation
	 *            the mapLocation to set
	 */
	public void setMapLocation(String mapLocation) {
		this.mapLocation = mapLocation;
	}

	/**
	 * @return the attendace
	 */
	public String getAttendace() {
		return attendace;
	}

	/**
	 * @param attendace
	 *            the attendace to set
	 */
	public void setAttendace(String attendace) {
		this.attendace = attendace;
	}

}
