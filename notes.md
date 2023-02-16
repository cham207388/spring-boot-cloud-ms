### Communication
Any one of the following
1. RestTemplate. create bean and annotate with @LoadBalanced
2. WebClient. create bean. It's both sync and async
3. Open feign
   1. add dependency, or.springframework.cloud:spring.cloud.starter.openfeign
   2. configure dependencyManagement (required)
   3. choose the correct compatible version with sb
   4. @EnabledFeignClients on main class
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

### config server
      - both config server and actuator dependencies are required
      - create git repo
      - spring.cloud.config.server.git.uri=https://github.com/cham207388/config-server-sb-sc-ms
      - spring.cloud.config.server.git.default-label=main
      - spring.cloud.config.server.git.username=username
      - spring.cloud.config.server.git.password=token (Profile --> settings --> developer setting)

##### Services using config server 
      - add config client and actuator as dependencies
      - create a file: <application.name>.properties
      - put all config props in this file and commit 
      ** except the following in application.properties
         - spring.application.name=<application.name>
         - spring.config.import=optional:configserver:http://localhost:8888

#### Refresh scope
      - store message in department-service.properties
      - create an endpoint that retrieves this value
      - add at @RefreshScope on the controller layer
      - change value of property in department-service.properties in git
      - restart config server
      - send a POST request to /actuator/refresh
      - this will picked up the changes from git config server
      - a call to this message endpoint will retrieve the changes

##### Problem with manually triggering /actuator/refresh
      - use message broker to pick up changes automatically
      - all services have to register to the broker and the broker will broadcast the changes
      Solution steps
         - org.springframework.cloud:spring-cloud-start-bus-amqp dependency
         - install RabbitMQ using docker
            . docker pull rabbitmq
            . docker run --rm -it -p 5672:5672 rabbitmq
         - rabbit mq configs in services
            . spring.rabbitmq.host=localhost
            . spring.rabbitmq.port=5672
            . spring.rabbitmq.username=guest
            . spring.rabbitmq.password=guest
         - change services properties and call /actuator/busrefresh

### Sleuth not working with spring boot 3
      - add dependency to services
      - org.springframework.cloud:spring-cloud-start-sleuth
      - logs format: application-name,trace-id,span-id
      - Zipkin allows you to view trace information through a UI
      Steps to use Zipkin
         - download jar file and start java -jar zipkin.jar
         - 127.0.0.1:9411/zipkin
         - add org.springframework.cloud:spring-cloud-starter-zipkin to api-gateway, and all services
         - add sleuth properties (search sleuth propertois)
            - spring.zipkin.base-url=127.0.0.1:9411
            - spring.sleuth.sampler.probability=1.0 // this 100% of logs
      