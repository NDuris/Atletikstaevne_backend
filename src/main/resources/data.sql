-- Indsæt testdata for deltager
INSERT INTO deltager (alder, klub, koen, navn) VALUES
                                                   (25, 'Klub A', 'Mand', 'Peter Jensen'),
                                                   (30, 'Klub B', 'Kvinde', 'Anne Petersen');

-- Indsæt testdata for disciplin
INSERT INTO disciplin (navn, resultat_type) VALUES
                                               ('100 meter sprint', 'TID'),
                                               ('Længdespring', 'AFSTAND');

-- Indsæt testdata for resultat
INSERT INTO resultat (dato, deltager_id, disciplin_id, resultat_type, resultatvaerdi) VALUES
                                                                                         ('2023-01-01', 1, 1, 'TID', '0:12.35'),
                                                                                         ('2023-01-02', 2, 2, 'AFSTAND', '7.80 meter');
