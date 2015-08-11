package delight.rhinogwt.internal;

import com.google.common.base.Objects;
import delight.functional.Closure;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("all")
public final class RhinoConcurrency {
  private final Closure<Runnable> executor;
  
  private final HashMap<Integer, Timer> ids;
  
  private final AtomicInteger idCounter;
  
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
  
  public void setInterval(final Runnable fn, final int rate) {
    final int id = this.idCounter.incrementAndGet();
    final Timer timer = new Timer();
    final TimerTask _function = new TimerTask() {
      @Override
      public void run() {
        RhinoConcurrency.this.executor.apply(fn);
      }
    };
    timer.scheduleAtFixedRate(_function, 1, rate);
    this.ids.put(Integer.valueOf(id), timer);
  }
  
  public void clear(final int id) {
    final Timer timer = this.ids.get(Integer.valueOf(id));
    boolean _notEquals = (!Objects.equal(timer, null));
    if (_notEquals) {
      this.ids.remove(Integer.valueOf(id));
      timer.cancel();
    }
  }
  
  public RhinoConcurrency(final Closure<Runnable> executor) {
    this.executor = executor;
    HashMap<Integer, Timer> _hashMap = new HashMap<Integer, Timer>();
    this.ids = _hashMap;
    AtomicInteger _atomicInteger = new AtomicInteger(0);
    this.idCounter = _atomicInteger;
  }
}
