package delight.rhinogwt.internal;

import delight.functional.Closure;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("all")
public class RhinoConcurrency {
  private Closure<Runnable> executor;
  
  private HashMap<Integer, Timer> ids;
  
  private AtomicInteger idCounter;
  
  public int setTimeout(final Runnable fn, final int delay) {
    int _xblockexpression = (int) 0;
    {
      final int id = this.idCounter.incrementAndGet();
      final Timer timer = new Timer();
      final TimerTask _function = new TimerTask() {
        @Override
        public void run() {
          RhinoConcurrency.this.ids.remove(Integer.valueOf(id));
          RhinoConcurrency.this.executor.apply(fn);
        }
      };
      timer.schedule(_function, delay);
      this.ids.put(Integer.valueOf(id), timer);
      _xblockexpression = id;
    }
    return _xblockexpression;
  }
  
  public Object clearTimeout(final int id) {
    throw new Error("Unresolved compilation problems:"
      + "\nno viable alternative at input \'}\'");
  }
  
  public RhinoConcurrency(final Closure<Runnable> executor) {
    this.executor = executor;
  }
}
