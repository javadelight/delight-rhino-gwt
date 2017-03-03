[![Build Status](https://travis-ci.org/javadelight/delight-rhino-gwt.svg?branch=master)](https://travis-ci.org/javadelight/delight-rhino-gwt)

# Rhino GWT

Allows running GWT applications with [Rhino](https://developer.mozilla.org/en-US/docs/Mozilla/Projects/Rhino).

An extension to [Rhino Sandbox](https://github.com/javadelight/delight-rhino-sandbox).

## Usage

Create a new RhinoSandbox and then inject the variables required for running a GWT application.

```java
RhinoSandbox sandbox = RhinoSandboxes.create()
	
sandbox.useSealedScope= false
				
RhinoGwt.injectGwtRuntimeEnvironment(sandbox, new Closure<Runnable>() {
   public void apply(Runnable runnable) {
     new Thread(runnable).start();
   }
});



```


