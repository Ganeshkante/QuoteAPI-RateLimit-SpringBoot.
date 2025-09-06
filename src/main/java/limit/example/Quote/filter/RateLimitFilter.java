package limit.example.Quote.filter;

import org.slf4j.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import limit.example.Quote.rateLimit.RateLimiterService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RateLimitFilter extends OncePerRequestFilter {
  private final RateLimiterService svc;
  private final Logger log = LoggerFactory.getLogger(RateLimitFilter.class);

  public RateLimitFilter(RateLimiterService svc) {
    this.svc = svc;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws ServletException, IOException { 

    String ip = clientIp(req);
    String uri = req.getRequestURI();
    boolean allowed = true;

    if ("/api/quote".equals(uri) && "GET".equalsIgnoreCase(req.getMethod())) {
      allowed = svc.isAllowed(ip);
      if (!allowed) {
        int retry = svc.retryAfterSeconds(ip);
        res.setStatus(429);
        res.setContentType("application/json");
        res.getWriter().write("{\"error\":\"Rate limit exceeded. Try again in " + retry + " seconds.\"}");
        log.info("IP {} -> 429 Too Many Requests", ip);
        return;
      }
    }

    chain.doFilter(req, res);
    log.info("IP {} -> {}", ip, res.getStatus());
  }

  private String clientIp(HttpServletRequest req) { 
    String h = req.getHeader("X-Forwarded-For");
    if (h != null && !h.isEmpty()) return h.split(",")[0].trim();
    return req.getRemoteAddr(); 
  }
}
