# 错误处理

### 1. Error处理自动配置：

```Java
@Configuration
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
// Load before the main WebMvcAutoConfiguration so that the error View is available
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties({ ServerProperties.class, ResourceProperties.class,
      WebMvcProperties.class })
public class ErrorMvcAutoConfiguration {
    // 错误处理的基础controller
    @Bean
	@ConditionalOnMissingBean(value = ErrorController.class, search = SearchStrategy.CURRENT)
	public BasicErrorController basicErrorController(ErrorAttributes errorAttributes) {
		return new BasicErrorController(errorAttributes, 	                   this.serverProperties.getError(),
				this.errorViewResolvers);
	}
    
    // 
    @Bean
	@ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
	public DefaultErrorAttributes errorAttributes() {
		return new DefaultErrorAttributes(
				this.serverProperties.getError().isIncludeException());
	}
    
}
```

### 2.错误处理流程

（1）页面出现错误，转发到`/error`

（2）`/error`由BasicErrorController处理

有两种处理方式，就以浏览器访问为例：

```java
@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView errorHtml(HttpServletRequest request,
			HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
				request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
		return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
	}
```

其中主要的一个部分是`getErrorAttributes`方法：

```java
protected Map<String, Object> getErrorAttributes(HttpServletRequest request,
			boolean includeStackTrace) {
		WebRequest webRequest = new ServletWebRequest(request);
		return this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
	}
```

底层调用的是`DefaultErrorAttributes`中的`getErrorAttributes`:

```java
public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) { // 设置错误页面的参数，本质是一个Map 
    Map<String, Object> errorAttributes = new LinkedHashMap();
    errorAttributes.put("timestamp", new Date());
    this.addStatus(errorAttributes, webRequest);
    this.addErrorDetails(errorAttributes, webRequest, includeStackTrace);
    this.addPath(errorAttributes, webRequest);
    return errorAttributes;
}
```

（3）根据请求源返回对应的结果（html页面或json数据）

### 3. 错误处理Controller:BasicErrorController

错误有两种处理结果，主要是根据请求源自适应返回对应的结果：

（1）浏览器请求错误返回错误页面

（2）客户端（Android/IOS）请求返回JSON数据

```Java
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}") // 错误路径，如果${server.error.path}是空就设置为${error.path},如果为空就配置为/error
public class BasicErrorController extends AbstractErrorController {
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE) // 错误结果返回html页面
	public ModelAndView errorHtml(HttpServletRequest request,
			HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
				request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
		return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
	}

	@RequestMapping // 错误结果返回json
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request,
				isIncludeStackTrace(request, MediaType.ALL));
		HttpStatus status = getStatus(request);
		return new ResponseEntity<>(body, status);
	}
}
```

#### BasicController的初始化 

（1）直接构造一个`BasicController`类型的Bean

```java
@Bean
@ConditionalOnMissingBean(value = ErrorController.class, search = SearchStrategy.CURRENT)
public BasicErrorController basicErrorController(ErrorAttributes errorAttributes) {
   return new BasicErrorController(errorAttributes, this.serverProperties.getError(),
         this.errorViewResolvers);
}
```



```java
// 构造器
public BasicErrorController(ErrorAttributes errorAttributes,
			ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
		super(errorAttributes, errorViewResolvers);
		Assert.notNull(errorProperties, "ErrorProperties must not be null");
		this.errorProperties = errorProperties;
	}
```

- errorAttributes是返回结果中的数据项

  ```java
  public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
          Map<String, Object> errorAttributes = new LinkedHashMap();
          errorAttributes.put("timestamp", new Date());
          this.addStatus(errorAttributes, webRequest);
          this.addErrorDetails(errorAttributes, webRequest, includeStackTrace);
          this.addPath(errorAttributes, webRequest);
          return errorAttributes;
      }
  ```

- errorProperties是错误参数：貌似就是用来判断错误的请求路径(默认`/error`)是否为空

  ```java
  public BasicErrorController(ErrorAttributes errorAttributes,
        ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
     super(errorAttributes, errorViewResolvers);
     Assert.notNull(errorProperties, "ErrorProperties must not be null");
     this.errorProperties = errorProperties;
  }
  ```

- **errorViewResolvers**

  **暂时不清楚**



# 日志

## 1. 日志类型

## 2. 使用

```java
/**
 * Created by XiaoX on 2018/12/6.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LogTest {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void contextLoader() {
        logger.trace("这是一个trace...");
        logger.debug("这是一个debug...");
        logger.info("这是一个info...");
        logger.warn("这是一个warn...");
        logger.error("这是一个error...");
    }
}
```



