package com.mycompany;

import java.util.stream.Stream;

public class BerlinClock {

	private enum LED {
		O,
		R,
		Y
	}
	
	public String getTopHours(int hours) {
		return getTopHoursRow(hours).toString();
	}

	public String getBottomHours(int hours) {
		int onLights = hours % 5;
		return new LightRow(LED.R, 4, onLights).toString();
	}
	
	public String getBottomMinutes(int minutes) {
		int onLights = minutes % 5;
		return new LightRow(LED.Y, 4, onLights).toString();
	}

	public String getTopMinutes(int minutes) {
		int yellowLights = minutes / 5;
		return new LightRow(LED.Y, 11, yellowLights).toString().replaceAll("YYY", "YYR");
	}
	
	public String[] convertToBerlinTime(String time) {
		int[] timeParts = Stream.of(time.split(":")).mapToInt(Integer::parseInt).toArray();
		String[] result = new String[] {
				getSeconds(timeParts[2]),
				getTopHours(timeParts[0]),
				getBottomHours(timeParts[0]),
				getTopMinutes(timeParts[1]),
				getBottomMinutes(timeParts[1])
		};
		return result;
	}

	public String getSeconds(int i) {
		return i % 2 == 0 ? "Y" : "O";
	}
	
	private LightRow getTopHoursRow(int hours) {
		int totalLights = 4;
		int onLights = hours / 5; 	
		return new LightRow(LED.R, totalLights, onLights);
	}
	
	private class LightRow {
		private final LED led;
		private final int totalLights;
		private final int onLights;

		public LightRow(LED led, int totalLights, int onLights) {
			this.led = led;
			this.totalLights = totalLights;
			this.onLights = onLights;
		}
		
		@Override
		public String toString() {
			StringBuilder resultBuilder = new StringBuilder();		
			for(int i=0; i<onLights; i++) {
				resultBuilder.append(led);
			}
			
			int offLights = totalLights - onLights;
			for(int i=0; i<offLights ; i++) {
				resultBuilder.append(LED.O);
			}
			
			return resultBuilder.toString();
		}
	}
}
