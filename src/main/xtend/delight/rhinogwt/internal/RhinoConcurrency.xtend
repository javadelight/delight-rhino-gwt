package delight.rhinogwt.internal

import java.util.HashMap
import java.util.Timer
import java.util.concurrent.atomic.AtomicInteger

class RhinoConcurrency {
	
	Runnable executor;
	
	HashMap<Integer, Timer> ids;
	
	AtomicInteger idCounter;
	
	def int setTimeout(Runnable fn, int delay) {
		
		val id = idCounter.incrementAndGet
		
		new Timer().schedule([
			fn.run()
		], delay)
		
		id
		
	} 
	
}