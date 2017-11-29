###Run with Gradle:  
>        .\gradlew clean bootRun  

###Run with Maven:  
>       mvn spring-boot:run

###Build docker image:
>####With Maven   
>        mvn clean install docker:build   or mvn clean package docker:build   
>####With Gradle  
>>##### 1. build image  
>>		gradle buildImage  
>>##### 2. start container  
>>     gradle start
>>*start container will build image first.*   
>>*connect ot service http://192.168.33.11:9527.* 

###Local ENV:
>       http://localhost:9527  
>*api metadata page should populated in seconds*  
>*pls check host/port/url mentioned in the Notes section*

###Debug App in IDEA directly:  
>There is a known issue for latest gradle/mvn in IDEA older version
>Pls upgrade IDEA to 2017.1 or 2016.3.6 or use gradle 3.3 instead of 3.3+ 

###Dev integration ENV:
>       http://shc-itsmax-bo-1.hpeswlab.net:8080/tenant/swagger-ui.html

###Authentication in Dev env
It is HTTP Basic Authentication and access users and roles in dev env are shown as below:

>| User | Password | Roles |
>| ---- | :------: | :---: |
>| admin | 123 | TENANT_ADMIN |
>| user | 123 | USER |



