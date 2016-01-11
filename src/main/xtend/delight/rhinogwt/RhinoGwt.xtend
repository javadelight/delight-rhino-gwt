package delight.rhinogwt

import delight.functional.Closure
import delight.rhinogwt.internal.RhinoConcurrency
import delight.rhinogwt.internal.RhinoConsole
import delight.rhinosandox.RhinoSandbox
import org.mozilla.javascript.JavaAdapter

class RhinoGwt {
	
	/**
	 * Add a basic GWT runtime environment to the global scope of the provided sandbox.
	 */
	static def injectGwtRuntimeEnvironment(RhinoSandbox sandbox, Closure<Runnable> operationsRunner) {
		
		
		sandbox.allow(Runnable)
		sandbox.allow(JavaAdapter)
		
		sandbox.inject("console", new RhinoConsole)
		sandbox.inject("concurrency", new RhinoConcurrency(operationsRunner))
		
		// set up timers
		sandbox.evalWithGlobalScope(RhinoGwt+"_define_environment", '''
		
		setTimeout = function (fn,delay) {
		    
		    var runnable = new JavaAdapter(java.lang.Runnable, {run: fn});

		    var id = concurrency.setTimeout(fn, delay);
		    
		    
		    return id;
		}
		
		clearTimeout = function (id) {
		    concurrency.clear(id);
		}
		
		setInterval = function (fn,interval) {
		    
		    var runnable = new JavaAdapter(java.lang.Runnable, {run: fn});
		    
			var id = concurrency.setInterval(fn,interval);

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
				},
				console: console
			};
			
			var window = $wnd;
			
			var $doc = {
				
			};
			
		''')
		
		
	}
	
}