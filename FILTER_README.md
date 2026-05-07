



# Custom Filters Implementation Guide

## Overview
This project now includes four custom HTTP filters with different ordering mechanisms. Filters are part of the Servlet API and execute before interceptors in the request processing chain.

## Files Created

### Filter Classes:

#### 1. **FirstFilter.java** & **SecondFilter.java**
- Use `@Component` + `@Order` annotation approach
- Automatically registered by Spring Boot
- Apply to all URLs (`/*`)

#### 2. **ThirdFilter.java** & **FourthFilter.java**
- Use programmatic registration via `FilterRegistrationBean`
- More control over URL patterns and ordering
- Apply only to `/api/*` URLs

#### 3. **FilterConfig.java**
- Configuration class for programmatic filter registration
- Demonstrates `FilterRegistrationBean` usage

## Filter Execution Order

### Complete Request Flow:
```
HTTP Request
    ↓
1. FirstFilter (@Order(1)) - All URLs
    ↓
2. SecondFilter (@Order(2)) - All URLs
    ↓
3. ThirdFilter (Order 3) - /api/* only
    ↓
4. FourthFilter (Order 4) - /api/* only
    ↓
5. Interceptors (if configured)
    ↓
6. Controller
    ↓
7. Response processing (reverse order)
```

## Two Ways to Set Filter Order

### Method 1: Using @Order Annotation (Recommended for simple cases)

```java
@Component
@Order(1) // Lower numbers = higher priority
public class FirstFilter implements Filter {
    // Filter implementation
}
```

**Pros:**
- Simple and declarative
- Automatically registered by Spring Boot
- Less configuration code

**Cons:**
- Less control over URL patterns
- Applies to all URLs by default

### Method 2: Using FilterRegistrationBean (Recommended for complex cases)

```java
@Bean
public FilterRegistrationBean<MyFilter> myFilterRegistration() {
    FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(new MyFilter());
    registration.addUrlPatterns("/api/*", "/admin/*"); // Specific patterns
    registration.setOrder(3); // Explicit ordering
    return registration;
}
```

**Pros:**
- Full control over URL patterns
- Can set multiple URL patterns
- More configuration options
- Can disable filters conditionally

**Cons:**
- More verbose configuration
- Manual bean creation

## Filter Lifecycle

Each filter implements three methods:

### 1. `init(FilterConfig config)`
- Called once when filter is initialized
- Good for setup operations

### 2. `doFilter(ServletRequest request, ServletResponse response, FilterChain chain)`
- Called for each request
- **Request processing:** Before calling `chain.doFilter()`
- **Response processing:** After `chain.doFilter()` returns
- Must call `chain.doFilter()` to continue the chain

### 3. `destroy()`
- Called once when filter is destroyed
- Good for cleanup operations

## Testing the Filters

### Test URLs:

#### For all filters (FirstFilter & SecondFilter):
```
http://localhost:8080/any-url
```

#### For programmatic filters (ThirdFilter & FourthFilter):
```
http://localhost:8080/api/hello
http://localhost:8080/api/test
```

#### URLs that bypass programmatic filters:
```
http://localhost:8080/other/path
```

### Expected Console Output for `/api/hello`:

```
=== FIRST FILTER: DO FILTER ===
Request URL: /api/hello
Request Method: GET
Filter Order: 1 (First)
--- First filter processing complete, passing to next filter ---

=== SECOND FILTER: DO FILTER ===
Request URL: /api/hello
Request Method: GET
Filter Order: 2 (Second)
First filter executed 5ms ago
--- Second filter processing complete, passing to next in chain ---

=== THIRD FILTER: DO FILTER ===
Request URL: /api/hello
Filter Order: 3 (Third - Programmatic Registration)
--- Third filter processing complete ---

=== FOURTH FILTER: DO FILTER ===
Request URL: /api/hello
Filter Order: 4 (Fourth - Programmatic Registration)
--- Fourth filter processing complete ---

=== INTERCEPTOR: PRE-HANDLE ===
...interceptor logs...

>>> Inside Controller: hello() method

=== INTERCEPTOR: POST-HANDLE ===
...interceptor logs...

=== FOURTH FILTER: RESPONSE PROCESSING ===
=== THIRD FILTER: RESPONSE PROCESSING ===
=== SECOND FILTER: RESPONSE PROCESSING ===
=== FIRST FILTER: RESPONSE PROCESSING ===

=== INTERCEPTOR: AFTER-COMPLETION ===
...interceptor logs...
```

