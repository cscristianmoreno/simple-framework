# Simple IoC + Web Annotations

This project include <b>dependencie injection</b> and <b>controllers annotations</b> that consume a servlet

## How to work?

### Requeriments

```html
<dependencies>
    <dependency>
      <groupId>io.github.cscristianmoreno</groupId>
      <artifactId>web</artifactId>
      <version>1.0</version>
    </dependency>
</dependencies>
```

```java
/** Annotation to launch tomcat server */
@Server
public class App {
    public static void main(String[] args) throws Exception {
        /** Initialized the IoC */
        IOCInitializer.main(args);
    }
}
```

### How to work the initilization

The implementation <b>ServletContainerInitializer</b> is the one in charge <b>onStartup</b> method.

This interface:

```java
@HandlesTypes(Controller.class)
```

load all classes that matches with <b>@Controller</b> annotation, next, i use a <b>Java Reflection</b> to access a class property.

### Change package scan

For default package scan is <b>com/../..</b> If you wish change to another package name, you need use

```java
PackageScanUtil.setScan("org");
IOCInitializer.main(args);
```

## Controller example

```java
@Controller("/users")
public class UserController {
    @GET("/value/{id}/test/{name}")
    private String message(@Variable int id, @Variable String name) {
        return "Hi! " + name + " " + "#" + Integer.toString(id)
    }

    @POST("/save")
    public Users save(@Body Users users) {
        return users;
    }
}
```

## IoC example
```java
@Component
public class MyComponent {
    
    public String getMessage() {
        return "MyComponent has been returned this message";
    }
}
```
```java
@Injectable
public class MyInjectable {
    
    @Inject
    private MyComponent myComponent;

    public String getMessage() {
        return myComponent.getMessage();
    }
}
```
Or else

```java
@Component
public class SimpleComponent {

    private final MyComponent myInjectable;

    @Constructor
    public UserController(final MyInjectable myInjectable) {
        this.myInjectable = myInjectable;
    }

    ...
}
```

## Servlet Annotations 

* <b>@GET("/../{...}/.../{...}")
* <b>@POST("/../{...}/.../{...}")
* <b>@PUT("/../{...}/.../{...}")
* <b>@PATCH("/../{...}/.../{...}")
* <b>@DELETE("/../{...}/.../{...}")

## Method Params Annotations

* <b>@Body</b>
* <b>@Variable</b>
* <b>@Param(..)</b>
* <b>@Header(..)</b>


## IoC Annotations

## @Injectable
    Create a new class instance.

    The classes with this annotation can use @Inject in field or constructor 
    
    This annotation is required for use in  @Inject fields.

    * Can inject dependencie

## @Inject
    Get a instance to @Injectable class.
    
    This annotation required @Component or @Injectable in the same class to can use.

    @Inject -> (@Component | @Injectable)

## @Component
    Create a new classe instance.

    This annotation is required to inject dependencie

    * Can inject dependencie

## @Constructor (Optional)
    A semanthic annotation to indicate that fields will injected through constructor
      
## @Server (Required in main class)
    This annotation run Apache Tomcat