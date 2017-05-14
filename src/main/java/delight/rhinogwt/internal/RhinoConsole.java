package delight.rhinogwt.internal;

import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public final class RhinoConsole {
  public void log(final Object o) {
    String _string = o.toString();
    InputOutput.<String>println(_string);
  }
}
