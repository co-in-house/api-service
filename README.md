# app-service



## 登録
`curl -vvv -XPOST  "http://localhost:9080/service/api/event/info" -H "Content-type:application/json" -d '{"communityId": 1, "description": "コミュニティ1に紐づくイベントA", "end": "2019-10-01 19:00:00.0","location": "東京都千代田区紀尾井町７−１","start": "2019-10-01 12:00:00.0","thumbnailImg": "https://image.asoview-media.com/image/production/acp/3000016117/pln3000032120/a223704d-89ef-4326-931e-b382982edb25.jpeg","title": "テストイベ ントA"}'`

## 更新
`curl -vvv -XPUT  "http://localhost:9080/service/api/event/info" -H "Content-type:application/json" -d '{"eventId": 6, "description": "コミュニティ1に紐づくイベントB", "end": "2019-10-01 19:00:00.0","location": "東京都千代田区紀尾井町７−１","start": "2019-10-01 12:00:00.0","thumbnailImg": "https://image.asoview-media.com/image/production/acp/3000016117/pln3000032120/a223704d-89ef-4326-931e-b382982edb25.jpeg","title": "テストイベントA"}'`

## 閲覧
`curl -vvv "http://localhost:9080/service/api/event/list?communityids=1"`