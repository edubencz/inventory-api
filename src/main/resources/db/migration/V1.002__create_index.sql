--product
CREATE INDEX CONCURRENTLY product_code_idx
	ON product USING btree (code);

-- movement
CREATE INDEX CONCURRENTLY movement_product_id_idx
	ON movement USING btree (product_id);
