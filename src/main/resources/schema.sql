DROP ALL OBJECTS;

--account
CREATE TABLE tb_account (
    account_id   INTEGER      NOT NULL AUTO_INCREMENT,
    account_document VARCHAR(14) NOT NULL,
    account_amount_limit      NUMBER(9, 2)    NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (account_id)
);

-- operation
CREATE TABLE tb_operation (
    operation_id  INTEGER NOT NULL,
    operation_description  VARCHAR(30) NOT NULL,
    CONSTRAINT pk_operation PRIMARY KEY (operation_id)
);

--transaction
CREATE TABLE IF NOT EXISTS tb_transaction(
    transaction_id   INTEGER      NOT NULL AUTO_INCREMENT,
    account_id    INTEGER NOT NULL,
    operation_id           INTEGER NOT NULL,
    operation_amount                 NUMBER(9, 2)    NOT NULL,
    operation_event_datetime  DATETIME NOT NULL,
    CONSTRAINT pk_transaction PRIMARY KEY (transaction_id),
    CONSTRAINT fkr_acc_tranacc FOREIGN KEY (account_id) REFERENCES tb_account(account_id),
    CONSTRAINT fkr_opr_tranacc FOREIGN KEY (operation_id) REFERENCES tb_operation(operation_id)
);
