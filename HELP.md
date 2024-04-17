# Order service

### REST
```bash
#curl -H 'Content-Type: application/json' \
#     -d '{ "message":"foo"}' \
#     -X POST \
#     http://localhost:8000/messages
```

#### Get all Orders
```bash
curl http://localhost:8000/order | json_pp
```

#### Add an Order
```bash
curl -H 'Content-Type: application/json' \
     -d '{ "orderDescription":"Order description", "productId":"123", "productPrice":"5.1", "productQuantity":"2.0", "deliveryAddress":"г.Москва, ул. Тверская, д.1"}' \
     -X POST \
     http://localhost:8000/order | json_pp
```

### Todo
0.5
+ DB add tables
+ Setup constrains
+ Add DB initialize script

0.5-1 Add REST POST for adding an Order
Add Kafka Producer for sending Order
Add Kafka test for Topic