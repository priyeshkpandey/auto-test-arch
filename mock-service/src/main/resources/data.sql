INSERT INTO permission (permission_name, type, resource_type) VALUES
('admin', 'read', 'product_resource'),
('admin', 'write', 'product_resource');

INSERT INTO user (first_name, last_name, user_name, password, role_name, permission_name) VALUES
('admin1', 'admin1', 'admin1', 'admin1', 'admin', ''),
('admin2', 'admin2', 'admin2', 'admin2', 'admin', '');