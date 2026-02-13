INSERT INTO personagens ( nome, idade, aldeia, chakra, tipo_de_ninja)
VALUES
    ( 'Rock Lee', 17, 'Folha', 150, 'TAIJUTSU'),
    ( 'Naruto Uzumaki', 17, 'Folha', 500, 'NINJUTSU'),
    ( 'Itachi Uchiha', 21, 'Folha', 400, 'GENJUTSU');

INSERT INTO personagem_jutsus (personagem_id, nome_jutsu, dano_maximo)
VALUES
    ((SELECT id FROM personagens WHERE nome = 'Rock Lee'), 'Chute Giratório', 80),
    ((SELECT id FROM personagens WHERE nome = 'Rock Lee'), 'Lótus Primária', 120),
    ((SELECT id FROM personagens WHERE nome = 'Naruto Uzumaki'), 'Rasengan', 150),
    ((SELECT id FROM personagens WHERE nome = 'Naruto Uzumaki'), 'Clone das Sombras', 60),
    ((SELECT id FROM personagens WHERE nome = 'Itachi Uchiha'), 'Tsukuyomi', 200),
    ((SELECT id FROM personagens WHERE nome = 'Itachi Uchiha'), 'Ilusão do Corvo', 110);
