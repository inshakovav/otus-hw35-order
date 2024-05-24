# Order service

### REST
#### Get all Orders
```bash
curl http://localhost:8000/order | json_pp
```

#### Add an Order
Проверка идемпотентности. Если товар похожий уже создан, то данные в БД не сохраняются.
Дубли ищутся по productId, productQuantity, BookingAt - 5 минут (через 5 минут можно создать заказ на туже позицию).
```bash
curl -H 'Content-Type: application/json' \
     -d '{ "accountId":"1", "price":"31.1", "bookingAt":"2024-04-27T02:55:28.183+00:00" }' \
     -X POST \
     http://localhost:8000/order | json_pp
```

### Deployment
```bash
mvn package
docker image build -t alxinsh/docker-java-hw35-order:1.0.0 .
docker push alxinsh/docker-java-hw35-order:1.0.0
```