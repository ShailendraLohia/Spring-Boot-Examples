# Spring-Boot-Examples

#Cassandra table

CREATE TABLE contact_records (  
   emailId text,  
   name text,  
   company text,  
   phoneNumber int,  
   PRIMARY KEY (emailId)  
);

#Sping Caching: https://medium.com/@igorkosandyak/spring-boot-caching-d74591abe117

@EnableCaching - Enables Spring Caching functionality.

@CacheConfig(cacheNames={"users"}) - tells Spring where to store. Look into Service class.

@Cacheable - caches the result of findAll() method. Generally on GET apis.

@CachePut - It is used to update the cache with the result of the method execution. Generally on POST, PUT and DELETE methods.

@CacheEvict — removes data from from the cache.



