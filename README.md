[![Build Status](https://travis-ci.org/javadelight/delight-rhino-gwt.svg?branch=master)](https://travis-ci.org/javadelight/delight-rhino-gwt)

# Rhino GWT

Allows running GWT applications with [Rhino](https://developer.mozilla.org/en-US/docs/Mozilla/Projects/Rhino).

An extension to [Rhino Sandbox](https://github.com/javadelight/delight-rhino-sandbox).

## Usage

Create a new RhinoSandbox and then inject the variables required for running a GWT application.

```java
RhinoSandbox sandbox = RhinoSandboxes.create();
	
sandbox.setUseSealedScope(false);
				
RhinoGwt.injectGwtRuntimeEnvironment(sandbox, new Closure<Runnable>() {
   public void apply(Runnable runnable) {
     new Thread(runnable).start();
   }
});

String moduleJs = // ... (get the JS of the GWT module)

sandbox.evalWithGlobalScope(path,moduleJs);
```

Note that the JS for the GWT module should contain the GWT app compiled into one page. To do this, see the [GWT Simple Linker](https://github.com/javadelight/delight-gwt-simple-linker) project.


