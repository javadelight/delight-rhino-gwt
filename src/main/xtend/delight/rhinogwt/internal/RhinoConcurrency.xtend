package delight.rhinogwt.internal

import delight.functional.Closure
import java.util.HashMap
import java.util.Timer
import java.util.concurrent.atomic.AtomicInteger

class RhinoConcurrency {
	
	Closure<Runnable> executor;
	
	HashMap<Integer, Timer> ids;
	
	AtomicInteger idCounter;
	
	def int setTimeout(Runnable fn, int delay) {
		
		val id = idCounter.incrementAndGet
		
		new Timer().schedule([
			executor.apply(fn)
		], delay)
		
		id
		
	} 
	
	new(Closure<Runnable> executor) {
		this.executor = executor
	}
	
}