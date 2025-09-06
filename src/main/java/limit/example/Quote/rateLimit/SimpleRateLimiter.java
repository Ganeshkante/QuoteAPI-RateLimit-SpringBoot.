package limit.example.Quote.rateLimit;



public class SimpleRateLimiter {
  private final int max;
  private final long windowMs;
  private int cnt;
  private long start;

  public SimpleRateLimiter(int max, long windowMs) {
    this.max = max;
    this.windowMs = windowMs;
    this.cnt = 0;
    this.start = System.currentTimeMillis();
  }

  public synchronized boolean allow() {
    long now = System.currentTimeMillis();
    if (now - start >= windowMs) {
      start = now;
      cnt = 0;
    } 
    if (cnt < max) {
      cnt++;
      return true;
    }
    return false;
  }

  public synchronized int retryAfterSeconds() {
    long now = System.currentTimeMillis();
    long left = windowMs - (now - start);
    if (left < 0) return 0; 
    return (int) Math.ceil(left / 1000.0);
  }
}

