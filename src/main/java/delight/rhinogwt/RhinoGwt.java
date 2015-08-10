package delight.rhinogwt;

import delight.rhinogwt.internal.RhinoConsole;
import delight.rhinosandox.RhinoSandbox;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.mozilla.javascript.JavaAdapter;

@SuppressWarnings("all")
public class RhinoGwt {
  /**
   * Add a basic GWT runtime environment to the global scope of the provided sandbox.
   */
  public Object injectGwtRuntimeEnvironment(final RhinoSandbox sandbox, final Runnable operationsRunner) {
    Object _xblockexpression = null;
    {
      RhinoConsole _rhinoConsole = new RhinoConsole();
      sandbox.inject("console", _rhinoConsole);
      sandbox.allow(Runnable.class);
      sandbox.allow(JavaAdapter.class);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("// based on http://stackoverflow.com/questions/2261705/how-to-run-a-javascript-function-asynchronously-without-using-settimeout");
      _builder.newLine();
      _builder.append("var executor = timerExecutor;");
      _builder.newLine();
      _builder.append("var counter = 1;");
      _builder.newLine();
      _builder.append("var ids = {};");
      _builder.newLine();
      _builder.newLine();
      _builder.append("setTimeout = function (fn,delay) {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("var id = counter++;");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("ids[id] = executor.schedule(runnable, delay, ");
      _builder.newLine();
      _builder.append("        ");
      _builder.append("java.util.concurrent.TimeUnit.MILLISECONDS);");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return id;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("clearTimeout = function (id) {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("ids[id].cancel(false);");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("executor.purge();");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("delete ids[id];");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("setInterval = function (fn,delay) {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("var id = counter++;");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("var runnable = new JavaAdapter(java.lang.Runnable, {run: fn});");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("ids[id] = executor.scheduleAtFixedRate(runnable, delay, delay, ");
      _builder.newLine();
      _builder.append("        ");
      _builder.append("java.util.concurrent.TimeUnit.MILLISECONDS);");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("return id;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("clearInterval = clearTimeout;");
      _builder.newLine();
      _builder.newLine();
      sandbox.evalWithGlobalScope(_builder.toString());
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.newLine();
      _builder_1.append("var navigator = {");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("userAgent: \"gecko1_8\"");
      _builder_1.newLine();
      _builder_1.append("};");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("var $wnd = {");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("alert: function(msg) {");
      _builder_1.newLine();
      _builder_1.append("\t\t");
      _builder_1.append("console.log(msg);");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("},");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("setTimeout: setTimeout,");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("setInterval: setInterval,");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("clearTimeout: clearTimeout,");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("clearInterval: clearInterval,");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("location: {");
      _builder_1.newLine();
      _builder_1.append("\t\t");
      _builder_1.append("hostname: \"localhost\"\t");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("}");
      _builder_1.newLine();
      _builder_1.append("};");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("var window = $wnd;");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("var $doc = {");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.newLine();
      _builder_1.append("};");
      _builder_1.newLine();
      _builder_1.newLine();
      _xblockexpression = sandbox.evalWithGlobalScope(_builder_1.toString());
    }
    return _xblockexpression;
  }
}
