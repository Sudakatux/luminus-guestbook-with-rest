FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/dbexample.jar /dbexample/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/dbexample/app.jar"]
