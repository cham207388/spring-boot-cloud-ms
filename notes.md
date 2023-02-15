### running multiple instance
    1. java -jar -Dserver.port=9090 jarfile.jar
    2. server.port=0 (this will deploy in a random port)

### Api Gateway
1. provides a unified interface for a set of ms so that clients does not need to know the internals of the ms
2. centralize cross-cutting concerns like security, monitoring, rate limiting etc
3. SC Gateway