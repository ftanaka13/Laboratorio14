package br.com.faculdadeimpacta.laboratorio14.data

data class Tarefa(
    val titulo: String,
    val descricao: String,
    var prioridade: Int,
    var concluido: Boolean
)
