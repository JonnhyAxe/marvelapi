# marvelapi


from https://gateway.marvel.com:443/v1/public/characters?limit=100&apikey=746d7e48cbc8d37cf4dd63c53296c3c1


Using the data.total (for instance 1491) calculate the number of calls by 1491/100 = 14,91 (round it Math.abs()). calls plus the inicial with offset equal to zero

Part 1.1 (endpoint /character)
0. create property file with apikey and limit (DONE)
1. consume Caracters api to dynamically create DTOs with swagger api (DONE)
2. create the stream with REST calls (parallel) by offset /characters?limit=100&offset=100&apikey=746d7e48cbc8d37cf4dd63c53296c3c1 (DONE)
AND collect the result (filtered by the necessary info???).
3. create the service (getAllIds()) to return in a JSON array of numbers (DONE).
4. Create Live Test (It should query the API to get data.total, for instance  1491 in order to compare to the amount of data returned by the API) (DONE)
5. Create the cache with the loaded data. The data strucure should be O(1) for performance of the API and thread safe. (DONE)

Part 1.2 (/characters/{characterId})
1. Create a Domain model with only id, name, description, thumbnail
2. Create a service with getById
2. Fecth characterId  parameter from the controller

Part 1.3 (add comics popularity to /characters/{characterId})

0. do not create an thread per request. Use gouping approach
1. for each commics do https://gateway.marvel.com:443/v1/public/characters/1009718/comics?apikey=746d7e48cbc8d37cf4dd63c53296c3c1
2. get data.total and update the cache

Assumptions
After the startup there all some scheduler that update the cache using Etags to understand if the data has changed

Part II.4
1. fetch
          {
            "type": "wiki",
            "url": "http://marvel.com/universe/Parker%2C_Ben_%28Uncle_Ben%29?utm_campaign=apiRef&utm_source=746d7e48cbc8d37cf4dd63c53296c3c1"
          }
2. Call the URL and fetch div with id="char-powers-contect"

Part II.5

1. Add parameter to ?language={languageCode}

Part II.6



Part


#Consume with swagger


https://developer.marvel.com/account

Your public key : 746d7e48cbc8d37cf4dd63c53296c3c1
Your private key : 8528221deac74528900e3121213f45093c2a464e
Domain : developer.marvel.com

https://developer.marvel.com/documentation/authorization

ts - a timestamp (or other long string which can change on a request-by-request basis)
hash - a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)

md5(ts+privateKey+publicKey)
1 8528221deac74528900e3121213f45093c2a464e 746d7e48cbc8d37cf4dd63c53296c3c1

from https://www.md5hashgenerator.com/
16d47e4b2b2d98f55ee6f922f246e786

- Valid URL : http://gateway.marvel.com/v1/public/characters?ts=1&apikey=746d7e48cbc8d37cf4dd63c53296c3c1&hash=16d47e4b2b2d98f55ee6f922f246e786


C:\dev\swagger-codegen-master>java -jar modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate -i "http://gateway.marvel.com/docs" -l java -o sample/client/marvel/java

C:\dev\swagger-codegen-master>java -jar modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate -i "http://gateway.marvel.com/docs" -l java -o src/main/java/com/swagger/marvelapi/services/marvel/dtos

#Create Marvel Client
https://dzone.com/articles/marvels-superheroic-rest-api

#Etags
https://developer.marvel.com/documentation/generalinfo
