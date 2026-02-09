DELETE FROM personagem_jutsus;
DELETE FROM personagens;

ALTER TABLE personagens ALTER COLUMN id RESTART WITH 1;