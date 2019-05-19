# Spring-Boot-Examples

# Cassandra table

CREATE TABLE contact_records (  
   emailId text,  
   name text,  
   company text,  
   phoneNumber int,  
   PRIMARY KEY (emailId)  
);

# Sping Caching: https://medium.com/@igorkosandyak/spring-boot-caching-d74591abe117

@EnableCaching - Enables Spring Caching functionality.

@CacheConfig(cacheNames={"users"}) - tells Spring where to store. Look into Service class.

@Cacheable - caches the result of findAll() method. Generally on GET apis.

@CachePut - It is used to update the cache with the result of the method execution. Generally on POST, PUT and DELETE methods.

@CacheEvict — removes data from from the cache.


# SpringBoot-RabbitMQ- Integration

1) Install rabbitMQ using docker.

docker run -d --hostname local-rabbit --name demo-rmq -p 15672:15672 -p 5672:5672 rabbitmq:3.6.11-management

2) stop rabbitMQ.

docker stop <containerId>

3) restart rabbitMQ.

docker restart <containerId>

# For more docker commands.

https://medium.com/@nagarwal/lifecycle-of-docker-container-d2da9f85959

# Deploy application on PCF.

https://www.javainuse.com/pcf/pcf-rabbitmq



