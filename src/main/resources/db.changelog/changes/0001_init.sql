CREATE TABLE clients
(
    id          BIGSERIAL PRIMARY KEY,
    clientId    VARCHAR(255),
    fullName    VARCHAR(255),
    phone       VARCHAR(255),
    birthday    DATE,
    messageSend BOOLEAN
);