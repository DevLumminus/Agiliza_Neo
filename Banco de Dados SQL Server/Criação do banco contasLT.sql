CREATE DATABASE contasLT;

USE contasLT;

CREATE TABLE bandeira (
	idBandeira SMALLINT IDENTITY CONSTRAINT pk_idBandeira PRIMARY KEY(idBandeira),
	Cor VARCHAR(10) NOT NULL,
	Valor MONEY NOT NULL
);

CREATE TABLE contas (
	idRegistro INT IDENTITY CONSTRAINT pk_idRegistro PRIMARY KEY(idRegistro),
	PrecoKW DECIMAL(9, 7) NOT NULL,
	CIP MONEY NOT NULL,
	Bandeira SMALLINT,
	EInjetada INT,
	EAtiva INT,
	EInjetadaComp INT,
	SaldoComp INT,
	CONSTRAINT FK_Bandeira FOREIGN KEY (Bandeira) REFERENCES Bandeira(idBandeira)
);