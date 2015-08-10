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
		
		val timer = new Timer()
		timer.schedule([
			ids.remove(id)
			executor.apply(fn)
		], delay)
		
		ids.put(id, timer)
		
		id
		
	} 
	
	def clear(int id) {
		val timer = ids.get(id)
		
		if (timer != null) {
			ids.remove(id)
			timer.cancel
		}
	}
	
	
	
	new(Closure<Runnable> executor) {
		this.executor = executor
	}
	
}