## Response Headers Added

Each filter adds a custom response header:

- `X-First-Filter: Executed`
- `X-Second-Filter: Executed`
- `X-Third-Filter: Executed`
- `X-Fourth-Filter: Executed`

## Key Differences: Filters vs Interceptors

| Feature | Filter | Interceptor |
|---------|--------|-------------|
| API | Servlet API | Spring MVC |
| Execution Order | Before Interceptors | After Filters |
| Access to Request | Full HttpServletRequest | HttpServletRequest |
| Access to Response | Full HttpServletResponse | HttpServletResponse |
| Spring Integration | Limited | Full Spring Context |
| Configuration | FilterRegistrationBean | WebMvcConfigurer |
| Use Cases | Global request/response processing | Controller-specific logic |

## Common Use Cases for Filters

1. **Authentication & Security** - Basic auth, API keys
2. **CORS Handling** - Cross-origin resource sharing
3. **Request/Response Logging** - HTTP traffic monitoring
4. **Compression** - GZIP compression
5. **Caching** - Response caching
6. **Rate Limiting** - Request throttling
7. **Request Validation** - Input sanitization
8. **Header Manipulation** - Adding/removing headers
9. **Encoding** - Character encoding
10. **Session Management** - Session handling

## Advanced Configuration

### Conditional Filter Registration:

```java
@Bean
public FilterRegistrationBean<MyFilter> conditionalFilter(
        @Value("${app.filters.enabled:true}") boolean enabled) {

    FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(new MyFilter());
    registration.setEnabled(enabled); // Enable/disable based on property
    registration.setOrder(5);
    return registration;
}
```

### Multiple URL Patterns:

```java
@Bean
public FilterRegistrationBean<MyFilter> multiPatternFilter() {
    FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<>();
    registration.setFilter(new MyFilter());
    registration.addUrlPatterns("/api/*", "/admin/*", "/public/*");
    registration.setOrder(1);
    return registration;
}
```

### Filter with Dependencies:

```java
@Component
@Order(1)
public class DependencyFilter implements Filter {

    private final MyService myService;

    public DependencyFilter(MyService myService) {
        this.myService = myService;
    }

    // Filter implementation using injected service
}
```

## Running the Application

1. **Build the project:**
   ```bash
   mvn clean package
   ```

2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

3. **Test the filters:**
   ```bash
   # Test all filters
   curl -v http://localhost:8080/api/hello

   # Test only @Order filters
   curl -v http://localhost:8080/other/path
   ```

## Order Precedence Rules

1. **@Order annotation** takes precedence over programmatic ordering
2. **Lower @Order values** = **higher priority** (executed first)
3. **FilterRegistrationBean order** is used when @Order is not present
4. **Spring Security filters** have their own ordering system
5. **Default order** is `Integer.MAX_VALUE` if no order is specified

## Best Practices

1. **Keep filters lightweight** - They execute on every request
2. **Use appropriate ordering** - Security filters should come first
3. **Handle exceptions properly** - Don't break the filter chain
4. **Document filter purposes** - Especially for complex filter chains
5. **Test filter interactions** - Ensure filters work well together
6. **Consider performance impact** - Profile filter execution time
7. **Use meaningful names** - Filter classes should indicate their purpose

## Troubleshooting

### Filters Not Executing:
- Check if `@Component` is present (for annotation-based filters)
- Verify URL patterns match the request
- Ensure filters are in the component scan path

### Wrong Execution Order:
- Check @Order values (lower = higher priority)
- Verify FilterRegistrationBean order settings
- Look for conflicting configurations

### Filter Not Registered:
- Check for compilation errors
- Verify Spring Boot auto-configuration is working
- Use `@EnableAutoConfiguration` if needed

The filter chain is now fully configured and will process all HTTP requests according to the specified order! 🚀
