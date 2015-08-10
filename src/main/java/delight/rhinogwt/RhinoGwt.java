package delight.rhinogwt;

import delight.rhinogwt.internal.RhinoConsole;
import delight.rhinosandox.RhinoSandbox;
import org.eclipse.xtend2.lib.StringConcatenation;

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
      StringConcatenation _builder = new StringConcatenation();
      _builder.newLine();
      _builder.append("var navigator = {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("userAgent: \"gecko1_8\"");
      _builder.newLine();
      _builder.append("};");
      _builder.newLine();
      _builder.newLine();
      _builder.append("var $wnd = {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("alert: function(msg) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("console.log(msg);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("},");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("setTimeout: setTimeout,");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("setInterval: setInterval,");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("clearTimeout: clearTimeout,");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("clearInterval: clearInterval,");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("location: {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("hostname: \"localhost\"\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("};");
      _builder.newLine();
      _builder.newLine();
      _builder.append("var window = $wnd;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("var $doc = {");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("};");
      _builder.newLine();
      _builder.newLine();
      _xblockexpression = sandbox.evalWithGlobalScope(_builder.toString());
    }
    return _xblockexpression;
  }
}
