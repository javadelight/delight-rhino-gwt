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
      Timer _timer = new Timer();
      final TimerTask _function = new TimerTask() {
        @Override
        public void run() {
          RhinoConcurrency.this.executor.apply(fn);
        }
      };
      _timer.schedule(_function, delay);
      _xblockexpression = id;
    }
    return _xblockexpression;
  }
  
  public RhinoConcurrency(final Closure<Runnable> executor) {
    this.executor = executor;
  }
}
