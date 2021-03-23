-- trigger_set_updated_at
CREATE OR REPLACE FUNCTION trigger_set_updated_at()
	RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
	$$ LANGUAGE 'plpgsql';

-- product
CREATE TABLE product (
	id uuid NOT NULL,
	product_type TEXT NOT NULL CHECK (char_length(product_type) <= 150),
	code TEXT NOT NULL CHECK (char_length(code) <= 150),
	description TEXT NOT NULL CHECK (char_length(description) <= 255),
	value NUMERIC(15,5) NOT NULL,
	quantity INTEGER NOT NULL,
	enabled BOOLEAN NOT NULL DEFAULT TRUE,
	created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
	updated_at TIMESTAMP WITH TIME ZONE NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id),
	CONSTRAINT product_code_un UNIQUE (code)
);

CREATE TRIGGER product_tgr_bu
	BEFORE UPDATE ON product
	FOR EACH ROW
	EXECUTE PROCEDURE trigger_set_updated_at();

-- movement

CREATE TABLE movement (
	id uuid NOT NULL,
	product_id uuid NOT NULL,
	movement_type TEXT NOT NULL CHECK (char_length(movement_type) <= 50),
	value NUMERIC(15,5) NOT NULL,
	gross_profit NUMERIC(15,5) NOT NULL,
	quantity INTEGER NOT NULL,
	event_at TIMESTAMP WITH TIME ZONE NULL,
	created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
	updated_at TIMESTAMP WITH TIME ZONE NULL,
	CONSTRAINT movement_pkey PRIMARY KEY (id),
	CONSTRAINT product_fkey FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TRIGGER movement_tgr_bu
	BEFORE UPDATE ON movement
	FOR EACH ROW
	EXECUTE PROCEDURE trigger_set_updated_at();
