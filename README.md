# container-network

Network Request

[中文](./README_zh_CN.md)

Support function：

- [x] GetRequest
- [x] PostRequest
- [x] PutRequest
- [x] DeleteRequest

# Support function：

Request parameter combination problem：
Request parameters can be combined freely, so do not violate the request rules, such as Get request can not contain Body

```xml
<!--Adding dependencies to pom. XML-->
<dependency>
    <artifactId>container-network-loading</artifactId>
    <groupId>io.github.thierrysquirrel</groupId>
    <version>1.0.0.0-RELEASE</version>
</dependency>
```  

### configuration file

 ```properties
 ## application.properties
network.connect-timeout=#Connect Timeout
network.read-timeout=#Read Timeout
network.write-timeout=#Write Timeout
 ```

# Start Network

 ```java

@ScannerPackage(packageName = "com.hello.world.web.network")

public class NetworkRegistrationImpl implements InterfaceManualRegistration {
    @Override
    public void scannerAll(List<Class<?>> scannerClassList, Map<Class<?>, Object> registrationMap) {
        NetworkRegistration.networkRegistrationScannerAll(scannerClassList, registrationMap);
    }
}
 ```

# Build Get、Delete Request

 ```java

@Network
public interface GetDeleteRequestDemo {
    @GetRequest("https://***.com/***")
    String getA(@NetworkHeader("Header") String header, @NetworkParam("paramA") String paramA, @NetworkParam("paramB") String paramB);

    @GetRequest("https://***.com/***.png")
    InputStream getB();

    @DeleteRequest("https://***.com/***")
    String delete(@NetworkHeader("Header") String header, @NetworkParam("paramA") String paramA, @NetworkParam("paramB") String paramB);

}
 ```

# Build Post,Put Request

 ```java

@Network
public interface PostPutRequestDemo {
    @PostRequest("https://***.com/***")
    String postA(@NetworkHeader("Header") String header, @NetworkParam("param") String paramA, @NetworkBody String body);

    @PostRequest("https://***.com/***")
    String postB(@NetworkFile("fileName") File fileName);

    @PutRequest("https://***.com/***")
    String putA(@NetworkHeader("Header") String header, @NetworkParam("param") String paramA, @NetworkBody String body);

    @PutRequest("https://***.com/***")
    String putB(@NetworkFile("fileName") File fileName);
}
 ```

# Send Requests

```java

@Http("/web")
public class HttpDemo {
    @Set
    private GetDeleteRequestDemo getDeleteRequestDemo;

    @Get("/demo")
    public String text() {
        String a = getDeleteRequestDemo.getA("network", "hello", "world");
        System.out.println("value" + a);
        return "demo";
    }
}
```
 

 
