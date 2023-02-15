### Communication
Any one of the following
1. RestTemplate. create bean and annotate with @LoadBalanced
2. WebClient. create bean. It's both sync and async
3. Open feign
   1. add dependency, or.springframework.cloud:spring.cloud.starter.openfeign
   2. configure dependencyManagement (required)
   3. choose the correct compatible version with sb
   4. @EnaledFeignClients on main class
   5. @FeignClient(name = "DEPARTMENT-SERVICE") on interface like controller methods
      1. with http method, and complete path
      2. use as you would restTemplate or webClient

### Running multiple instance
    1. java -jar -Dserver.port=9090 jarfile.jar
    2. server.port=0 (this will deploy in a random port)

### Api Gateway
1. provides a unified interface for a set of ms so that clients does not need to know the internals of the ms
2. centralize cross-cutting concerns like security, monitoring, rate limiting etc
3. SC Gateway 
   1. config properties: https://cloud.spring.io/spring-cloud-gateway/reference/html/appendix.html
4. once api-gateway and service registry are used, we can use server.port=0 to assign a random for the service at runtime.