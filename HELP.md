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

### Deployment
```bash
mvn package
docker image build -t alxinsh/docker-java-hw30-order:1.0.5 .
docker push alxinsh/docker-java-hw30-order:1.0.5
```