###Swagger flow:

localhost:9527
    -> redirect to 

###Notes:  
1. dir contains swagger-ui static pages should be consistent in these 3 places: 
>       content folder "src/main/resource/api-spec"  
>       SwaggerConfig.java - addResourceLocations("classpath:/api-spec/")  
>       HomeController.java - response.sendRedirect("api-spec/index.html")  

2. URL to generate/consume the api meta file should be consistent in these 2 places:
>       $project.home/src/main/resource/api-spec/index.html - swaggerSpecPath = '/swagger-spec.json'
>       application.yml - springfox:documentation:swagger:v2:path: /swagger-spec.json (check the app port as well)

to get the prod war (excluded swagger):
gradle clean prodExclude war -Dspring.profiles.active=$pringBootProfileName

