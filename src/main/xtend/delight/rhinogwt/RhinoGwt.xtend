package delight.rhinogwt

import delight.rhinogwt.internal.RhinoConsole
import delight.rhinosandox.RhinoSandbox
import org.mozilla.javascript.JavaAdapter

class RhinoGwt {
	
	/**
	 * Add a basic GWT runtime environment to the global scope of the provided sandbox.
	 */
	def injectGwtRuntimeEnvironment(RhinoSandbox sandbox, Runnable operationsRunner) {
		sandbox.inject("console", new RhinoConsole)
		
		sandbox.allow(Runnable)
		sandbox.allow(JavaAdapter)
		
		// set up timers
		sandbox.evalWithGlobalScope('''
		// based on http://stackoverflow.com/questions/2261705/how-to-run-a-javascript-function-asynchronously-without-using-settimeout
		var executor = timerExecutor;
		var counter = 1;
		var ids = {};
		
		setTimeout = function (fn,delay) {
		    var id = counter++;
		    ids[id] = executor.schedule(runnable, delay, 
		        java.util.concurrent.TimeUnit.MILLISECONDS);
		    return id;
		}
		
		clearTimeout = function (id) {
		    ids[id].cancel(false);
		    executor.purge();
		    delete ids[id];
		}
		
		setInterval = function (fn,delay) {
		    var id = counter++;
		    var runnable = new JavaAdapter(java.lang.Runnable, {run: fn});
		    ids[id] = executor.scheduleAtFixedRate(runnable, delay, delay, 
		        java.util.concurrent.TimeUnit.MILLISECONDS);
		    return id;
		}
		
		clearInterval = clearTimeout;
		
		''')
		
		// set up environment for GWT
		sandbox.evalWithGlobalScope('''
			
			var navigator = {
				userAgent: "gecko1_8"
			};
			
			var $wnd = {
				alert: function(msg) {
					console.log(msg);
				},
				setTimeout: setTimeout,
				setInterval: setInterval,
				clearTimeout: clearTimeout,
				clearInterval: clearInterval,
				location: {
					hostname: "localhost"	
				}
			};
			
			var window = $wnd;
			
			var $doc = {
				
			};
			
		''')
		
		
	}
	
}