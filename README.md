# app-service



## 登録
`curl -vvv -XPOST  "http://localhost:9080/service/api/event/info" -H "Content-type:application/json" -d '{"communityId": 1, "description": "コミュニティ1に紐づくイベントA", "end": "2019-10-01 19:00:00.0","location": "東京都千代田区紀尾井町７−１","start": "2019-10-01 12:00:00.0","thumbnailImg": "https://image.asoview-media.com/image/production/acp/3000016117/pln3000032120/a223704d-89ef-4326-931e-b382982edb25.jpeg","title": "テストイベ ントA"}'`

## 更新
`curl -vvv -XPUT  "http://localhost:9080/service/api/event/info" -H "Content-type:application/json" -d '{"eventId": 6, "description": "コミュニティ1に紐づくイベントB", "end": "2019-10-01 19:00:00.0","location": "東京都千代田区紀尾井町７−１","start": "2019-10-01 12:00:00.0","thumbnailImg": "https://image.asoview-media.com/image/production/acp/3000016117/pln3000032120/a223704d-89ef-4326-931e-b382982edb25.jpeg","title": "テストイベントA"}'`

## 閲覧
`curl -vvv "http://localhost:9080/service/api/event/list?communityids=1"`




## push image into Container Registroy
1. `docker tag app-service:1.0.0 us.icr.io/inh-p-01/app-service:1.0.0`
2. `docker push us.icr.io/inh-p-01/app-service:1.0.0`
3. `ibmcloud iam api-key-create inhouse-cr -d "for container registry access" --file crKey.json`
4. `export $( echo $(cat crKey.json) | jq -r 'keys[] as $k | "export \($k)=\(.[$k])"')`
5. `export CF_DOCKER_PASSWORD=$apikey`

## deploy image to Cloud Foundry
1. `ibmcloud target -r au-syd`
2. `ibmcloud target --cf`
3. `ibmcloud target -g Default`
4. check diego_docker is enabled
   1. `ibmcloud cf feature-flags`
5. `ibmcloud cf push app-service --docker-image us.icr.io/inh-p-01/app-service:1.0.0 --docker-username iamapikey -f manifest.yaml`
6. `ibmcloud apps`
7. `ibmcloud cf logs  app-service`