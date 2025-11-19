INSERT INTO dbo.bandeira (Cor, Valor)
VALUES
    ('Verde',    0.00000),
    ('Amarela',  0.01885),
    ('Vermelha 1', 0.04463),
    ('Vermelha 2', 0.07877);

ALTER TABLE dbo.bandeira
    ADD ValorCem AS CAST(Valor * 100 AS DECIMAL(10,2));
ALTER TABLE dbo.bandeira
	ALTER COLUMN Valor DECIMAL(8,6) NOT NULL;

SELECT * FROM bandeira;