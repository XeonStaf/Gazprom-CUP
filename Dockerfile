FROM arm64v8/openjdk:17
COPY /build/libs/Gazprom-CUP-0.0.1.jar gazprom-cup.jar
CMD ["java","-jar","gazprom-cup.jar"]