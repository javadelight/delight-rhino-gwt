package delight.rhinogwt;

import delight.functional.Closure;
import delight.rhinogwt.internal.RhinoConcurrency;
import delight.rhinogwt.internal.RhinoConsole;
import delight.rhinosandox.RhinoSandbox;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.mozilla.javascript.JavaAdapter;

@SuppressWarnings("all")
public class RhinoGwt {
  /**
   * Add a basic GWT runtime environment to the global scope of the provided sandbox.
   */
  public static Object injectGwtRuntimeEnvironment(final RhinoSandbox sandbox, final Closure<Runnable> operationsRunner) {
    Object _xblockexpression = null;
    {
      sandbox.allow(Runnable.class);
      sandbox.allow(JavaAdapter.class);
      RhinoConsole _rhinoConsole = new RhinoConsole();
      sandbox.inject("console", _rhinoConsole);
      RhinoConcurrency _rhinoConcurrency = new RhinoConcurrency(operationsRunner);
      sandbox.inject("concurrency", _rhinoConcurrency);
      String _plus = (RhinoGwt.class + "_define_environment_timer");
      StringConcatenation _builder = new StringConcatenation();
      _builder.newLine();
      _builder.append("setTimeout = function (fn,delay) {");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("var runnable = new JavaAdapter(java.lang.Runnable, {run: fn});");
      _builder.newLine();
      _builder.newLine();
      _builder.append("    ");
      _builder.append("var id = concurrency.setTimeout(fn, delay);");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      _builder.append("    ");
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
      _builder.append("concurrency.clear(id);");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("setInterval = function (fn,interval) {");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("var runnable = new JavaAdapter(java.lang.Runnable, {run: fn});");
      _builder.newLine();
      _builder.append("    ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var id = concurrency.setInterval(fn,interval);");
      _builder.newLine();
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
      sandbox.evalWithGlobalScope(_plus, _builder.toString());
      String _plus_1 = (RhinoGwt.class + "_define_environment_browser");
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
      _builder_1.append("},");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("console: console");
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
      _xblockexpression = sandbox.evalWithGlobalScope(_plus_1, _builder_1.toString());
    }
    return _xblockexpression;
  }
}
