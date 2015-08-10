package delight.rhinogwt.internal

import java.util.Timer
import java.util.TimerTask

class RhinoConcurrency {
	
	Runnable executor;
	
	def String setTimeout(Runnable fn, int delay) {
		
		new Timer().schedule([], delay)
		
	} 
	
}