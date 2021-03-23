INSERT INTO product(id, product_type, code, description, value, quantity, enabled)
	VALUES(UUID('1fd0c5a4-87d6-41f9-a010-feeb2d316857'), 'ELECTRONIC', '0101011', 'Calculadora', '22.55', 10, 'true' ),
		  (UUID('1fd0c5a4-87d6-41f9-a010-feeb2d316858'), 'ELECTRONIC', '0101012', 'Calculadora', '22.55', 0, 'false' ),
		  (UUID('1fd0c5a4-87d6-41f9-a010-feeb2d316859'), 'FURNITURE', '0101014', 'Cadeira', '150.00', 30, 'true' ),
		  (UUID('1fd0c5a4-87d6-41f9-a010-feeb2d316860'), 'FURNITURE', '0101015', 'Cadeira Giratoria', '150.00', 30, 'true' ),
		  (UUID('1fd0c5a4-87d6-41f9-a010-feeb2d316861'), 'FURNITURE', '0101016', 'Cadeira Giratoria Azul', '150.00', 30, 'true' ),
		  (UUID('1fd0c5a4-87d6-41f9-a010-feeb2d316862'), 'FURNITURE', '0101017', 'Cadeira Giratoria Verde', '150.00', 30, 'true' );

INSERT INTO movement(id, product_id, movement_type, quantity, value, gross_profit)
	VALUES(UUID('1fd0c5a4-87d6-41f9-a010-feeb2d316857'), UUID('1fd0c5a4-87d6-41f9-a010-feeb2d316859'), 'OUT', 10, '170.00', '200.00'),
		  (UUID('1fd0c5a4-87d6-41f9-a010-feeb2d316858'), UUID('1fd0c5a4-87d6-41f9-a010-feeb2d316859'), 'OUT', 1, '180.00', '30.00'),
		  (UUID('1fd0c5a4-87d6-41f9-a010-feeb2d316859'), UUID('1fd0c5a4-87d6-41f9-a010-feeb2d316859'), 'OUT', 1, '190.00', '40.00');
