package limit.example.Quote.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Quote API", description = "Provides inspirational quotes")

public class QuoteController {
  private final List<String> q = List.of(
    "The only way to do great work is to love what you do. - Steve Jobs",
    "Life is what happens when you're busy making other plans. - John Lennon",
    "Do not watch the clock. Do what it does. Keep going. - Sam Levenson",
    "Believe you can and you're halfway there. - Theodore Roosevelt",
    "The future depends on what you do today. - Mahatma Gandhi"
  );
  private final Random r = new Random();

  @GetMapping("/quote")
  @Operation(summary = "Get random quote", description = "Returns a random inspirational quote")
  public Map<String, String> quote() {
    return Collections.singletonMap("quote", q.get(r.nextInt(q.size())));
  }
}
