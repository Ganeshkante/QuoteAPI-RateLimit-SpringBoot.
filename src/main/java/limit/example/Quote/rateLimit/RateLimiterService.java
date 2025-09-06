package limit.example.Quote.rateLimit;



import org.springframework.stereotype.Component; 
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimiterService {
  private final ConcurrentHashMap<String, SimpleRateLimiter> map = new ConcurrentHashMap<>();
  private final int max = 5;
  private final long windowMs = 60_000L; 

  public boolean isAllowed(String ip) {
    return map.computeIfAbsent(ip, k -> new SimpleRateLimiter(max, windowMs)).allow();
  }
 
  public int retryAfterSeconds(String ip) { 
    SimpleRateLimiter s = map.get(ip);
    if (s == null) return 0;
    return s.retryAfterSeconds();
  }
}

