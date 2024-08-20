CREATE TABLE products(
    id TEXT PRIMARY KEY NOT NULL,
    name TEXT NOT NULL,
    price_in_cents INTEGER NOT NULL,
    stock INTEGER NOT NULL,
    available BOOLEAN NOT NULL
)