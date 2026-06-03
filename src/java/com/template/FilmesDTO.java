package com.template;

public class FilmesDTO {

     private int id;
        private String nome;
        private String categoria;
        private String classificacao;
        private String atores;

        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getNome()
        {
            return nome;
        }

        public void setNome(String nome)
        {
            this.nome = nome;
        }

        public String getCategoria()
        {
            return categoria;
        }

        public void setCategoria(String categoria)
        {
            this.categoria = categoria;
        }

        public String getClassificacao()
        {
            return classificacao;
        }

        public void setClassificacao(String classificacao)
        {
            this.classificacao = classificacao;
        }

        public String getAtores()
        {
            return atores;
        }

        public void setAtores(String atores)
        {
            this.atores = atores;
        }

}
