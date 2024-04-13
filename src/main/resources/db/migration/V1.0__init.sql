CREATE TABLE order_scheme.order
(
    id   bigserial,
    name text,
    CONSTRAINT pk_order_id PRIMARY KEY (id)
);

CREATE TABLE order_scheme.order_event
(
    id         bigserial,
    order_id   integer,
    name       text,
    event_type text,
    CONSTRAINT pk_order_event_id PRIMARY KEY (id),
    CONSTRAINT FK_order_event_order_id FOREIGN KEY (order_id) REFERENCES order_scheme.order (id)
);
