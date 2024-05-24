CREATE TABLE order_scheme.order
(
    id         bigserial,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    account_id integer   NOT NULL,
    booking_at text,
    price      numeric(10, 2) NOT NULL DEFAULT 0,
    status     text      NOT NULL,
    CONSTRAINT pk_order_id PRIMARY KEY (id)
);

