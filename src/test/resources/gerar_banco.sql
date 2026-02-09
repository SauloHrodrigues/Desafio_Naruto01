INSERT INTO personagens (id, nome, idade, aldeia, chakra, tipo_de_ninja)
VALUES
    (1, 'Rock Lee', 17, 'Folha', 150, 'TAIJUTSU'),
    (2, 'Naruto Uzumaki', 17, 'Folha', 500, 'NINJUTSU'),
    (3, 'Itachi Uchiha', 21, 'Folha', 400, 'GENJUTSU');

INSERT INTO personagem_jutsus (personagem_id, nome_jutsu, dano_maximo)
VALUES
    (1, 'Chute Giratório', 80),
    (1, 'Lótus Primária', 120),
    (2, 'Rasengan', 150),
    (2, 'Clone das Sombras', 60),
    (3, 'Tsukuyomi', 200),
    (3, 'Ilusão do Corvo', 110);
