INSERT INTO org_user
	(id, name, sex, department_id)
VALUES
	(1, 'bage', 'Unknown', 	1);

INSERT INTO org_user
	(id, name, sex, department_id)
VALUES
	(2, 'bage2', 'Famale',	2);

INSERT INTO org_user
	(id, name, sex, department_id)
VALUES
	(3, 'bage3', 'Male',	3);



INSERT INTO org_department
	(id, name)
VALUES
	('1', '部门1');
INSERT INTO org_department
	(id, name)
VALUES
	('2', '部门2');


INSERT INTO org_department_address
	(id, name, department_id)
VALUES
	('1', '地址1', '1');
INSERT INTO org_department_address
	(id, name, department_id)
VALUES
	('2', '地址2','2');