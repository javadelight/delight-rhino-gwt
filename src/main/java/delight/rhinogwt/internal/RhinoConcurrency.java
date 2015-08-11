package delight.rhinogwt.internal;

import com.google.common.base.Objects;
import delight.functional.Closure;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public final class RhinoConcurrency {
  private Closure<Runnable> executor;
  
  private HashMap<Integer, Timer> ids;
  
  private AtomicInteger idCounter;
  
  public int setTimeout(final Runnable fn, final int delay) {
    int _xblockexpression = (int) 0;
    {
      InputOutput.<String>println(((("here " + fn) + " ") + Integer.valueOf(delay)));
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
  }
}
