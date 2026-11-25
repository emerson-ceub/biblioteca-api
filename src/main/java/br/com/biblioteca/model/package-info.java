/**
 * Camada de modelo (entidades JPA).
 *
 * <p>Contém as classes mapeadas para tabelas do banco de dados:
 * {@code Livro}, {@code Autor} e {@code Editora}.</p>
 *
 * <p>Regra de composição: um Livro pertence a um Autor e a uma Editora
 * ({@code @ManyToOne} em Livro; {@code @OneToMany(mappedBy = ...)} em Autor e Editora).</p>
 */
package br.com.biblioteca.model;
