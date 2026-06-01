# container-network

网络请求

[English](./README.md)

支持功能：

- [x] Get请求
- [x] Post请求
- [x] Put请求
- [x] Delete请求

# 提示：

请求参数组合问题：
请求参数，可以自由组合，不要因此违法了请求规则，例如Get请求不能包含Body

```xml
<!--在pom.xml中添加依赖-->
<dependency>
    <artifactId>container-network-loading</artifactId>
    <groupId>io.github.thierrysquirrel</groupId>
    <version>1.0.0.0-RELEASE</version>
</dependency>
``` 

### 配置文件

 ```properties
 ## Java.ClassLoading
Class.forName=io.github.thierrysquirrel.network.loading.NetworkLoading
Method.setConnectTimeout.int=8192
Method.setReadTimeout.int=8192
Method.setWriteTimeout.int=8192
 ```

# 启动Network

```java

@ScannerPackage(packageName = "com.hello.world.web.network")

public class NetworkRegistrationImpl implements InterfaceManualRegistration {
    @Override
    public void scannerAll(List<Class<?>> scannerClassList, Map<Class<?>, Object> registrationMap) {
        NetworkRegistration.networkRegistrationScannerAll(scannerClassList, registrationMap);
    }
}
```

# 构建Get、Delete请求

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

# 构建Post,Put请求

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

# 发送请求

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


