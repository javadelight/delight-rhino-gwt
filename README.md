[![Build Status](https://travis-ci.org/javadelight/delight-rhino-gwt.svg?branch=master)](https://travis-ci.org/javadelight/delight-rhino-gwt)

# Rhino GWT

Allows running GWT applications with [Rhino](https://developer.mozilla.org/en-US/docs/Mozilla/Projects/Rhino).

An extension to [Rhino Sandbox]().

## Usage

```java
RhinoSandbox sandbox = RhinoSandboxes.create()
	
sandbox.useSealedScope= false
				
RhinoGwt.injectGwtRuntimeEnvironment(sandbox, new Closure<Runnable>() {
   public void apply(Runnable runnable) {
     
   }
})
```


