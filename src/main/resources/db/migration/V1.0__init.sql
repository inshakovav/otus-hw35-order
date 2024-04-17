CREATE TABLE order_scheme.order
(
    id          bigserial,
    created_at  timestamp NOT NULL,
    updated_at  timestamp NOT NULL,
    description text      NOT NULL,
    status      text      NOT NULL,
    CONSTRAINT pk_order_id PRIMARY KEY (id)
);

CREATE TABLE order_scheme.order_event
(
    id          bigserial,
    created_at  timestamp NOT NULL,
    order_id    integer,
    description text,
    event_type  text,
    CONSTRAINT pk_order_event_id PRIMARY KEY (id),
    CONSTRAINT FK_order_event_order_id FOREIGN KEY (order_id) REFERENCES order_scheme.order (id)
);
