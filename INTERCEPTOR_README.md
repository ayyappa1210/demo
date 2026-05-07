# Custom Interceptor Implementation Guide

## Overview
This project now includes a custom HTTP interceptor that intercepts requests and responses at the controller level. The interceptor implements Spring's `HandlerInterceptor` interface to capture lifecycle events of HTTP requests.

## Files Created

### 1. **CustomInterceptor.java** (`src/main/java/com/example/demo/interceptor/`)
The main interceptor class that implements `HandlerInterceptor` with three key methods:

#### Methods Implemented:

**a) `preHandle()` - Before Controller**
- Called BEFORE the request reaches the controller
- Captures request details: URL, HTTP method, handler information
- Stores request start time for performance tracking
- Returns `true` to allow the request to proceed, `false` to block it

```
Request → [Interceptor.preHandle()] → Controller
```

**b) `postHandle()` - After Controller (Before Response)**
- Called AFTER the controller executes but BEFORE the view is rendered
- Can modify the ModelAndView if needed
- Useful for adding common attributes or modifying response

```
Controller → [Interceptor.postHandle()] → Response
```

**c) `afterCompletion()` - After Response Complete**
- Called AFTER the entire response is complete
- Calculates total request duration
- Handles any exceptions that occurred
- Perfect for cleanup operations and logging

```
Response → [Interceptor.afterCompletion()] → End
```

### 2. **InterceptorConfig.java** (`src/main/java/com/example/demo/interceptor/`)
Spring configuration class that registers the custom interceptor:

- Implements `WebMvcConfigurer` interface
- Overrides `addInterceptors()` method
- Registers interceptor for all paths (`/**`)
- Excludes static resources (CSS, JS, images) from interception

### 3. **InterceptorDemoController.java** (`src/main/java/com/example/demo/interceptor/`)
Sample REST controller to test the interceptor with multiple endpoints:

- `/api/hello` - Simple GET request
- `/api/greet/{name}` - GET with path variable
- `/api/process?message=...` - GET with query parameter
- `/api/submit` - POST request
- `/api/error` - Endpoint that throws an exception

## How It Works

### Request Flow with Interceptor:

```
1. HTTP Request arrives
   ↓
2. preHandle() executes
   - Logs request details
   - Stores start time
   ↓
3. Controller method executes
   - Your business logic
   ↓
4. postHandle() executes
   - Logs controller completion
   ↓
5. Response rendered/sent
   ↓
6. afterCompletion() executes
   - Logs total duration
   - Handles exceptions if any
   ↓
7. Response sent to client
```

## Testing the Interceptor

### Using Browser:
```
http://localhost:8080/api/hello
http://localhost:8080/api/greet/John
http://localhost:8080/api/process?message=Hello
```

### Using cURL:
```bash
# Simple GET
curl http://localhost:8080/api/hello

# GET with path variable
curl http://localhost:8080/api/greet/Alice

# GET with query parameter
curl "http://localhost:8080/api/process?message=TestData"

# POST request
curl -X POST "http://localhost:8080/api/submit?data=MyData"

# Error handling
curl http://localhost:8080/api/error
```

### Expected Console Output:

For a request to `/api/hello`:

```
=== INTERCEPTOR: PRE-HANDLE ===
Request URL: /api/hello
Request Method: GET
Handler: com.example.demo.interceptor.InterceptorDemoController
--- Request about to reach controller ---

>>> Inside Controller: hello() method

=== INTERCEPTOR: POST-HANDLE ===
Controller processing completed
Response Status: 200
--- Controller execution finished, view about to render ---

=== INTERCEPTOR: AFTER-COMPLETION ===
Total Request Duration: 12ms
Request completed successfully
--- Request-response cycle complete ---
```

## Configuration Details

### Path Patterns:
- **included**: `/**` - All request paths
- **excluded**: `/css/**`, `/js/**`, `/images/**` - Static resources

### Customization Options:

#### To modify path patterns:
Edit `InterceptorConfig.java`:
```java
registry.addInterceptor(new CustomInterceptor())
        .addPathPatterns("/api/**")  // Only /api/* paths
        .addPathPatterns("/admin/**") // Add multiple patterns
        .excludePathPatterns("/api/public/**"); // Exclude specific paths
```

#### To add multiple interceptors:
```java
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new CustomInterceptor())
            .addPathPatterns("/**");
    
    registry.addInterceptor(new AnotherInterceptor())
            .addPathPatterns("/admin/**");
}
```

#### To stop request propagation:
In `preHandle()`, return `false`:
```java
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (!isAuthorized(request)) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false; // Stop the request
    }
    return true; // Continue
}
```

## Use Cases for Interceptors

1. **Authentication & Authorization** - Verify user credentials before controller
2. **Logging & Monitoring** - Track all requests and responses
3. **Request/Response Header Manipulation** - Add/remove headers
4. **Performance Metrics** - Measure execution time
5. **Request Filtering** - Block malicious or invalid requests
6. **Compression** - Modify response before sending
7. **CORS Handling** - Handle cross-origin requests
8. **Token Validation** - Validate JWT or session tokens
9. **User Tracking** - Track user actions
10. **Exception Handling** - Centralize error handling

## Key Differences: Interceptors vs Filters vs AOP

| Feature | Interceptor | Filter | AOP |
|---------|-----------|--------|-----|
| Scope | HTTP Layer | Servlet Layer | Method Level |
| Access to Handler | Yes | No | Yes |
| Request Context | Full HttpServletRequest | Full | Limited |
| Main Use | Web Requests | Requests/Responses | Business Logic |
| Order Control | Yes | Yes | Yes |
| Exception Handling | Yes | Yes | Yes |

## Running the Application

1. **Build the project:**
   ```bash
   mvn clean package
   ```

2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

3. **Test the endpoints** as shown in the "Testing the Interceptor" section above

The interceptor logs will appear in your application console, showing the complete request lifecycle for each HTTP request.

## Notes

- The interceptor is registered with `@Configuration` annotation, so it's automatically detected by Spring Boot
- All three interceptor methods are being called for each request in order
- The interceptor works with all controller types: `@RestController`, `@Controller`, etc.
- Exception handling in the interceptor allows you to log and track errors
- The request duration calculation helps identify performance bottlenecks

