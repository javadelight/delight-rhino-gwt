package delight.rhinogwt

import delight.rhinogwt.internal.RhinoConsole
import delight.rhinosandox.RhinoSandbox

class RhinoGwt {
	
	/**
	 * Add a basic GWT runtime environment to the global scope of the provided sandbox.
	 */
	def injectGwtRuntimeEnvironment(RhinoSandbox sandbox, Runnable operationsRunner) {
		sandbox.inject("console", new RhinoConsole)
		
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
		
		// set up timers
	}
	
}